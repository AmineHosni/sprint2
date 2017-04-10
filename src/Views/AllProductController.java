/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import dao.ProduitUtil;
import entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AllProductController implements Initializable {

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
    ProduitUtil produitUtil = new ProduitUtil();
    private Stage stage;
    private HomeController homeController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        tableUpdate();
        
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

     void setStage(Stage primaryStage) {
        this.stage = primaryStage;

    }

      void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
  
}
