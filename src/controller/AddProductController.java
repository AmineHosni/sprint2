/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.CategorieUtil;
import entities.Produit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
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
import javafx.util.Duration;
import services.ProduitService;

import services.ftpSave;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AddProductController implements Initializable {

    FXMLLoader loader;

    private Stage stage;
    ProduitService produitService;
    ObservableList<String> etatList = FXCollections.observableArrayList("nouveau", "occasion");
    ObservableList<String> dureeList = FXCollections.observableArrayList("15", "30", "45");
    File file1, file2, file3;
    @FXML
    ChoiceBox chEtat, chDuree, chCategorie;
    @FXML
    TextField txtStock, txtprixProduit, txtquantiteStock, txtmarque, txtlibelle;
    @FXML
    Label lblPhoto, lblDuree, lblEtat, lblPhoto2, lblPhoto3;
    @FXML
    ImageView Photoview, Photoview2, Photoview3;
    @FXML
    TextArea txtdescription;
    @FXML
    JFXButton btnAjouter, btn1erPhoto, btn2emePhoto, btn3emePhoto;
    private HomeController homeController;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        chDuree.setItems(dureeList);
        chEtat.setItems(etatList);
        produitService = new ProduitService();
        CategorieUtil categorieUtil = new CategorieUtil();
        chCategorie.setItems(categorieUtil.listerCategorie());
    }

    private boolean AjouterButtonAction(File file1, File file2, File file3) {

        Produit produit = new Produit();
        if (txtlibelle.getText().equals("")) {

            TrayNotification tray
                    = new TrayNotification("Username error", "CHECK YOUR USERNAME ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (txtdescription.getText().equals("")) {

            TrayNotification tray
                    = new TrayNotification("Description error", "CHECK YOUR Description ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (txtmarque.getText().equals("")) {

            TrayNotification tray
                    = new TrayNotification("Marque error", "CHECK YOUR MARQUE ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (txtprixProduit.getText().equals("")) {

            TrayNotification tray
                    = new TrayNotification("Price error", "CHECK YOUR PRICE , \n it need to be 00.00 ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (txtStock.getText().equals("")) {

            TrayNotification tray
                    = new TrayNotification("Stock error", "CHECK YOUR Stock \n must be a number  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if ((file1 == null)) {

            TrayNotification tray
                    = new TrayNotification("Image error", "upload at least one image", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (chDuree.getValue() == null) {

            TrayNotification tray
                    = new TrayNotification("Duration error", "Chose duree ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        } else if (chEtat.getValue() == null) {

            TrayNotification tray
                    = new TrayNotification("Etat error", "Chose etat ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(2));

        }else if ((file2==null)&&(file3==null)){
            
        }

        produit.setLibelle(txtlibelle.getText());
        produit.setDescription(txtdescription.getText());
        produit.setMarque(txtmarque.getText());
        produit.setPrixProduit(Double.parseDouble(txtprixProduit.getText()));
        produit.setQuantiteStock(Integer.parseInt(txtStock.getText()));
        new ftpSave().saveFile(file1);
       
       

        produit.setImageName(file1.getName());
        if (file2!=null) {
      
            produit.setImageName2(file2.getName());
             new ftpSave().saveFile(file2);
        } else
            produit.setImageName2(null);
        if (file3 != null) {
            produit.setImageName3(file3.getName());
             new ftpSave().saveFile(file3);
        } else {
            produit.setImageName3(null);
        }

            
        
        produit.setDuree(Integer.valueOf(chDuree.getValue().toString()));
        produit.setEtat(chEtat.getValue().toString());
        produit.setSeller(9);
        CategorieUtil categorieUtil = new CategorieUtil();
        produit.setProduitCategorie(categorieUtil.getIdFromNom(chCategorie.getValue().toString()));
        produitService.ajouterProduit(produit);
        TrayNotification tray = new TrayNotification("succées d'ajout", "Vous Avez ajouter un produit", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(2));
        homeController.tableUpdate(9);

        //new SendMail().envoyé("jamel.mustapha94@gmail.com", "vous avez ajouter un produit " + produit.getLibelle(),
                //"l'ajout du " + produit.getLibelle());
        //send sms
//        SmsSender smsSender = new SmsSender("votre produit "+produit.getLibelle() +"\n prix :"+txtprixProduit.getText() +" dt"+
        //              "\n stock : "+produit.getQuantiteStock()+"\n Description : "+produit.getDescription()+"\n ");
        return true;

    }

    @FXML
    private void firstPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        file1 = fileChooser.showOpenDialog(stage);

        if (file1 != null) {
            lblPhoto.setText(file1.getName());
            try {
                String img = file1.toURI().toURL().toString();
                Image image = new Image(img);
                Photoview.setImage(image);
                Photoview.setFitWidth(300);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void secondPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file2 = fileChooser.showOpenDialog(stage);

        if (file2 != null) {
            lblPhoto2.setText(file2.getName());
            try {
                String img = file2.toURI().toURL().toString();
                Image image = new Image(img);
                Photoview2.setImage(image);
                Photoview2.setFitWidth(300);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void thirdPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file3 = fileChooser.showOpenDialog(stage);
        if (file3 != null) {
            lblPhoto3.setText(file3.getName());
            try {
                String img = file3.toURI().toURL().toString();
                Image image = new Image(img);
                Photoview3.setImage(image);
                Photoview3.setFitWidth(300);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
        btnAjouter.setOnAction(e -> {

            if (AjouterButtonAction(file1, file2, file3)) {

            }

            stage = (Stage) btnAjouter.getScene().getWindow();

            stage.close();

        });

    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;

    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
