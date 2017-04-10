/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class SidePanelContentController implements Initializable {

    @FXML
    JFXButton btnTousLesProduit,btnHistorique;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnTousLesProduit.setOnMouseClicked(e -> {

            AllProduct allProduct = new AllProduct();
            
            Stage stage = new Stage();
            stage = (Stage)btnTousLesProduit.getScene().getWindow();
            try {
                allProduct.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
