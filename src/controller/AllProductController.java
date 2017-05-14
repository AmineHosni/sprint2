/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import dao.CategorieUtil;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ProduitService;
import services.PieChartSample;
import view.UserConnection;

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
    private Label lblSearch, lblUser;
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
    private Button btnNextPhoto;
    @FXML
    private JFXHamburger btnHamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    JFXButton btnLogout;
    @FXML
    JFXButton btnStatistique;        

    ProduitService produitservice = new ProduitService();
    CategorieUtil categorieUtil = new CategorieUtil();
    Integer i = 1;

    @FXML
    public void NextPhoto() {

        if (table.getSelectionModel().getSelectedItem()==null) {
            return;
        }
        if (i == 1) {
            new HomeController().imageDisplay(imgView, table, "image_name2");
            i++;
        } else if (i == 2) {
            new HomeController().imageDisplay(imgView, table, "image_name3");
            i++;
        } else if (i == 3) {
            new HomeController().imageDisplay(imgView, table, "image_name");
            i = 1;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUser.setText("Jamel Mustapha");

        lblMaxPrix.setText(String.valueOf(produitservice.maxPrice()));
        lblMinPrix.setText(String.valueOf(produitservice.maxPrice()));
        prixMaxSlider.setMax(produitservice.maxPrice());
        prixMinSlider.setMax(produitservice.maxPrice());
        cmbCategorie.setItems(categorieUtil.listerCategorie());
        new HomeController().tableUpdate(table, Stock, Etat, Marque, Name, Description, Prix);
        new HomeController().Mousepress(table, imgView);
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(btnHamburger);
        transition.setRate(-1);
        btnHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

        txtSearch.setOnKeyReleased(e -> {

            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByName(txtSearch.getText()));
        });
        cmbCategorie.setOnAction(e -> {
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByCategorie(cmbCategorie.getValue().toString()));

        });

        prixMaxSlider.setOnMouseClicked(e -> {
            lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        prixMinSlider.setOnMouseClicked(e -> {
            lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });
        btnLogout.setOnAction(e -> {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
            new UserConnection().start(stage);
        });
        btnStatistique.setOnAction(e->{
            
            Stage stage = new Stage();
              new PieChartSample().inserData(stage, produitservice.Nouveau(), produitservice.Occasion());
        });
    }

}
