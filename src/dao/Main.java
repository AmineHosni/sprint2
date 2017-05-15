/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jamel_pc
 */
public class Main {
     Connection conn;
    Statement stmt;

    
    public void initConnection(){
        conn = DatabaseConnection.getInstance().getConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void VerifConnection(String nom ) {
        String req3="select `username` from utilisateur";
         
    try {
                      System.out.println(req3);

             ResultSet  res =  stmt.executeQuery(req3);
      
           while (res.next()) {
             
               
               if (nom.equals(res.getString("username"))) {
                   System.out.println("ok");
               }
           }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.initConnection();
       
        m.VerifConnection("jamel");
                
    }
    
}
