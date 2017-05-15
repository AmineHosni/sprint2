/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;

import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class FinPanierController implements Initializable {

    @FXML
    private JFXButton print;
//    int id_liv;
//     ArrayList al = new ArrayList();
//     ArrayList alp = new ArrayList();
//     Connection con = ConnextionSingleton.getInstance().getConnection();
//              ResultSet rs,rss = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PrintFac(ActionEvent event) throws IOException{
         ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AllProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
   
//                Statement stmt = con.createStatement();
//        
//               rs = stmt.executeQuery("Select max(id_liv) as cmid from livraison");
//                while (rs.next()) {
//              id_liv = rs.getInt("cmid");
//                
//                }
//            rs = stmt.executeQuery("SELECT nom_prod,qte_prod,prix_prod FROM commande,livraison,user,produit,details_commande where livraison.id_commande = commande.id_commande and details_commande.id_commande = commande.id_commande and details_commande.id_prod = produit.id_prod and livraison.id_user = user.id_user and livraison.id_liv = "+id_liv );
//                while (rs.next()) {
//               alp.add(rs.getString("nom_prod"));
//            alp.add(rs.getString("qte_prod"));
//            alp.add(rs.getString("prix_prod"));
//                
//                }
//           rss = stmt.executeQuery("SELECT * FROM commande,livraison,user where "
//                        + "livraison.id_commande = commande.id_commande and "
//                        + "livraison.id_user = user.id_user and "
//                        + "livraison.id_liv = "+id_liv );
//            while (rss.next()) {
//            al.add(rss.getString("nom_user"));
//            al.add(rss.getString("prenom_user"));
//            al.add(rss.getString("email_user"));
//            al.add(rss.getString("adresse _liv"));
//            al.add(rss.getString("gouvernorat_user"));
//            al.add(rss.getString("delegation_user"));
//            al.add(rss.getString("codepostale_user"));
//            al.add(rss.getString("telephone_user"));
//            al.add(rss.getString("commande_tot"));
//            al.add(rss.getString("prixtotal"));
//            al.add(rss.getString("date_livraison"));
//            al.add(rss.getString("meth_livraison"));
//            al.add(rss.getString("paiement"));
//            al.add("COMID"+rss.getString("id_commande"));  
//            }
            
           
    
          
           
}
        
   
//    public void impr()  {
//       Document document = new Document();
//        try{
//            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//            document.open();
//            Paragraph p1 = new Paragraph("Hello World!");
//            Paragraph p2 = new Paragraph("Examsmyantra.com is committed to provide you valuable information and tutorials on various technologies.");
//				document.add(p1);
//            document.add(p2);
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//        document.close();
//    }
    
     public ArrayList affiche(ArrayList list){
        for(Iterator it=list.iterator();
                it.hasNext();){
               
        } 
        return list;
    }
     
}
