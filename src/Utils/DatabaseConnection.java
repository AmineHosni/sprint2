/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author jamel_pc
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DatabaseConnection {
    

    
    
  static Connection myconnection;
  static String url = "jdbc:mysql://localhost:3306/pidev3";
  static String login = "root";
  static String pwd = "";
  static DatabaseConnection instance;

    private DatabaseConnection() {
         try {
       myconnection = DriverManager.getConnection(url,
                   login, pwd);
           System.out.println("connexion etablie");
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
    public static DatabaseConnection getInstance(){
        if(instance== null)
            instance = new DatabaseConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return myconnection;
    }
    
    
}
    
    
