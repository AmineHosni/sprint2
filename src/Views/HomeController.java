/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.*;
import dao.ProduitUtil;
import entities.Produit;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.*;
import dao.CategorieUtil;
import javafx.scene.Cursor;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class HomeController implements Initializable {

    @FXML
    JFXComboBox cmbCategorie;
    @FXML
    JFXSlider prixMaxSlider, prixMinSlider;
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
    @FXML
    JFXButton btnModifier, btnSupprimer, btnAjouter, btnDetails;
    @FXML
    TextField txtStock, txtprixProduit, txtquantiteStock, txtmarque, txtlibelle;
    @FXML
    JFXTextField txtSearch;
    @FXML
    Label lblPhoto, lblDuree, lblEtat, lblMinPrix, lblMaxPrix;
    @FXML
    TextArea txtdescription;
    @FXML
    ImageView imgView;
    Produit produit = new Produit();
    ProduitUtil produitUtil = new ProduitUtil();
    private Stage stage;

    /**
     * Initializes the controller class.
     *
     *
     */
    public void imageDisplay() {
        InputStream binaryStream = produitUtil.returnImage(table.getSelectionModel().getSelectedItem().getId());
        Image image = new Image(binaryStream);
        imgView.setImage(image);
    }

    public void Mousepress() {
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (table.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                imageDisplay();
                txtprixProduit.setText(table.getSelectionModel().getSelectedItem().getPrixProduit().toString());
                txtlibelle.setText(table.getSelectionModel().getSelectedItem().getLibelle());
                txtmarque.setText(table.getSelectionModel().getSelectedItem().getMarque());
                txtStock.setText(table.getSelectionModel().getSelectedItem().getQuantiteStock().toString());
                txtdescription.setText(table.getSelectionModel().getSelectedItem().getDescription());
            }
        });
    }

    public void tableUpdate() {
        ObservableList<Produit> listProduit = produitUtil.afficher();
        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();
    }

    public void tableAfterSearch(ObservableList listProduit) {

        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();
    }

    void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void supprimer() {
        if (table.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Integer i = table.getSelectionModel().getSelectedItem().getId();
        produitUtil.supprimerObject(i);
        tableUpdate();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProduitService produitService = new ProduitService();
        CategorieUtil categorieUtil = new CategorieUtil();
        lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
        lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
        prixMaxSlider.setMax(produitService.maxPrice());
        prixMinSlider.setValue(0);
        tableUpdate();
        Mousepress();

        cmbCategorie.setItems(categorieUtil.listerCategorie());
        btnModifier.setOnMouseClicked(e -> {
            if (table.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            Integer i = table.getSelectionModel().getSelectedItem().getId();
            produit.setPrixProduit(Double.parseDouble(txtprixProduit.getText()));
            produit.setLibelle(txtlibelle.getText());
            produit.setMarque(txtmarque.getText());
            produit.setQuantiteStock(Integer.parseInt(txtStock.getText()));
            produit.setDescription(txtdescription.getText());
            produit.setId(i);
            produitUtil.modifierObject(produit);
            tableUpdate();

        });
        btnAjouter.setOnMouseClicked(e -> {
            AddProduct addProduct = new AddProduct(this);
            Stage stage = new Stage();
            try {
                addProduct.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//multi search mezel
        txtSearch.setOnKeyReleased(e -> {

            tableAfterSearch(produitService.SearchByName(txtSearch.getText()));
        });
        cmbCategorie.setOnAction(e -> {
            tableAfterSearch(produitUtil.SearchByCategorie(cmbCategorie.getValue().toString()));

        });

        prixMaxSlider.setOnMouseClicked(e -> {
            lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            tableAfterSearch(produitService.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        prixMinSlider.setOnMouseClicked(e -> {
            lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            tableAfterSearch(produitService.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        btnDetails.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem().getId() != null) {
                DisplayProduct displayProduct = new DisplayProduct(this, table.getSelectionModel().getSelectedItem());
                
                
                try {
                     Stage stage = new Stage();
                    displayProduct.start(stage);
                    System.out.println(displayProduct.produit);
                } catch (Exception ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("null");
            }
           

        });
    }
}
