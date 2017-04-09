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
    
    
    
     public Integer VerifUsername(String name,String password) {
        String req3="select *  from utilisateur";
        Integer idUser = null;
        //,`salt`
    try {
        ResultSet  res =  statement.executeQuery(req3);
           while (res.next()) {
               
              
               
               if ((name.equals(res.getString("username")))&&(password.equals(res.getString("password")))) {
                  
                   idUser= res.getInt("id");
               }
               
               
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    return idUser;
    
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
