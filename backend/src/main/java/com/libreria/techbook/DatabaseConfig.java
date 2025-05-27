package com.libreria.techbook;
 
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// questa annotazione dice a spring che questa classe contiene metodi di configurazione.
@Configuration
public class DatabaseConfig { // questa classe contiene i dati per connettersi al db

	// Spring scansionerà questa classe e registrerà tutti i bean definiti come metodi con @Bean.
	// @Bean: dice a Spring di registrare il risultato del metodo nel contesto come un bean, accessibile ovunque nell'app.
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/javadb?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword(""); // ho lasciato password vuota
        return dataSource;
    }
}