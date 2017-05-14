/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;



/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class EndCartController extends Application implements Initializable{

javafx.embed.swing.SwingFXUtils fXUtils;
BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
File file = new File("C:/test.jpg");
VBox vbox = null;

@Override
public void start(Stage primaryStage) {
    vbox = new VBox();
    Button btn = new Button();
    Image i = new Image("file:C:\\Koala.jpg");
    ImageView imageView = new ImageView();
    imageView.setImage(i);
    vbox.getChildren().add(imageView);
    vbox.setSpacing(10);
    btn.setText("Say 'Hello World'");
    btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        // here we make image from vbox and add it to scene, can be repeated :)
       WritableImage snapshot = vbox.snapshot(new SnapshotParameters(), null);
           vbox.getChildren().add(new ImageView(snapshot));
            saveImage(snapshot);
            System.out.println(vbox.getChildren().size());
        }
    });


    Scene scene = new Scene(new Group(btn), 500, 400);

    primaryStage.setScene(scene);
    primaryStage.show();
}

private void saveImage(WritableImage snapshot) {
    BufferedImage image;
    image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
    try {
        Graphics2D gd = (Graphics2D) image.getGraphics();
        gd.translate(vbox.getWidth(), vbox.getHeight());
        ImageIO.write(image, "png", file);
    } catch (IOException ex) {
        Logger.getLogger(EndCartController.class.getName()).log(Level.SEVERE, null, ex);
    };
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }
