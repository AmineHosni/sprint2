/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import dao.ProduitUtil;
import entities.Produit;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class HomeController implements Initializable {

    @FXML
    TableView<Produit> table = new TableView<Produit>();
    @FXML
    TableColumn<Produit, Float> Prix;
    @FXML
    TableColumn<Produit, Float> Description;
    @FXML
    TableColumn<Produit, String> Name;
    @FXML
    TableColumn<Produit, String> Marque;
    @FXML
    TableColumn<Produit, String> Etat;
    @FXML
    TableColumn<Produit, String> Stock;
    ProduitUtil PU = new ProduitUtil();

    @FXML
    Button btnModifier, btnSupprimer, btnAjouter;

    @FXML
    TextField txtStock, txtprixProduit, txtquantiteStock, txtmarque, txtlibelle;
    @FXML
    Label lblPhoto, lblDuree, lblEtat;
    @FXML
    TextArea txtdescription;
    @FXML
    ImageView imgView;
    Produit p = new Produit();
    ProduitUtil pu = new ProduitUtil();
    private Stage stage;

    /**
     * Initializes the controller class.
     *
     *
     */
    
    
    
    
    public void Mousepress() {

        table.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {


             
                
                if(table.getSelectionModel().getSelectedItem() == null)
                    return;
                
                
                txtprixProduit.setText(table.getSelectionModel().getSelectedItem().getPrixProduit().toString());
                txtlibelle.setText(table.getSelectionModel().getSelectedItem().getLibelle());
                txtmarque.setText(table.getSelectionModel().getSelectedItem().getMarque());
                txtStock.setText(table.getSelectionModel().getSelectedItem().getQuantiteStock().toString());
                txtdescription.setText(table.getSelectionModel().getSelectedItem().getDescription());
                
            InputStream binaryStream = PU.returnImage(table.getSelectionModel().getSelectedItem().getId());
            Image image = new Image(binaryStream);
            imgView.setImage(image);
  }

        });

    }
          
    public void tableUpdate() {
        ObservableList<Produit> listProduit = PU.afficher();
        
        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        System.out.println("c bon");
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();

    }

    void setStage(Stage primaryStage) {
        this.stage = primaryStage;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableUpdate();
        Mousepress();
        
        
        
        
        
        btnModifier.setOnMouseClicked(e -> {

            Integer i = table.getSelectionModel().getSelectedItem().getId();

            p.setPrixProduit(Double.parseDouble(txtprixProduit.getText()));
            p.setLibelle(txtlibelle.getText());
            p.setMarque(txtmarque.getText());
            p.setQuantiteStock(Integer.parseInt(txtStock.getText()));
            p.setDescription(txtdescription.getText());
            p.setId(i);
            

            pu.modifierObject(p);
            tableUpdate();

        });
        
        btnSupprimer.setOnMouseClicked(e -> {
            Integer i = table.getSelectionModel().getSelectedItem().getId();
            pu.supprimerObject(i);
            tableUpdate();
        });

        btnAjouter.setOnMouseClicked(e -> {
            
      AddProduct p = new AddProduct(this) ;
        Stage stage = new Stage();
            try {
                p.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });

    }
}
