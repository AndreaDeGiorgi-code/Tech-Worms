package com.libreria.techbook;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl("jdbc:mysql://mysql-techbook-techbook-db.d.aivencloud.com:18759/javadb"
            + "?useSSL=true&requireSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true");

        dataSource.setUsername("avnadmin"); // utente Aiven
        dataSource.setPassword("AVNS_gtzdZQp_tqmCcDF2CLW"); // password Aiven

        return dataSource;
    }
}