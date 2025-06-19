package com.libreria.techbook.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.libreria.techbook.model.Challange;
import com.libreria.techbook.model.LibreriaUser;
import com.libreria.techbook.model.Moderatore;
import com.libreria.techbook.model.Prodotto;
import com.libreria.techbook.model.Storico;
import com.libreria.techbook.model.User;

@Component
public class ProdottoJDBCTemp {

    private JdbcTemplate jdbcTemplateObject;

    /**
     * Inietta il bean JdbcTemplate necessario per eseguire query
     * sul database.
     * 
     * @param jdbcTemplateObject il bean JdbcTemplate
     */
    @Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }   
    
    /**
     * Ritorna una lista di oggetti Prodotto contenenti i dati presenti
     * nella tabella "libri" del database.
     * 
     * @return lista di oggetti Prodotto
     */
    public ArrayList<Prodotto> ritornaProdotto() {
        try {
            String query = "SELECT * FROM libri";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Prodotto>>() {
                @Override
                public ArrayList<Prodotto> extractData(ResultSet rs) throws SQLException {
                    ArrayList<Prodotto> listaProd = new ArrayList<>();
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId(rs.getInt("id"));
                        prodotto.setTitolo(rs.getString("titolo"));
                        prodotto.setGenere(rs.getString("genere"));
                        prodotto.setAutore(rs.getString("autore"));
                        prodotto.setLetture(rs.getInt("letture"));
                        prodotto.setCopertina(rs.getString("copertina"));
                        
                      
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

   
    
    /**
     * Ritorna una lista di oggetti Moderatore contenenti i dati presenti
     * nella tabella "moderatore" del database.
     * La lista contiene oggetti di tipo Moderatore, ciascuno dei quali
     * rappresenta un libro con i propri attributi: id, userMod, titoloMod,
     * genereMod, autoreMod e copertinaMod.
     * Se si verifica un errore durante l'esecuzione della query, viene
     * ritornata una lista vuota.
     * @return lista di oggetti Moderatore
     */
    public ArrayList<Moderatore> ritornaTabModeratore() {
        try {
            String query = "SELECT * FROM moderatore";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Moderatore>>() {
                @Override
                public ArrayList<Moderatore> extractData(ResultSet rs) throws SQLException {
                    ArrayList<Moderatore> listaModeratore = new ArrayList<>();
                    while (rs.next()) {
                        Moderatore moderatore = new Moderatore();
                        moderatore.setId(rs.getInt("id"));
                        moderatore.setUserMod(rs.getString("user_mod"));
                        moderatore.setTitoloMod(rs.getString("titolo_mod"));
                        moderatore.setGenereMod(rs.getString("genere_mod"));
                        moderatore.setAutoreMod(rs.getString("autore_mod"));
                        moderatore.setCopertinaMod(rs.getString("copertina_mod"));
                        
                      
                        listaModeratore.add(moderatore);
                    }
                    return listaModeratore;
                }
            });
        } catch (Exception e) {
            // Gestione dell'errore
            return new ArrayList<>();
        }
    }
    
    
    /**
     * Ritorna una lista di oggetti LibreriaUser contenenti i dati presenti
     * nella tabella "nomeLibreria" del database.
     * La lista contiene oggetti di tipo LibreriaUser, ciascuno dei quali
     * rappresenta un libro con i propri attributi: idLibreria, idLibro,
     * titoloLibro, genereLibro, autoreLibro e copertinaLibro.
     * Se si verifica un errore durante l'esecuzione della query, viene
     * ritornata una lista vuota.
     * @param nomeLibreria nome della libreria
     * @return lista di oggetti LibreriaUser
     */
    public ArrayList<LibreriaUser> ritornaLibreria(String nomeLibreria) {
        try {
            String query = "SELECT * FROM " + nomeLibreria;
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<LibreriaUser>>() {
                @Override
                public ArrayList<LibreriaUser> extractData(ResultSet rs) throws SQLException {
                    ArrayList<LibreriaUser> listaLibrerie = new ArrayList<>();
                    while (rs.next()) {
                        LibreriaUser libreria = new LibreriaUser();
                        libreria.setIdLibreria(rs.getInt("idLibreria"));
                        libreria.setIdLibro(rs.getInt("idLibro"));
                        libreria.setTitoloLibro(rs.getString("TitoloLibro"));
                        libreria.setGenereLibro(rs.getString("genereLibro"));
                        libreria.setAutoreLibro(rs.getString("autoreLibro"));
                        libreria.setCopertinaLibro(rs.getString("copertinaLibro"));
                        
                      
                        listaLibrerie.add(libreria);
                    }
                    return listaLibrerie;
                }
            });
        } catch (Exception e) {
            // Gestione dell'errore
            return new ArrayList<>();
        }
    }
  
    /**
     * Ritorna una lista di oggetti Challange contenenti i dati presenti
     * nella tabella "nomeChallange" del database.
     * La lista contiene oggetti di tipo Challange, ciascuno dei quali
     * rappresenta una partita di una challenge con i propri attributi:
     * idRecord, dataInizio, nomePartecipante e punteggio.
     * I dati sono ordinati in base al punteggio decrescente.
     * Se si verifica un errore durante l'esecuzione della query, viene
     * ritornata una lista vuota.
     * @param nomeChallange nome della challenge
     * @return lista di oggetti Challange
     */
    public ArrayList<Challange> ritornaChallange(String nomeChallange) { 
    try {
        String query = "SELECT * FROM " + nomeChallange + " ORDER BY punteggio DESC";
        return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Challange>>() {
            @Override
            public ArrayList<Challange> extractData(ResultSet rs) throws SQLException {
                ArrayList<Challange> listaChallange = new ArrayList<>();
                while (rs.next()) {
                    Challange challange = new Challange();
                    challange.setIdRecord(rs.getInt("id_record"));
                    challange.setDataInizio(rs.getDate("data_inizio").toLocalDate());
                    challange.setNomePartecipante(rs.getString("nome_partecipante"));
                    challange.setPunteggio(rs.getInt("punteggio"));
                    listaChallange.add(challange);
                }
                return listaChallange;
            }
        });
    } catch (Exception e) {
        // Gestione dell'errore
        return new ArrayList<>();
    }
}


    /**
     * Ritorna una lista di oggetti Storico contenenti i dati presenti
     * nella tabella "storico_challanger" del database.
     * La lista contiene oggetti di tipo Storico, ciascuno dei quali
     * rappresenta una partita con i propri attributi: id, data, idChallange,
     * nomeChallange, nomeVincitore, punti.
     * Se si verifica un errore durante l'esecuzione della query, viene
     * ritornata una lista vuota.
     * @return lista di oggetti Storico
     */
    public ArrayList<Storico> ritornaStorico() {
        try {
            String query = "SELECT * FROM storico_challange";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Storico>>() {
                @Override
                public ArrayList<Storico> extractData(ResultSet rs) throws SQLException {
                    ArrayList<Storico> listaStorico = new ArrayList<>();
                    while (rs.next()) {
                        Storico storico = new Storico();
                        storico.setIdChallange(rs.getInt("id_challange"));
                        storico.setData(rs.getDate("data").toLocalDate());
                        storico.setDataFine(rs.getDate("data_fine").toLocalDate());
                        storico.setNomeChallange(rs.getString("nome_challange"));
                        storico.setCondizione(rs.getString("condizione"));
                        storico.setNomeVincitore(rs.getString("nome_vincitore"));
                        storico.setPunti(rs.getInt("punti"));
                        storico.setStato(rs.getInt("stato"));
                        
                        
                      
                        listaStorico.add(storico);
                    }
                    return listaStorico;
                }
            });
        } catch (Exception e) {
            // Gestione dell'errore
            return new ArrayList<>();
        }
    }
      
    /**
     * Crea una nuova tabella chiamata "libri" nel database 
     * se essa non esiste gia. La tabella ha 5 colonne: 
     * id, titolo, genere, autore, copertina.
     * 
     * @author Alessandro Bugatti
     */
    public void creaNuovaTabLibri() {
  

    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'libri'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE libri (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "titolo VARCHAR(50)," +
                "genere VARCHAR(50)," +
                "autore VARCHAR(50)," +
                "letture INT," +
                "copertina VARCHAR(1000)" +
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

    /**
     * Crea una nuova tabella chiamata "moderatore" nel database 
     * se essa non esiste gia. La tabella ha 6 colonne: 
     * id, user_mod, titolo_mod, genere_mod, autore_mod, copertina_mod.
     * La tabella e' utilizzata per contenere le richieste di moderazione dei libri.
     * 
     * @author Alessandro Bugatti
     */
    public void creaNuovaTabModeratore() {
  

    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'moderatore'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE moderatore (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_mod VARCHAR(50) NOT NULL UNIQUE," +
                "titolo_mod VARCHAR(50)," +
                "genere_mod VARCHAR(50)," +
                "autore_mod VARCHAR(50)," +
                "copertina_mod VARCHAR(1000)" +
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

    /**
     * Crea una nuova tabella chiamata "nomeLibreria" nel database 
     * se essa non esiste gia. La tabella ha 6 colonne: 
     * idLibreria, idLibro, TitoloLibro, genereLibro, autoreLibro, copertinaLibro.
     * La tabella e' utilizzata per contenere i libri presenti nella libreria dell'utente.
     * 
     * @param nomeLibreria il nome della libreria da creare
     * @author Alessandro Bugatti
     */
    public void creaLibreriaUser(String nomeLibreria) {
  
        
    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE '" + nomeLibreria + "'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE " + nomeLibreria + " (" +
                "idLibreria INT AUTO_INCREMENT PRIMARY KEY," +
                "idLibro INT," +
                "TitoloLibro VARCHAR(50)," +
                "genereLibro VARCHAR(50)," +
                "autoreLibro VARCHAR(50)," +
                "copertinaLibro VARCHAR(1000)" +
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
    /**
     * Crea una nuova tabella chiamata "nomeChallanger" nel database 
     * se essa non esiste gia. La tabella ha 4 colonne: 
     * id_record, data_inizio, nome_partecipante, punteggio.
     * La tabella e' utilizzata per contenere i record 
     * delle partecipazioni dell'utente alle sfide.
     * 
     * @param nomeChallanger il nome della tabella da creare
     * @author Alessandro Bugatti
     */
    public void creaTabellaChallange(String nomeChallange) {
  
        
    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE '" + nomeChallange + "'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE " + nomeChallange + " (" +
                "id_record INT AUTO_INCREMENT PRIMARY KEY," +
                "data_inizio DATE," +
                "nome_partecipante VARCHAR(50)," +
                "punteggio INT" +
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

    /**
     * Crea una nuova tabella chiamata "storico_challange" nel database 
     * se essa non esiste gia. La tabella ha 8 colonne: 
     * id_challange, data, data_fine, nome_challange, condizione, nome_vincitore, punti, stato.
     * La tabella e' utilizzata per contenere le informazioni relative alle sfide create 
     * dagli utenti.
     * 
     * @author Alessandro Bugatti
     */
    public void creaStoricoChallanger() {
  

    	// Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'storico_challange'";
     	
     	
        // Query SQL per creare una nuova tabella
        String query = "CREATE TABLE storico_challange (" +
                "id_challange INT AUTO_INCREMENT PRIMARY KEY," +
                "data DATE," +
                "data_fine DATE," +
                "nome_challange VARCHAR(50)," +
                "condizione VARCHAR(50)," +
                "nome_vincitore VARCHAR(50)," +
                "punti INT," +
                "stato INT" +
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

    
    
    /**
     * Aggiunge un libro alla libreria dell'utente specificato.
     * La funzione verifica se il libro è già presente nella libreria e, se non lo è,
     * lo aggiunge alla tabella "nomeLibreria".
     * @param nomeLibreria il nome della tabella che rappresenta la libreria dell'utente
     * @param idLibro l'ID del libro da aggiungere
     */
    public void aggiungiLibroAllaLibreria(String nomeLibreria, int idLibro) {
        // Verifica se il libro è già presente nella libreria
    String checkQuery = "SELECT COUNT(*) FROM " + nomeLibreria + " WHERE idLibro = ?";
    Integer count = jdbcTemplateObject.queryForObject(checkQuery, Integer.class, idLibro);

    if (count != null && count > 0) {
        System.out.println("Il libro è già presente nella libreria.");
        return; 
    }
        String query = "SELECT * FROM libri WHERE id = ?";
        Prodotto libro = jdbcTemplateObject.queryForObject(query, new BeanPropertyRowMapper<>(Prodotto.class), idLibro);
        String titoloLibro = libro.getTitolo();
        String genereLibro = libro.getGenere();
        String autoreLibro = libro.getAutore();
       
        String copertinaLibro = libro.getCopertina();
        String queryInsert = "INSERT INTO " + nomeLibreria + " (idLibro, TitoloLibro, genereLibro, autoreLibro, copertinaLibro) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(queryInsert, idLibro, titoloLibro, genereLibro, autoreLibro, copertinaLibro);
        
    }

    



    /**
     * Ritorna il libro con l'ID specificato.
     * La funzione esegue una query per selezionare il libro
     * con l'ID specificato e lo ritorna come oggetto Prodotto.
     * @param idLibro l'ID del libro da selezionare
     * @return l'oggetto Prodotto corrispondente al libro selezionato
     */
    public Prodotto getLibroById(int idLibro) {
        String query = "SELECT * FROM libri WHERE id = ?";
        return jdbcTemplateObject.queryForObject(query, new BeanPropertyRowMapper<>(Prodotto.class), idLibro);
    }

    
     
    /**
     * Ritorna il libro con l'ID specificato dal database.
     * La funzione esegue una query per selezionare il libro
     * con l'ID specificato e lo ritorna come oggetto Moderatore.
     * @param idLibro l'ID del libro da selezionare
     * @return l'oggetto Moderatore corrispondente al libro selezionato
     */
    public Moderatore getLibroModeratoreById(int idLibro) {
        String query = "SELECT * FROM moderatore WHERE id = ?";
        return jdbcTemplateObject.queryForObject(query, new BeanPropertyRowMapper<>(Moderatore.class), idLibro);
    }

    
    /**
     * Rimuove un libro dalla libreria specificata.
     * La funzione esegue una query per eliminare il libro identificato da idLibro
     * dalla tabella corrispondente al nome della libreria fornito.
     * 
     * @param nomeLibreria il nome della tabella che rappresenta la libreria da cui rimuovere il libro
     * @param idLibro l'ID del libro da rimuovere
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     */

    /**
     * Rimuove un libro dalla libreria specificata.
     * La funzione esegue una query per eliminare il libro identificato da idLibro
     * dalla tabella corrispondente al nome della libreria fornito.
     * L'operazione di eliminazione viene eseguita utilizzando un JDBC Template.
     * Se si verifica un errore durante l'esecuzione della query, viene
     * sollevata un'eccezione di tipo SQLException.
     * @param nomeLibreria il nome della tabella che rappresenta la libreria da cui rimuovere il libro
     * @param idLibro l'ID del libro da rimuovere
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     */
    public void rimuoviLibroDaLibreria(String nomeLibreria, int idLibro) throws SQLException {
        String sql = "DELETE FROM " + nomeLibreria + " WHERE idLibro = ?";
        jdbcTemplateObject.update(sql, idLibro);
    }

    /**
     * Elimina l'utente con l'ID specificato e la sua relativa libreria.
     * 
     * @param userId l'ID dell'utente da eliminare
     * @param nomeLibreria il nome della tabella della libreria dell'utente da eliminare
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     * @author Alessandro Bugatti
     */
     public void eliminaUser(String nomeLibreria) throws SQLException {
        
        // Elimina l'utente con l'ID specificato
        String sql = "DELETE FROM users WHERE nomelibreria = ?";
        jdbcTemplateObject.update(sql, nomeLibreria);
        // Elimina la tabella con il nome specificato
        String dropTableSql = "DROP TABLE " + nomeLibreria;

        jdbcTemplateObject.execute(dropTableSql);
    }
    
    
    /**
     * Crea una nuova tabella chiamata 'users' nel database se non esiste già.
     * La tabella include le colonne: userid, username, email, password e punteggio.
     * Se la tabella esiste già, non viene eseguita alcuna azione.
     * In caso di errore durante l'esecuzione della query, l'eccezione verrà stampata.
     */

    public void creaNuovaTabUsers() {
        // Query SQL per verificare se la tabella esiste
        String checkTableQuery = "SHOW TABLES LIKE 'users'";

        // Query SQL per creare una nuova tabella
        String createTableQuery = "CREATE TABLE users (" +
                                "userid INT AUTO_INCREMENT PRIMARY KEY, " +
                                "username VARCHAR(50) NOT NULL UNIQUE, " +
                                "email VARCHAR(50) NOT NULL UNIQUE, " +
                                "password VARCHAR(50) NOT NULL, " +
                                "nomelibreria VARCHAR(50) NOT NULL, " +
                                "punteggio INT DEFAULT 0" +
                                ")";

        try {
            // Esegui la query di controllo
            List<Map<String, Object>> tables = jdbcTemplateObject.queryForList(checkTableQuery);

            // Se la tabella non esiste, crea la tabella
            if (tables.isEmpty()) {
                jdbcTemplateObject.execute(createTableQuery);
            }
        } catch (Exception e) {
            System.err.println("Errore nella creazione della tabella users: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * Ritorna la lista di tutti gli utenti presenti nella tabella 'users' del database.
     * La lista contiene oggetti di tipo User, ciascuno dei quali rappresenta un utente
     * con i propri attributi: userid, username, email, password, nomelibreria e punteggio.
     * Se si verifica un errore durante l'esecuzione della query, viene ritornata una lista vuota.
     * @return lista di utenti
     */
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
                        user.setNomeLibreria(rs.getString("nomelibreria"));
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

    /* Metodo per creare il nome della libreria derivante dall'username */  
    /**
     * Crea il nome della libreria derivante dall'username.
     * Il nome della libreria viene creato sostituendo tutti i caratteri non alfanumerici
     * dell'username con la stringa vuota e convertendo il risultato in minuscolo.
     * @param username username dell'utente
     * @return nome della libreria
     */
    public String creaLibreriaName(String username) {
        String pulita = username.replaceAll("[^a-zA-Z0-9]", "");
        String nameLibreria = pulita.toLowerCase();
        return "libreria_" + nameLibreria;    
    }

    /**
     * Crea il nome della challenge derivante dal nome della challenge.
     * Il nome della challenge viene creato sostituendo tutti i caratteri non alfanumerici
     * del nome della challenge con la stringa vuota e convertendo il risultato in minuscolo.
     * @param challangeName nome della challenge
     * @return nome della challenge
     */
    public String creaChallangeName(String challangeName) {
        String pulita = challangeName.replaceAll("[^a-zA-Z0-9]", "");
        String nameChallange = pulita.toLowerCase();
        return "challenge_" + nameChallange;    
    }

    /* Metodo per creare un user */
    /**
     * Crea un nuovo utente nel database.
     * @param user oggetto User che rappresenta l'utente da creare
     * @param username username dell'utente
     * @param email email dell'utente
     * @param password password dell'utente
     * @param punteggio punteggio dell'utente
     */   
    public void createUser(User user, String username, String email, String password, int punteggio) {
        
        String query = "INSERT INTO users (username, email, password, nomelibreria, punteggio) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getNomeLibreria(), user.getPunteggio());
    }

    /**
     * Inserisce un nuovo partecipante alla challenge.
     * La funzione riceve come parametri il nome della challenge, l'oggetto Challange che rappresenta il partecipante, la data di inizio, il nome del partecipante e il punteggio iniziale.
     * La funzione esegue una query di inserimento per aggiungere il nuovo partecipante alla tabella della challenge.
     * @param nomeChallange il nome della challenge
     * @param challange l'oggetto Challange che rappresenta il partecipante
     * @param dataInizio la data di inizio della challenge
     * @param nomePartecipante il nome del partecipante
     * @param punteggio il punteggio iniziale del partecipante
     */
    public void insertUserCallange(String nomeChallange, Challange challange, LocalDate dataInizio,  String nomePartecipante, int punteggio) {
        
        String query = "INSERT INTO " + nomeChallange + " (data_inizio, nome_partecipante, punteggio) VALUES (?, ?, ?)";
        jdbcTemplateObject.update(query, challange.getDataInizio(), challange.getNomePartecipante(), challange.getPunteggio());
    }

    /**
     * Inserisce un nuovo storico della challenge.
     * La funzione riceve come parametri l'oggetto Storico che rappresenta il nuovo storico, la data di inizio, la data di fine, il nome della challenge, la condizione, il nome del vincitore, il punteggio e lo stato.
     * La funzione esegue una query di inserimento per aggiungere il nuovo storico alla tabella storico_challange.
     * @param storico l'oggetto Storico che rappresenta il nuovo storico
     * @param data la data di inizio della challenge
     * @param dataFine la data di fine della challenge
     * @param nomeChallange il nome della challenge
     * @param condizione la condizione della challenge
     * @param nomeVincitore il nome del vincitore
     * @param punteggio il punteggio del vincitore
     * @param stato lo stato della challenge
     */
     public void insertStoricoCallange(Storico storico, LocalDate data, LocalDate dataFine,  String nomeChallange, String condizione, String nomeVincitore, int punteggio, int stato) {
        
        String query = "INSERT INTO storico_challange (data, data_fine, nome_challange, condizione, nome_vincitore, punti, stato) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(query, storico.getData(), storico.getDataFine(), storico.getNomeChallange(), storico.getCondizione(), storico.getNomeVincitore(), storico.getPunti(), storico.getStato());
    }

    /**
     * Verifica se l'utente con il nome specificato partecipa alla challenge con il nome specificato.
     * La funzione esegue una query di tipo SELECT per contare il numero di righe con il nome partecipante specificato
     * nella tabella della challenge.
     * Se il numero di righe è maggiore di zero, la funzione restituisce true, altrimenti restituisce false.
     * Se si verifica un errore durante l'esecuzione della query, la funzione restituisce false per sicurezza.
     * @param nomeChallange il nome della challenge
     * @param nomeUtente il nome dell'utente
     * @return true se l'utente partecipa, false altrimenti
     */
    public boolean isUserPartecipante(String nomeChallange, String nomeUtente) {
    // Query che verifica se esiste una riga con quel nome partecipante nella tabella della challenge
    String query = "SELECT COUNT(*) FROM `" + nomeChallange + "` WHERE nome_partecipante = ?";

    try {
        Integer count = jdbcTemplateObject.queryForObject(query, new Object[]{nomeUtente}, Integer.class);
        return count != null && count > 0;
    } catch (Exception e) {
        e.printStackTrace();
        // Se errore, supponiamo che l'utente non partecipi per sicurezza
        return false;
    }
}

    /**
     * Aggiorna lo stato di una challenge.
     * La funzione riceve come parametri l'id della challenge, il nome del vincitore, il punteggio e lo stato.
     * La funzione esegue una query di UPDATE per aggiornare lo stato della challenge.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param idChallange l'id della challenge
     * @param NomeVincitore il nome del vincitore
     * @param punti il punteggio del vincitore
     * @param stato lo stato della challenge
     * @return il numero di righe aggiornate
     */
    public int updateStatoStorico(int idChallange, String NomeVincitore, int punti, int stato) {
        try {
            String query = "UPDATE storico_challange SET nome_vincitore = ?, punti = ?, stato = ? WHERE id_challange = ?";
            return jdbcTemplateObject.update(query, NomeVincitore, punti, stato, idChallange);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }

    /**
     * Aggiorna le letture di un libro.
     * La funzione riceve come parametri l'id del libro e il nuovo numero di letture.
     * La funzione esegue una query di UPDATE per aggiornare le letture del libro.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param idLibro l'id del libro
     * @param letture il nuovo numero di letture
     * @return il numero di righe aggiornate
     */
    public int updateLettureLibro(int idLibro, int letture) {
        try {
            String query = "UPDATE libri SET letture = ? WHERE id = ?";
            return jdbcTemplateObject.update(query, letture, idLibro);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }

    /**
     * Aggiorna il punteggio dell'utente con username specificato.
     * La funzione riceve come parametri il nome dell'utente e il nuovo punteggio.
     * La funzione esegue una query di UPDATE per aggiornare il punteggio dell'utente.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param username il nome dell'utente
     * @param punteggio il nuovo punteggio dell'utente
     * @return il numero di righe aggiornate
     */
    public int updatePunteggioUser(String username, int punteggio) {
        try {
            String query = "UPDATE users SET punteggio = ? WHERE username = ?";
            return jdbcTemplateObject.update(query, punteggio, username);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }

    /**
     * Aggiorna il punteggio di un partecipante a una challenge.
     * La funzione riceve come parametri il nome della challenge, il nome del partecipante e il nuovo punteggio.
     * La funzione esegue una query di UPDATE per aggiornare il punteggio del partecipante.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param nomeChallange il nome della challenge
     * @param nomePartecipante il nome del partecipante
     * @param punteggio il nuovo punteggio del partecipante
     * @return il numero di righe aggiornate
     */
    public int updatePunteggioChallange(String nomeChallange, String nomePartecipante, int punteggio) {
        try {
            String query = "UPDATE " + nomeChallange + " SET punteggio = ? WHERE nome_partecipante = ?";
            return jdbcTemplateObject.update(query, punteggio, nomePartecipante);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }

    /**
     * Elimina il libro con l'id specificato.
     * La funzione esegue una query di DELETE per eliminare il libro.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param idLibro l'id del libro da eliminare
     * @return il numero di righe eliminate
     */
    public int eliminaModLibro(int idLibro) {
        try {
            String query = "DELETE FROM libri WHERE id = ?";
            return jdbcTemplateObject.update(query, idLibro);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }

    /**
     * Elimina il libro con l'id specificato dalla tabella moderatore.
     * La funzione esegue una query di DELETE per eliminare il libro.
     * Se la query fallisce, la funzione restituisce 0 per sicurezza.
     * @param idLibro l'id del libro da eliminare
     * @return il numero di righe eliminate
     */
    public int eliminaLibroDaTabMod(int idLibro) {
        try {
            String query = "DELETE FROM moderatore WHERE id = ?";
            return jdbcTemplateObject.update(query, idLibro);
        } catch (Exception e) {
            // Gestione dell'errore
            return 0;
        }
    }
    
    /**
     * Crea un nuovo libro nel database.
     * La funzione inserisce un nuovo record nella tabella "libri"
     * utilizzando i dati forniti dall'oggetto Prodotto.
     * 
     * @param libro l'oggetto Prodotto contenente i dati del libro da inserire
     */

    public void creaLibro(Prodotto libro) {
        
        String query = "INSERT INTO libri (titolo, genere, autore, letture, copertina) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(query, libro.getTitolo(), libro.getGenere(), libro.getAutore(), libro.getLetture(), libro.getCopertina());
    }

    /**
     * Crea un nuovo libro nel database moderatore.
     * La funzione inserisce un nuovo record nella tabella "moderatore"
     * utilizzando i dati forniti dall'oggetto Moderatore.
     * 
     * @param libro l'oggetto Moderatore contenente i dati del libro da inserire
     */
     public void creaLibroModeratore(Moderatore libro) {
        
        String query = "INSERT INTO moderatore (user_mod, titolo_mod, genere_mod, autore_mod, copertina_mod) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(query, libro.getUserMod(), libro.getTitoloMod(), libro.getGenereMod(), libro.getAutoreMod(), libro.getCopertinaMod());
    }

    /**
     * Elimina una challenge dal database.
     * La funzione rimuove il record associato al nome della challenge specificata
     * dalla tabella "storico_challange" e elimina la tabella con il nome della challenge.
     * 
     * @param nomeChallange il nome della challenge da eliminare
     * @throws SQLException se si verifica un errore durante l'esecuzione delle query
     */

      public void adminEliminaChallange(String nomeChallange) throws SQLException {
        
        // Elimina l'utente con l'ID specificato
        String sql = "DELETE FROM storico_challange WHERE nome_challange = ?";
        jdbcTemplateObject.update(sql, nomeChallange);
        // Elimina la tabella con il nome specificato
        String dropTableSql = "DROP TABLE " + nomeChallange;

        jdbcTemplateObject.execute(dropTableSql);
    }

    // Metodo per eseguire query DDL  
    /**
     * Esegue una query DDL (Data Definition Language) sul database.
     * La query e' eseguita tramite l'oggetto JdbcTemplate.
     * Se si verifica un errore durante l'esecuzione della query, viene richiamata la gestione dell'errore.
     * @param query la query DDL da eseguire
     */
    public void executeDDLQuery(String query) {
        try {
            jdbcTemplateObject.execute(query);
        } catch (Exception e) {
            // Gestione dell'errore
        }
    }

}

