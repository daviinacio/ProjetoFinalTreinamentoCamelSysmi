package br.com.sysmanager.academy.camel.projetofinal.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * @author Davi-PC
 */
@Component
public class RestApiBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .contextPath("/").port(8080)
                .apiContextPath("/")
                .apiProperty("cors", "true");
    }
    
}
