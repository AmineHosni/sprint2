/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class PaypalController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private JFXButton fermer;
WebEngine we;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
             we  = webview.getEngine();
        we.setJavaScriptEnabled(true);
        we.load("http://localhost/json/");
        } catch (Exception e) {
            System.err.println(e);
        }
    }    

    @FXML
    private void closeBu(ActionEvent event) throws SQLException, IOException {
           String x = we.getLocation();System.out.println(x);
             if(x.equals("http://localhost/json/done.php") || x.equals("http://127.0.0.1/json/done.php")){
                        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FinPanier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
                 }
             if(x.equals("http://localhost/json/") || x.equals("http://127.0.0.1/json/")){
                     System.out.println("erreur");
                 }
    }

    
    
}
