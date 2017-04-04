/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXButton;
import entities.Produit;
import java.io.File;
import java.net.MalformedURLException;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AddProductController implements Initializable {

    FXMLLoader loader;

    private Stage stage;
    ProduitService produitService;
    File file;
    ObservableList<String> etatList = FXCollections.observableArrayList("nouveau", "occasion");
    ObservableList<String> dureeList = FXCollections.observableArrayList("15", "30", "45");

    @FXML
    ChoiceBox chEtat, chDuree;
    @FXML
    TextField txtStock, txtprixProduit, txtquantiteStock, txtmarque, txtlibelle;
    @FXML
    Label lblPhoto, lblDuree, lblEtat;
    @FXML
    ImageView Photoview;
    @FXML
    TextArea txtdescription;
    @FXML
    Button btnAjouter;
    private HomeController homeController;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        chDuree.setItems(dureeList);
        chEtat.setItems(etatList);
        produitService = new ProduitService();
    }
    @FXML
    private void AjouterButtonAction(ActionEvent event) {
        Produit produit = new Produit();
        if (txtlibelle.getText().equals("") || txtdescription.getText().equals("") ||
                txtmarque.getText().equals("")
                || txtprixProduit.getText().equals("") || txtStock.getText().equals("") || (file == null)
                || chDuree.getValue() == null || chEtat.getValue() == null) {
            return;
        }
        produit.setLibelle(txtlibelle.getText());
        produit.setDescription(txtdescription.getText());
        produit.setMarque(txtmarque.getText());
        produit.setPrixProduit(Double.parseDouble(txtprixProduit.getText()));
        produit.setQuantiteStock(Integer.parseInt(txtStock.getText()));
        produit.setImageFile(file);
        produit.setDuree(Integer.valueOf(chDuree.getValue().toString()));
        produit.setEtat(chEtat.getValue().toString());
        produitService.ajouterProduit(produit);
        stage = (Stage) btnAjouter.getScene().getWindow();
        stage.close();
        homeController.tableUpdate();
    }
    
    @FXML
    private void PhotoButtonAction(ActionEvent event) {
        System.out.println("Views.AddProductController.PhotoButtonAction()");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            lblPhoto.setText(file.getPath());
            try {
                String img = file.toURI().toURL().toString();
                Image image = new Image(img);
                Photoview.setImage(image);
                Photoview.setFitWidth(300);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
    }
    void setStage(Stage primaryStage) {
        this.stage = primaryStage;

    }
    public FXMLLoader getLoader() {
        return loader;
    }
    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }
    void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
