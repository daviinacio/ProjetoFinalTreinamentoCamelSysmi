package br.com.sysmanager.academy.camel.projetofinal;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Davi-PC
 */
@Component
public class InsertPatientIntoDatabase extends RouteBuilder {
    private final String id = "{{route.insertdatabase.id}}";
    
    private final String uri = "direct:" + id;
    
    @Override
    public void configure() throws Exception {
        from(uri).routeId(id)
            .setProperty("nome", jsonpath("$.nome"))
            .setProperty("cpf", jsonpath("$.cpf"))
            .setProperty("rg", jsonpath("$.rg"))
            .setProperty("data_nasc", jsonpath("$.data_nasc"))
            .setProperty("idade", jsonpath("$.idade"))
            .setProperty("cor", jsonpath("$.cor"))
            .setProperty("altura", jsonpath("$.altura"))
            .setProperty("peso", jsonpath("$.peso"))
            .setProperty("tipo_sanguineo", jsonpath("$.tipo_sanguineo"))
            .setProperty("signo", jsonpath("$.signo"))
            .setProperty("celular", jsonpath("$.celular"))
            .setProperty("telefone_fixo", jsonpath("$.telefone_fixo"))
            .setProperty("mae", jsonpath("$.mae"))
            .setProperty("pai", jsonpath("$.pai"))
            .setProperty("email", jsonpath("$.email"))
            .setProperty("senha", jsonpath("$.senha"))
            .setProperty("numero", jsonpath("$.numero"))
            .setProperty("cep", jsonpath("$.cep"))
            .setProperty("cidade", jsonpath("$.cidade"))
            .setProperty("uf", jsonpath("$.uf"))
            .setProperty("bairro", jsonpath("$.bairro"))
            .setProperty("rua", jsonpath("$.rua"))
            .setProperty("complemento", jsonpath("$.complemento"))
                
            //.to("sql:classpath:D:\\Cursos\\SysAcademy\\ProjetoFinalSysmi\\src\\main\\resources\\sql\\InsertPatientsIntoTable.sql")
            
            .setBody(simple(
                "INSERT INTO patients (\n" +
                "    nome, cpf, rg, data_nasc, idade, cor, altura, peso, tipo_sanguineo, signo, celular, telefone_fixo, mae, pai, email, senha, numero, cep, cidade, uf, bairro, rua, complemento\n" +
                ")\n" +
                "VALUES (\n" +
                "    '${exchangeProperty.nome}',\n" +
                "    '${exchangeProperty.cpf}',\n" +
                "    '${exchangeProperty.rg}',\n" +
                "    DATE_FORMAT(STR_TO_DATE('${exchangeProperty.data_nasc}', '%d/%m/%Y'), '%Y-%m-%d'),\n" +
                "    '${exchangeProperty.idade}',\n" +
                "    '${exchangeProperty.cor}',\n" +
                "    '${exchangeProperty.altura}',\n" +
                "    '${exchangeProperty.peso}',\n" +
                "    '${exchangeProperty.tipo_sanguineo}',\n" +
                "    '${exchangeProperty.signo}',\n" +
                "    '${exchangeProperty.celular}',\n" +
                "    '${exchangeProperty.telefone_fixo}',\n" +
                "    '${exchangeProperty.mae}',\n" +
                "    '${exchangeProperty.pai}',\n" +
                "    '${exchangeProperty.email}',\n" +
                "    '${exchangeProperty.senha}',\n" +
                "    '${exchangeProperty.numero}',\n" +
                "    '${exchangeProperty.cep}',\n" +
                "    '${exchangeProperty.cidade}',\n" +
                "    '${exchangeProperty.uf}',\n" +
                "    '${exchangeProperty.bairro}',\n" +
                "    '${exchangeProperty.rua}',\n" +
                "    '${exchangeProperty.complemento}'\n" +
                ")"
            ))
            .to("jdbc:database")
            .log("Paciente ${exchangeProperty.nome} inserido no banco de dados");
    }
    
}
