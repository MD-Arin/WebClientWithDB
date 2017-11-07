package main.java.Model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static String dbUsername;
    private static String dbPassword;
    private static String dbName;

    private static final Logger DBLOG = Logger.getLogger(Database.class.getName());

    private static Database instance = null;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            //Get Database Properties from db.properties configuration file
            Properties dbProps = new Properties();
            try (
                    InputStream dbInput = new FileInputStream("db.properties");) {

                //Load the database properties;
                dbProps.load(dbInput);

                //get values from db.properties file and set them to local variables
                dbName = dbProps.getProperty("database");
                dbUsername = dbProps.getProperty("username");
                dbPassword = dbProps.getProperty("password");

                Class.forName("com.mysql.jdbc.Driver");
                DBLOG.log(Level.INFO, "Props Loaded Successfully");

            } catch (Exception e) {
                DBLOG.log(Level.SEVERE, e.getMessage(), e);
            }
            instance = new Database();
            return instance;
        }
        return instance;
    }

    public final Connection getConnection() {
        try {
            DBLOG.log(Level.INFO, "Connected To The Database");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUsername, dbPassword);
        } catch (SQLException ex) {
            System.out.println("Not Connected");
            DBLOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public final boolean closeConnection() {
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (Exception ex) {
            DBLOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return true;

    }

}
