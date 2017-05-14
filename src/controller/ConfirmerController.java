/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import services.Livraison;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class ConfirmerController implements Initializable,Livraison {

    @FXML
    private JFXButton confirmer;
    private int userid = 1;
    ArrayList al = new ArrayList();
 Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;

    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
     
    }    
    
      public ArrayList setTexte(ArrayList list){
        for(Iterator it=list.iterator();
                it.hasNext();){
             al.add(it.next());
             System.out.println(al.size());
             
        } 
 System.out.println(al.get(0));
        return list;
    }

    @FXML
    private void ValiderLiv(ActionEvent event) throws IOException {
         
         int id_comm = Integer.valueOf(al.get(0).toString());
        String adr = al.get(1).toString();
        String tel = al.get(5).toString();
        String cp = al.get(3).toString();
        String del = al.get(4).toString();
        String gouv = al.get(2).toString();
        String paie = al.get(7).toString();
       
        String methode = al.get(6).toString();
        
        ValidLivraison(userid, id_comm, adr, tel,cp,del,gouv,paie,methode);
            
        System.out.println(id_comm);
    
           ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ShowInvoice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @Override
    public void ValidLivraison(int userid, int id_comm, String adr, String tel, String cp, String del, String gouv, String paie, String methode) {
        int comtot =0;
                int etat = 0;     
        try {
            // create a mysql database connection
      
                Statement stmt = con.createStatement();
              
            rs = stmt.executeQuery("select commande_tot from commande where id_commande = "+id_comm );
            while (rs.next()) {
               
                comtot = rs.getInt("commande_tot");
             
            }
                     String sql = ("INSERT INTO `livraison` (`id_user`, `prixtotal`, `adresse _liv`, `telephone_user`, `codepostale_user`, "
                    + "`delegation_user`, `gouvernorat_user`, `paiement`, `etat_paie`, `meth_livraison`, `id_commande`)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            if(methode.equals("aramex")){
                comtot = comtot +8000;
            }
             if(methode.equals("poste")){
                comtot = comtot +10000;
            }
            pstmt.setInt(1, userid);
            pstmt.setInt(2, comtot);
            pstmt.setString(3, adr);
            pstmt.setString(4, tel);
            pstmt.setString(5, cp);
            pstmt.setString(6, del);
            pstmt.setString(7, gouv);
            pstmt.setString(8, paie);
            pstmt.setInt(9, etat);
            pstmt.setString(10, methode);
            pstmt.setInt(11, id_comm);


            pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void afficheDataUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
