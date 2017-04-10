/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import dao.CategorieUtil;
import entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AllProductController implements Initializable {

    @FXML
    private TableView<Produit> table;
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
    private Label lblSearch;
    @FXML
    private Label lblMaxPrix;
    @FXML
    private Label lblMinPrix;
    @FXML
    private JFXSlider prixMinSlider;
    @FXML
    private JFXSlider prixMaxSlider;
    @FXML
    private JFXComboBox cmbCategorie;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnNextPhoto,btnMonespace;
    ProduitService produitservice = new ProduitService();
    CategorieUtil categorieUtil = new CategorieUtil();
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
        lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
        prixMaxSlider.setMax(produitservice.maxPrice());
        prixMinSlider.setMax(produitservice.maxPrice());
        cmbCategorie.setItems(categorieUtil.listerCategorie());
        new HomeController().tableUpdate(table,Stock,Etat,Marque,Name,Description,Prix);
        new HomeController().Mousepress(table,imgView);
        btnMonespace.setOnAction(e->{
          Stage stage = (Stage)btnMonespace.getScene().getWindow();

            try {
                new Home().start(stage);
            } catch (Exception ex) {
                Logger.getLogger(AllProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        txtSearch.setOnKeyReleased(e -> {

            new HomeController().tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitservice.SearchByName(txtSearch.getText()));
        });
        cmbCategorie.setOnAction(e -> {
            new HomeController().tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitservice.SearchByCategorie(cmbCategorie.getValue().toString()));

        });

        prixMaxSlider.setOnMouseClicked(e -> {
            lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            new HomeController().tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        prixMinSlider.setOnMouseClicked(e -> {
            lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
            new HomeController().tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });
    
    }    
    
}
