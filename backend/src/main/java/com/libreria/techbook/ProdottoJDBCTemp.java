package com.libreria.techbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class ProdottoJDBCTemp {

    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }   
    
    public ArrayList<Prodotto> ritornaProdotto() {
        try {
            String query = "SELECT * FROM prodotti";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Prodotto>>() {
                @Override
                public ArrayList<Prodotto> extractData(ResultSet rs) throws SQLException {
                    ArrayList<Prodotto> listaProd = new ArrayList<>();
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId(rs.getInt("id"));
                        prodotto.setNomeProdotto(rs.getString("nome_prodotto"));
                        prodotto.setCategoria(rs.getString("categoria"));
                        prodotto.setAutore(rs.getString("autore"));
                        
                      
                        listaProd.add(prodotto);
                    }
                    return listaProd;
                }
            });
        } catch (Exception e) {
            // Gestione dell'errore
            return new ArrayList<>();
        }
    }
      
    public void creaNuovaTabella() {
  

    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'prodotti'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE prodotti (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome_prodotto VARCHAR(255)," +
                "categoria VARCHAR(255)," +
                "autore VARCHAR(255)" +
                ")";
        			
        
        try {
            // Esegui la query di controllo
            List<Map<String, Object>> tables = jdbcTemplateObject.queryForList(checkTableQuery);

            // Se la tabella non esiste, crea la tabella
            if (tables.isEmpty()) {
                jdbcTemplateObject.execute(query);
            } 
        } catch (Exception e) {
            // Gestione dell'errore
            e.printStackTrace();
        }
        
       
    }
    
    
    public void creaNuovaTabUsers() {
        // Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'users'";

        // Query SQL per creare una nuova tabella
        String createTableQuery = "CREATE TABLE users (" +
                                  "userid INT AUTO_INCREMENT PRIMARY KEY," +
                                  "username VARCHAR(255)," +
                                  "email VARCHAR(255)," +
                                  "password VARCHAR(255)," +
                                  "punteggio INT" +
                                  ")";

        try {
            // Esegui la query di controllo
            List<Map<String, Object>> tables = jdbcTemplateObject.queryForList(checkTableQuery);

            // Se la tabella non esiste, crea la tabella
            if (tables.isEmpty()) {
                jdbcTemplateObject.execute(createTableQuery);
            }
        } catch (Exception e) {
            // Gestione dell'errore
            e.printStackTrace();
        }
    }
    
    
    
    public ArrayList<User> ritornaUsers() {
        try {
            String query = "SELECT * FROM users";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<User>>() {
                @Override
                public ArrayList<User> extractData(ResultSet rs) throws SQLException {
                    ArrayList<User> listUsers = new ArrayList<>();
                    while (rs.next()) {
                    	User user = new User();
                        user.setUserId(rs.getInt("userid"));
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setPunteggio(rs.getInt("punteggio"));
                     
                        listUsers.add(user);
                    }
                    return listUsers;
                }
            });
        } catch (Exception e) {
            // Gestione dell'errore
            return new ArrayList<>();
        }
    }
    
    
    // Metodo per eseguire query DDL
    public void executeDDLQuery(String query) {
        try {
            jdbcTemplateObject.execute(query);
        } catch (Exception e) {
            // Gestione dell'errore
        }
    }
}

