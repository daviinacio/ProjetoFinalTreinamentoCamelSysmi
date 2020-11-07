package br.com.sysmanager.academy.camel.projetofinal;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
//@ComponentScan("com.sample.camel")
public class MySpringBootApplication {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) throws InterruptedException {
    	ConfigurableApplicationContext app =  SpringApplication.run(
                MySpringBootApplication.class,
                args
        );
        
        //Thread.sleep(10000);
        
        //app.stop();
    }
    
    /*@Bean("RestApiRouteRegistry")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean
          (new CamelHttpTransportServlet(), "/*");
        servlet.setName("CamelServlet");
        return servlet;
    }*/
}
