package br.com.sysmanager.academy.camel.projetofinal;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReadFileBuilder extends RouteBuilder {
    private final String id = "{{route.readfile.id}}";
    private final String directoryName = "{{route.readfile.path}}";
    private final String fileName = "{{route.readfile.filename}}";

    private final String uri = "file:" + directoryName + "?filename=" + fileName + "&delete=true";

	@Override
	public void configure() throws Exception {
            from(uri).routeId(id)
                //.to("direct:{{route.getcorreiosdata.id}}")
                .split().jsonpathWriteAsString("$")
                    //.log("[split]\n${body}")
                    .enrich("direct:{{route.getcorreiosdata.id}}", new AggregateAddressToPatient())
                    //.log("[with_cep]\n${body}")
                    
                .to("direct:{{route.insertdatabase.id}}");
                //.log("{\n\n\n\n}");
	}
}
