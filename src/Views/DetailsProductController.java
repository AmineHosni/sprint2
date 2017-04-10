/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXButton;
import entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import dao.ProduitUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class DetailsProductController implements Initializable {

    @FXML
    private Label lbllibelle;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lblStock;
    @FXML
    private JFXButton btnAcheter;
    @FXML
    private JFXButton btnStatistique;
    @FXML
    ImageView imgView;
    ProduitUtil produitUtil = new ProduitUtil();
    /**
     * Initializes the controller class.
     */

    
    
    public void setProduct(Produit produit){
        lblPrix.setText(produit.getPrixProduit().toString());
        lbllibelle.setText(produit.getLibelle());
        lblStock.setText(produit.getQuantiteStock().toString());
        imgView.setImage(new Image(produitUtil.returnImage(produit.getId())));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

}
