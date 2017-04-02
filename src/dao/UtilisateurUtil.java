/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jamel_pc
 */
public class UtilisateurUtil extends UtilInterface{

    public UtilisateurUtil() {
    initConnection();
    }
    
    
    
     public boolean VerifUsername(String name,String password) {
        String req3="select `username` ,`password`  from utilisateur";
        boolean verif = false ;
        //,`salt`
    try {
        ResultSet  res =  stmt.executeQuery(req3);
           while (res.next()) {
               
              
               
               if ((name.equals(res.getString(1)))&&(password.equals(res.getString(2)))) {
                  
               verif = true;
               
               }else verif = false;
               
               
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    return verif;
    
    }
    @Override
    public boolean ajouterObject(Object p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierObject(Object p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerObject(Integer i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
