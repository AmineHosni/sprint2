/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.AllProduct;
import view.Home;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class SidePanelContentController implements Initializable {

    @FXML
    JFXButton btnTousLesProduit, btnMonespace, addFlash, allFlash;
    @FXML
    public void Flash(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader loader = new FXMLLoader();

        loader.load(getClass().getResource("/view/FlashList.fxml").openStream());

        Parent parent = loader.getRoot();
        parent.getStylesheets().add(getClass().getResource("/view/newCascadeStyleSheet.css").toExternalFo‌​rm());

        scene.setRoot(parent);
    }
    @FXML
    public void AllFlash(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.load(getClass().getResource("/Views/AllFlash.fxml").openStream());
        Parent parent = loader.getRoot();
        scene.setRoot(parent);
    }
    @FXML
    public void AddFlash() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addFlash.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.allFlash.getScene().getWindow());
      stage.show();    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnTousLesProduit.setOnMouseClicked(e -> {

            AllProduct allProduct = new AllProduct();

            Stage stage = new Stage();
            stage = (Stage) btnTousLesProduit.getScene().getWindow();
            try {
                allProduct.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnMonespace.setOnAction(e -> {

            Stage stage = (Stage) btnMonespace.getScene().getWindow();

            try {
                new Home().start(stage);
            } catch (Exception ex) {
                Logger.getLogger(AllProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
