/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.AddProductController;
import controller.HomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 *
 * @author jamel_pc
 */
public class AddProduct extends Application {
    HomeController homeController;
    public AddProduct(HomeController homeController) {
        this.homeController = homeController;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Ajouter Produit");
        primaryStage.setScene(scene);
        AddProductController AddController = loader.getController();
        AddController.setStage(primaryStage);
        AddController.setHomeController(homeController);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
