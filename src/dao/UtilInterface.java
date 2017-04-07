/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jamel_pc
 */
public abstract class UtilInterface {

    public abstract boolean ajouterObject(Object p);

    public abstract boolean modifierObject(Object p);

    public abstract boolean supprimerObject(Integer i);

    public abstract void afficherObject();
    Connection conn;
    Statement statement;

    public void initConnection() {
        conn = DatabaseConnection.getInstance().getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UtilInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
