/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import services.Livraison;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert;
import util.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class ShippingOrderController implements Initializable,Livraison {

    @FXML
    private JFXTextArea adr;
  
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField gouv;
    @FXML
    private JFXTextField dele;
    @FXML
    private JFXCheckBox info;
    @FXML
    private JFXTextField codepo;
    // private ObservableList<Produit> data;
 ArrayList al = new ArrayList();
   Connection con = DatabaseConnection.getInstance().getConnection();
              ResultSet rs = null;
 
private int id_user = UserConnectionController.user.getId();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(info.isSelected()){
           
        }
        loadDataFromDatabase();
        // TODO
    }   
    
    private void loadDataFromDatabase() {
       
        afficheDataUser();

    }
       @FXML
    public void EtapeDeux(ActionEvent event) throws IOException {
    
            String adresse = adr.getText();
          
            String num_tel = tel.getText();
            String gouver = gouv.getText();
            String codeP = codepo.getText();
            String delega = dele.getText();
            if(num_tel.isEmpty() && adresse.isEmpty() && gouver.isEmpty() && codeP.isEmpty() && delega.isEmpty() ){
                         Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Ooops, there was an error!");

alert.showAndWait(); 
            }else{
         al.add(adresse);
            al.add(gouver);
             al.add(codeP);
              al.add(delega);
               al.add(num_tel);
               ((Node) (event.getSource())).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/MethodeLivraison.fxml"));
                    loader.load();
                    MethodeLivraisonController display = loader.getController();
                    display.setTexte(al);
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();  }          
    }

    @FXML
    private void ViderChamps(ActionEvent event) {
      
        
         if(info.isSelected() == false){
               
                adr.setText("");
            
                tel.setText("");
                gouv.setText("");
                dele.setText("");
                codepo.setText("");
             
        }
         else{
                 loadDataFromDatabase();
         }
    }
     public ArrayList setTexte(ArrayList list){
        for(Iterator it=list.iterator();
                it.hasNext();){
               al.add(it.next());
        } 
        return list;
    }

    @Override
    public void ValidLivraison(int userid, int id_comm, String adr, String tel, String cp, String del, String gouv, String paie, String methode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficheDataUser() {
         try {
            
               
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM utilisateur where id =" + id_user);
            //ss
            while (rs.next()) {

              
                String adr_user = rs.getString("adr_user");
           
                String delegation = rs.getString("delegation_user");
                String codepostale = rs.getString("codepostale_user");
                String telephone = rs.getString("telephone_user");
                String gouvernorat = rs.getString("gouvernorat_user");

                adr.setText(adr_user);
                
                tel.setText(telephone);
                gouv.setText(gouvernorat);
                dele.setText(delegation);
                codepo.setText(codepostale);
            }
           
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
}
