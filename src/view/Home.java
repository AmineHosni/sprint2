/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.HomeController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author jamel_pc
 */

public class Home extends Application {
     Integer idUser;
     public Home(Integer idUser) {
        this.idUser= idUser;
    }

    public Home() {
    }
     
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        HomeController homeController= loader.getController();
        homeController.idUser=idUser;
        primaryStage.setTitle("Mon espace");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
   

   
    
}
