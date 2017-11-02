package Model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private String dbUsername;
    private String dbPassword;
    private String dbName;
    
    private Logger dbLog = Logger.getLogger(Database.class.getName());

    public Database() {

        //Get Database Properties from db.properties configuration file
        Properties dbProps = new Properties();
        try (
                InputStream dbInput = new FileInputStream("db.properties");
            ) {

            //Load the database properties;
            dbProps.load(dbInput);
            
            //get values from db.properties file and set them to local variables
            this.dbName = dbProps.getProperty("database");
            this.dbUsername = dbProps.getProperty("username");
            this.dbPassword = dbProps.getProperty("password");

            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUsername, dbPassword);
            dbLog.log(Level.INFO, "Connected To The Database");
            return con;
        } catch (SQLException ex) {
            System.out.println("Not Connected");
            dbLog.log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
