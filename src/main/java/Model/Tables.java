/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arin
 */
public class Tables {

    private Logger tablesLog = Logger.getLogger(Tables.class.getName());
    private final String dropLengthTable = "DROP TABLE IF EXISTS lengths;";
    private final String createLengthTable = "CREATE TABLE IF NOT EXISTS lengths(LengthID INT NOT NULL "
            + "AUTO_INCREMENT, Miles INT, Kilometers DOUBLE, DateModified TIMESTAMP DEFAULT "
            + "CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY(LengthID));";

    private final String populateColumnMiles = "INSERT INTO `lengths`(Miles) VALUES(?);";
    Connection con = Database.getInstance().getConnection();
    
    public Tables() {
        
        //Drop Table if it already Exists
        try{
            Statement dropTable = con.createStatement();
            dropTable.execute(this.dropLengthTable);
            tablesLog.log(Level.INFO, "TABLE DROPPED SUCCESSFULLY");
        } catch (SQLException e){
            tablesLog.log(Level.SEVERE, e.getMessage(), e);
        }

        //Create Table
        try {
            Statement createTable = con.createStatement();
            createTable.execute(this.createLengthTable);
            tablesLog.log(Level.INFO, "TABLE CREATED SUCCESSFULLY");
        } catch (Exception e) {
            tablesLog.log(Level.SEVERE, e.getMessage(), e);
        }

        //Populate Table With Initial Values
        try {

            PreparedStatement populateMiles = con.prepareStatement(this.populateColumnMiles);
            for (int i = 10; i <= 100; i += 10) {

                populateMiles.setInt(1, i);
                populateMiles.execute();

                tablesLog.log(Level.INFO, "Field Miles Inserted Width Value: " + i);
            }

        } catch (Exception e) {
            tablesLog.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void updateKilometers(String val, int milesVal) {
        final String updateKilometers = "UPDATE lengths SET kilometers = ? WHERE Miles = ?;";

        try {
            PreparedStatement kilometers = con.prepareStatement(updateKilometers);
            kilometers.setInt(2, milesVal);
            kilometers.setDouble(1, Double.parseDouble(val));
            kilometers.execute();
            tablesLog.log(Level.INFO, "Kilometer Value Updated to: " + val);
            
        }catch(Exception e){
            tablesLog.log(Level.SEVERE, e.getMessage(), e);
        }

    }
}
