/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
        
        
        /**
 *
 * @author jamel_pc
 */
public class DisplayProduct extends Application {
    
    

   @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayProduct.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Produit");
        primaryStage.setScene(scene);
        primaryStage.show();     
        

            
    }
    public static void main(String[] args) {
       
       
        launch(args);
        
    }

}
