package br.com.sysmanager.academy.camel.projetofinal;

import java.util.Map;
import java.util.TreeMap;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.springframework.stereotype.Component;

/**
 * @author Davi-PC
 */
@Component
public class GetCorreiosData extends RouteBuilder {
    private final String id = "{{route.getcorreiosdata.id}}";
    
    private final String uri = "direct:" + id;
    
    @Override
    public void configure() throws Exception {
        
        /*XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setForceTopLevelObject(true);
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setRootName("return");
        xmlJsonFormat.setSkipNamespaces(true);
        xmlJsonFormat.setRemoveNamespacePrefixes(true);*/
        
        Map<String, String> ns = new TreeMap<String, String>();
        ns.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        ns.put("ns2", "http://cliente.bean.master.sigep.bsb.correios.com.br/");

        from(uri).routeId(id)
            .setBody(simple("<cep>22710200</cep>"))
            .to("xslt:correios-soap.xslt")
            //.log("[1]\n${body}")
                
            //.to("http://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente")
                
            .pipeline()
                .to("direct:fakecorreios")
                //.split().xpath("/soap:Envelope/soap:Body/ns2:consultaCEPResponse/return", ns)
                .to("direct:fakecorreiosjson");
                
            /*.pipeline()
                .to("direct:fakecorreios")
                .log("[2]\n${body}")

                .split().xpath("/soap:Envelope/soap:Body/ns2:consultaCEPResponse/return", ns)
                .log("[3]\n${body}")

                .to("direct:fakecorreiosjson")
                .log("[4]\n${body}")
            
            .end();*/
        
        from("direct:fakecorreios")
            .setBody(simple(
                "<soap:Envelope\n" +
                "    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body>\n" +
                "        <ns2:consultaCEPResponse\n" +
                "            xmlns:ns2=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\">\n" +
                "            <return>\n" +
                "                <bairro>Jardim Mutinga</bairro>\n" +
                "                <cep>06463170</cep>\n" +
                "                <cidade>Barueri</cidade>\n" +
                "                <complemento2></complemento2>\n" +
                "                <end>Rua Alagoinha</end>\n" +
                "                <uf>SP</uf>\n" +
                "            </return>\n" +
                "        </ns2:consultaCEPResponse>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>"
            ))
            .end();
        
        from("direct:fakecorreiosjson")
            .setBody(simple(
                "{\n" +
                "   \"bairro\": \"Jardim Mutinga\",\n" +
                "   \"cep\": \"06463170\",\n" +
                "   \"cidade\": \"Barueri\",\n" +
                "   \"complemento2\": \"\",\n" +
                "   \"end\": \"Rua Alagoinha\",\n" +
                "   \"uf\": \"SP\"\n" +
                "}"
            ))
            .end();
    }
    

//    @Override
//    public void configure() throws Exception {
//        Map<String, String> ns = new TreeMap<String, String>();
//        ns.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
//        ns.put("ns2", "http://cliente.bean.master.sigep.bsb.correios.com.br/");
//        
//        from(uri).routeId(id)
//            .setBody(simple(
//                "<soap:Envelope\n" +
//                "    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//                "    <soap:Body>\n" +
//                "        <ns2:consultaCEPResponse\n" +
//                "            xmlns:ns2=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\">\n" +
//                "            <return>\n" +
//                "                <bairro>Jardim Mutinga</bairro>\n" +
//                "                <cep>06463170</cep>\n" +
//                "                <cidade>Barueri</cidade>\n" +
//                "                <complemento2></complemento2>\n" +
//                "                <end>Rua Alagoinha</end>\n" +
//                "                <uf>SP</uf>\n" +
//                "            </return>\n" +
//                "        </ns2:consultaCEPResponse>\n" +
//                "    </soap:Body>\n" +
//                "</soap:Envelope>"
//            ))
//                
//            
//            .log("[1] ${body}")
//            .split().xpath("/soap:Envelope/soap:Body/ns2:consultaCEPResponse/return", ns)
//            .log("[2] ${body}")
//            //.marshal().xmljson()
//            .log("[2] ${body}")
//            .end();
//        
//        /*from(uri).routeId(id)
//            .setBody(simple("<cep>22710200</cep>"))
//            .log(id + ": ${body}")
//            .to("xslt:correios-soap.xslt")
//            .log(id + ":\n${body}")
//            .setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
//            .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
//                
//        .to("cxf://https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente"
//            + "?serviceClass=br.com.correios.bsb.sigep.master.bean.cliente.ConsultaCEP"
//            + "&wsdlURL=src\\wsdl\\apps.correios.com.br\\SigepMasterJPA\\AtendeClienteService\\AtendeCliente.wsdl")
//                
//        //.to("http://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?q=ssl&bridgeEndpoint=true&throwExceptionOnFailure=false")
//        .log(id + ": ${body}");*/
//    }   
    
}
 