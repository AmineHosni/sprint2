/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.magasin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.FtpUtil;

/**
 * FXML Controller class
 *
 * @author AmineHosni
 */
public class TestFXMLController implements Initializable {

    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           InputStream is=  null;
           is = FtpUtil.getFile("throwBack.jpg");
           img.setImage(new Image(is));
           try {
               is.close();
           } catch (IOException ex) {
               Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       } catch (IOException ex) {
           Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
    
}
