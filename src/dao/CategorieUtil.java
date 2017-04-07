/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Categorie;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jamel_pc
 */
public class CategorieUtil extends UtilInterface {

    public CategorieUtil() {
        initConnection();
    }

    @Override
    public boolean ajouterObject(Object p) {
        Categorie categorie = (Categorie) p;
        int i = 0;
        try {
            String req = "INSERT INTO `categorie` (`nomCategorie`) "
                    + "VALUES (" + categorie.getNomCategorie() + ")";

            i = statement.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;

    }

    @Override
    public boolean modifierObject(Object p) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    @Override
    public boolean supprimerObject(Integer i) {
        try {
            String req = "DELETE FROM `categorie` WHERE " + i;
            i = statement.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public void afficherObject() {
        try {
            String req3 = "select * from categorie";
            ResultSet res = statement.executeQuery(req3);
            while (res.next()) {
                String nomCategorie = res.getString("nomCategorie");
                System.out.format("%s \n", nomCategorie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Integer getIdFromNom(String nom){
        Integer id = null ;
        try {

            String req ="select `id` from `categorie` where `nomCategorie`=\""+nom+"\"";
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next())
                id= resultSet.getInt("Id");
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
       return id;
    }
     public ObservableList<String> listerCategorie() {
         ObservableList<String> categories =  FXCollections.observableArrayList();
        try {
            String req3 = "select * from categorie";
            ResultSet res = statement.executeQuery(req3);
            while (res.next()) {
                
                categories.add(res.getString("nomCategorie"));
              
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
     

}
