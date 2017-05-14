/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Facture;
import services.Commandes;
import com.jfoenix.controls.JFXButton;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import util.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class ShowInvoiceController implements Initializable,Commandes {

    @FXML
    private JFXButton confirmer;
    @FXML
    private TableView<Facture> validertab;
    private ObservableList<Facture> data;
    @FXML
    private TableColumn<Facture, Image> img;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> pu;
    @FXML
    private TableColumn<?, ?> qnt;
    @FXML
    private TableColumn<?, ?> tot;
    @FXML
    private Label totp;
    @FXML
    private Label pl;
    @FXML
    private Label pall;
                int id_comm =0;
              int prix_tot=0; 
              int liv_pa =0;
              int prix_ut = 0;
              int commande_tot= 0;
              String paie="";
              String Imagess= "";
              Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           setsCllTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
    }    
    public void setsCllTable() {

// Permet de récupérer l'image pour la colonne host pour la valeur whatever de la ligne courante.
img.setCellValueFactory(new Callback<CellDataFeatures<Facture, Image>, ObservableValue<Image>>() {
    public ObservableValue<Image> call(CellDataFeatures<Facture, Image> p) {
         Facture w = p.getValue();
           Image image = new Image("/Images/"+w.getImage(), 120,120, false, false);
        return  new SimpleObjectProperty<>(image);
         // Ou si pas de propriété sous la main :
         //Image image = ...
         //return new SimpleObjectProperty<>(image);    
       
     }
});
// Affiche l'image dans la cellule de la table host pour la ligne courante.
img.setCellFactory(new Callback<TableColumn<Facture, Image>, TableCell<Facture, Image>>(){
    public TableCell<Facture, Image> call(TableColumn<Facture, Image> p) {
        return new TableCell<Facture, Image>() {
               public void updateItem(Image image, boolean empty) {
                  setText(null);
                  setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                  
                   setMaxWidth(20);
                  
                  ImageView imageView = ((image == null || empty) ? null :  ImageViewBuilder.create().image(image).build());
                 
                  setGraphic(imageView);
              

               }
        };
    }
});
       // img.setCellValueFactory(new PropertyValueFactory<>("Image"));
     
       nom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        pu.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
        qnt.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
        tot.setCellValueFactory(new PropertyValueFactory<> ("prix_tot"));
        System.out.println("dkjshfkjsd "+new PropertyValueFactory<>("nom_produit"));
        

    }
          private void loadDataFromDatabase() {
         
        afficheinvoice();
        validertab.setItems(data);
      totp.setText(""+commande_tot);
            pl.setText(""+liv_pa);
      pall.setText(""+prix_tot);
      

    }

    @FXML
    private void ValiderLiv(ActionEvent event) throws IOException {
        if( paie.equals("paypal")){ 
            JSONObject obj = new JSONObject();
		obj.put("Prix",prix_tot);
		obj.put("Description",id_comm);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("../../../../../../../wamp64/www/json/file2.txt")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
                       ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Paypal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();          
              
        }
         if( paie.equals("cash")){
                ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FinPanier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
        }
    }

    @Override
    public void afficheinvoice() {
        try {
              
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("Select max(id_commande) as cmid from commande");
                while (rs.next()) {
              id_comm = rs.getInt("cmid");
                    
                
                }
                System.out.println("hedha"+id_comm);
            rs = stmt.executeQuery("SELECT Image,nom_prod,prix_prod,commande_tot,qte_prod,prixtotal,meth_livraison,paiement FROM commande,livraison,user,produit,details_commande where livraison.id_commande = commande.id_commande and details_commande.id_commande = commande.id_commande and details_commande.id_prod = produit.id_prod and livraison.id_user = user.id and livraison.id_commande = "+id_comm);
            //ss
            
            while (rs.next()) {
                Imagess = rs.getString("Image");

                String nom_produit = rs.getString("nom_prod");
                int prix_prod = rs.getInt("prix_prod");
                 commande_tot = rs.getInt("commande_tot");
                int qte_prod = rs.getInt("qte_prod");
                 prix_tot = rs.getInt("prixtotal");
                String meth_liv = rs.getString("meth_livraison");
                 paie = rs.getString("paiement");
                
                System.out.println(meth_liv);
                if(meth_liv.equals("aramex")){
                     liv_pa = 8000;}else{
                    liv_pa = 10000;
                            }
                prix_ut = prix_prod * qte_prod;
                            

                // print the results
                              data.add(new Facture(prix_prod, commande_tot, qte_prod, prix_ut, nom_produit,liv_pa,Imagess));

            }
       
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


}
