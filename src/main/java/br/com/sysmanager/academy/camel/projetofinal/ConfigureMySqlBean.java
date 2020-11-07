package br.com.sysmanager.academy.camel.projetofinal;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * @author Davi-PC
 */
@Component("DataSourceConfig")
public class ConfigureMySqlBean {
    @Value("${spring.datasource.driver}")
    public String driver;
    
    @Value("${spring.datasource.host}")
    public String host;
    
    @Value("${spring.datasource.port}")
    public String port;

    @Value("${spring.datasource.username}")
    public String username;
    
    @Value("${spring.datasource.database}")
    public String database;

    @Value("${spring.datasource.password}")
    public String password;
    
    @Value("${spring.datasource.platform}")
    public String platform;
    
    @Bean("database")
    public DataSource getConfig() {
        String url = "jdbc:" + platform + "://" + host + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC";
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
