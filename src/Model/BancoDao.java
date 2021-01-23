
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * BancoDao.java
 */

package Model;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo dos Santos de lima
 *
 */

public class BancoDao {
    
    /** Creates a new instance of BancoDao */
    public BancoDao() {
        this("Remissao");                                                       // Nome da Pasta do Banco de Dados
    }
    
    public BancoDao(String BoletoName) {
        this.dbName = BoletoName;
        setDBSystemDir();
        dbProperties = loadDBProperties();
        String driverName = dbProperties.getProperty("derby.driver"); 
        loadDatabaseDriver(driverName);
        if(!dbExists()) {
            createDatabase();
        }
    }
    
    private boolean dbExists() {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }
    
    private void setDBSystemDir() {
    // decide on the db system directory
    // String userHomeDir = System.getProperty("user.home", ".");               // Neste Caso O Systema Decide Diretorio
       String userHomeDir = System.getProperty(".","C:/Amarildo/");             // Caminho Do Banco
       String systemDir = userHomeDir + "Remissao/BancoDeDados";
       System.setProperty("derby.system.home", systemDir);
    // create the db system directory
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }
    
    private void loadDatabaseDriver(String driverName) {
        // load Derby driver
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private Properties loadDBProperties() {
        InputStream dbPropInputStream = null;
        dbPropInputStream = BancoDao.class.getResourceAsStream("Configuration.properties");
        dbProperties = new Properties();
        try {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dbProperties;
    }
    
    private boolean createDatabase() {
        boolean bCreated = false;
        Connection dbConnection = null;
        String dbUrl = getDatabaseUrl();
        dbProperties.put("create", "true");
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            bCreated = createTables(dbConnection);
        } catch (SQLException ex) {
        // Inserir Mensagens de Erro
        }
        dbProperties.remove("create");
        return bCreated;
    }

   
    private boolean createTables(Connection dbConnection) {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(strCreateTableRemissao);
            statement.execute(strCreateTableCodigo);
            bCreatedTables = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bCreatedTables;
    }

    public void executeSQL_BdClear(String instrucao) {
       try {
        statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }
    
    public void executeSQL_BdAppend(String instrucao) {
       try {
       // statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement = dbConnection.createStatement();
        statement.executeUpdate(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }

    public void executeSQL_BdNavigator(String instrucao) {
       try {
        statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }
    
    public boolean connect() {
        String dbUrl = getDatabaseUrl();
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
           // stmtSaveNewRecord = dbConnection.prepareStatement(strSaveAddress, Statement.RETURN_GENERATED_KEYS);
            isConnected = dbConnection != null;
        } catch (SQLException ex) {
            isConnected = false;
        }
        return isConnected;
    }
    
    private String getHomeDir() {
        return System.getProperty("user.home");
    }
    
    public void disconnect() {
        if(isConnected) {
            String dbUrl = getDatabaseUrl();
            dbProperties.put("shutdown", "true");
            try {
                DriverManager.getConnection(dbUrl, dbProperties);
            } catch (SQLException ex) {
            }
            isConnected = false;
        }
    }
    
    public String getDatabaseLocation() {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        return dbLocation;
    }
    
    public String getDatabaseUrl() {
        String dbUrl = dbProperties.getProperty("derby.url") + dbName;
        return dbUrl;
    }
    
// Começo Metodo Main Apenas Para teste de Conexão
 /*
    public static void main(String[] args) {
        BancoDao db = new BancoDao();
        System.out.println(db.getDatabaseLocation());
        System.out.println(db.getDatabaseUrl());
        db.connect();
        db.disconnect();
    }
  */
  //Final  Metodo Main Apenas Para teste de Conexão

    private Connection dbConnection;
    private Properties dbProperties;
    private boolean isConnected;
    private String dbName;
    private PreparedStatement stmtSaveNewRecord;
    public Statement statement;
    public ResultSet resultset;


    
    private static final String strCreateTableRemissao =
      "Create Table App.Remissao (" +
      " Linhas          VarChar(500)  " +
      ")";

    private static final String strCreateTableCodigo =
      "Create Table App.Codigo (" +
      " CodigoId        VarChar(34)  " +
      ")";
     
}
