package br.com.sysmanager.academy.camel.projetofinal.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author Davi-PC
 */
@Component
public class PatientsApiRouteBuilder extends RouteBuilder {
    private final String selectColumns = "idade, cor, altura, peso, tipo_sanguineo, signo, mae, pai, numero, cep, cidade, uf, bairro, rua, complemento, DATE_FORMAT(data_nasc, '%d/%m/%Y') as data_nasc";
    private final String sql = "SELECT " + selectColumns + " FROM patients";
    
    @Override
    public void configure() throws Exception {
        rest("/pacientes").description("Api de Pacientes")
            .consumes(MediaType.APPLICATION_JSON_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
                
            .get().description("Consulta Pacientes")
                .param().name("cidade")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .param().name("estado")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .param().name("uf")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .param().name("idade")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .route().id("{{route.restgetpatients.id}}")
                    .choice()
                        .when(simple("${header.cidade} != null"))
                            .transform().simple(sql + " WHERE cidade = '${header.cidade}'")
                            .to("jdbc:database")
                
                        .when(simple("${header.estado} != null"))
                            .transform().simple(sql + " WHERE uf = '${header.estado}'")
                            .to("jdbc:database")
                        
                        .when(simple("${header.uf} != null"))
                            .transform().simple(sql + " WHERE uf = '${header.uf}'")
                            .to("jdbc:database")
                
                        .when(simple("${header.idade} != null"))
                            .transform().simple(sql + " WHERE idade = '${header.idade}'")
                            .to("jdbc:database")
                
                        .otherwise()
                            .transform().simple(sql)
                            .to("jdbc:database")
                .endRest()
                
            .get("/pesomedio").description("Consulta o peso médio dos pacientes")
                .param().name("idade")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .param().name("uf")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()
                
                .param().name("estado")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .defaultValue("")
                    .endParam()

                .route().id("{{route.restgetpatientspesomedio.id}}")
                    .choice()
                        .when(simple("${header.idade} != null"))
                            .transform().simple("SELECT peso from patients WHERE idade = '${header.idade}'")
                            .to("jdbc:database")
                            .marshal().json(JsonLibrary.Jackson)
                            .process(calcPesoMedio)
                            .unmarshal().json(JsonLibrary.Jackson)
                            .stop()

                        .when(simple("${header.estado} != null"))
                            .transform().simple("SELECT peso from patients WHERE uf = '${header.estado}'")
                            .to("jdbc:database")
                            .marshal().json(JsonLibrary.Jackson)
                            .process(calcPesoMedio)
                            .unmarshal().json(JsonLibrary.Jackson)
                            .stop()
                        
                        .when(simple("${header.uf} != null"))
                            .transform().simple("SELECT peso from patients WHERE uf = '${header.uf}'")
                            .to("jdbc:database")
                            .marshal().json(JsonLibrary.Jackson)
                            .process(calcPesoMedio)
                            .unmarshal().json(JsonLibrary.Jackson)
                            .stop()

                        .otherwise()
                            .transform().simple("SELECT peso from patients")
                            .to("jdbc:database")
                            .marshal().json(JsonLibrary.Jackson)
                            .process(calcPesoMedio)
                            .unmarshal().json(JsonLibrary.Jackson)
                
                .endRest();
                        
    }
    
    Processor calcPesoMedio = new Processor() {
        private int sum = 0;
        
        @Override
        public void process(Exchange exchange) throws Exception {
            JSONArray patients = new JSONArray(exchange.getIn().getBody(String.class));
            
            sum = 0;
            
            patients.forEach((pat) -> {;
                JSONObject patient = new JSONObject(pat.toString());
                int peso = patient.getInt("peso");
                
                sum += peso;
            });
            
            int count = patients.length();
            
            JSONObject result = new JSONObject();
            
            if(count > 0)
                result.put("peso_medio", sum / count);
            else {
                result.put("peso_medio", 0);
                result.put("message", "Nenhum paciente encontrado");
            }
            
            exchange.getIn().setBody(result.toString());
        }
    };
}
