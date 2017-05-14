/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entities.VenteFlash;
import entities.Produit;
import util.DatabaseConnection;
import services.IVenteFlashService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Salma
 */
public class VenteFlashService implements IVenteFlashService{

    Connection myConnection;
    Statement ste ;
        Statement ste1 ;


    public VenteFlashService() {
        myConnection = DatabaseConnection.getInstance()
                .getConnection();
        try {
            ste = myConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
             
        }
    }
    
    
    @Override
    public boolean ajouterVenteFlash(VenteFlash f) {
      int i= 0;
                 System.out.print(f.getDatedebut());
              java.sql.Timestamp datedebut = new java.sql.Timestamp(f.getDatedebut().getTime());
              java.sql.Timestamp datefin = new java.sql.Timestamp(f.getDatefin().getTime());

              
        try {
            String req="INSERT INTO `vente_flash` (`nom`,`description`,datedebut,finish,image)"
                    + "VALUES ('"+f.getNom()+"',' "+
                            f.getDescription()+"','"+datedebut+"','"+datefin+"','"+f.getImage()+"')";
            
         i= ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i>0)
            return true;
                                
             return  false;
    }

      
 
    public boolean modifierVenteFlash(VenteFlash f ,String nom,String description,Date debut,Date fin) {
int i= 1;
              java.sql.Timestamp datedebut = new java.sql.Timestamp(debut.getTime());
              java.sql.Timestamp datefin = new java.sql.Timestamp(fin.getTime());
              
        try {
            System.out.print(datedebut);
            String req="UPDATE  vente_flash SET  nom='"+nom+"', description='"+description+"',datedebut='"+datedebut+"', finish='"+datefin+"' WHERE  id="+f.getId();
            
           i= ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i>0)
            return true;
        
             return  false;    }


    @Override
    public boolean supprimerVenteFlash(VenteFlash f) {
               int i= 1;
        try {
            String req="DELETE From  vente_flash  WHERE  id="+f.getId();
            
           i= ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i>0)
            return true;
        
             return  false;        }
    
     
    public ObservableList<Produit> produitdslibres() {
 
    ObservableList<Produit> produits = FXCollections.observableArrayList();
             Produit p = null;
        try {
            String req3="select * from produit WHERE venteflash_id IS NULL";

            ResultSet res =  ste.executeQuery(req3);
            
            while (res.next()) {                
       p= new Produit(res.getInt("id"), res.getString("libelle"), res.getDouble("prixProduit"));
       p.setPrixflash(res.getDouble("pourcentagePromotion"));
        p.setImageName(res.getString("image_name"));

           produits.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }  return produits;

    }
     public ObservableList<Produit> taken(int f){
 
    ObservableList<Produit> produits = FXCollections.observableArrayList();
             Produit p = null;
        try {

            String req3="select* FROM produit WHERE venteflash_id="+f;

                        ResultSet res =  ste.executeQuery(req3);
                       
             while (res.next()) {                
              p= new Produit(res.getInt("id"),res.getDouble("pourcentagePromotion"),res.getString("libelle"));
              System.out.println(p.getPrixflash());
              p.setImageName(res.getString("image_name"));
                  produits.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
        }
     public void ajouterproduit(int f, Produit p){

        try {
             VenteFlash vente = this.getByid(f);


            String req="UPDATE  produit set venteflash_id="+f+" WHERE id="+p.getId();
                p.setFlash(vente);
                        int res =  ste.executeUpdate(req);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
       public void supprimerproduit( Produit p){
 
        try {

            String req="UPDATE produit set venteflash_id=null  and pourcentagePromotion=null where id="+p.getId();

                        int res =  ste.executeUpdate(req);
                        p.setFlash(null);
                        p.setPrixflash(0.0);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
    
    
    

    @Override
    public ObservableList<VenteFlash>  afficherVenteFlash() {
  
ObservableList<VenteFlash> ventes = FXCollections.observableArrayList();
             VenteFlash f = null;
            

             
        try {
            String req3="select * from vente_flash where datedebut > now()";
            ResultSet res =  ste.executeQuery(req3);
            
            while (res.next()) {                
       f = new VenteFlash(res.getInt("id"),res.getString("nom"), res.getString("description"), res.getTimestamp("datedebut"), res.getTimestamp("finish"),res.getString("image"));
           ventes.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventes;
        }
    
    public VenteFlash getByid(int id) throws SQLException{
                                String req2="select * from vente_flash WHERE id="+id;
                                ResultSet flash =  ste.executeQuery(req2);
                                    flash.next();
   VenteFlash vente =new VenteFlash(flash.getInt("id"),flash.getString("nom"),flash.getString("description"),flash.getTimestamp("datedebut"),flash.getTimestamp("finish"),flash.getString("image"));
        
                              return vente ;  

        
    }
    public String getImage(int id) throws SQLException{
        
         String req2="select image_name from vente_flash WHERE id="+id;
                                ResultSet flash =  ste.executeQuery(req2);
                                    flash.next();
   String image =flash.getString("image_name");
                              return image ;  
    }
    public boolean setFlash(Produit p,float prix ){
    
    int i= 1;
            
        try {
            
            String req="UPDATE  produit SET pourcentagePromotion="+prix+"  WHERE  id="+p.getId();
            
           i= ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(VenteFlashService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i>0)
            return true;
        
             return  false;    }    
    
}  
        
        
    
    

