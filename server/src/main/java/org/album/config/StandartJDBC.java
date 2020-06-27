package org.album.config;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.sqlite.SQLiteDataSource;

@Configuration
public class StandartJDBC {

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @Scope("singleton")
    SQLiteDataSource getDataSoruce(){
        SQLiteDataSource source = new SQLiteDataSource();
        source.setUrl(url);
//        source.setUsername(username);
//        source.setPassword(password);
        return source;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/master.xml");
        liquibase.setDataSource(getDataSoruce());
        return liquibase;
    }
}
