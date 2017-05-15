/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.magasin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author AmineHosni
 */
public class DetailsMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/FXMLDetails.fxml"));

//        URL css = getClass().getResource("/x.css");
        Scene scene = new Scene(root);
//        System.out.println(css.toExternalForm());
//        scene.getStylesheets().add(css.toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("Application Closed by click to Close Button(X)");
                        
                        try {
                            App m = new App();
                            Stage s = new Stage();
                            m.start(s);

                        } catch (Exception ex) {
                            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Connection c = MyConnection.getInstance();
    }

}
