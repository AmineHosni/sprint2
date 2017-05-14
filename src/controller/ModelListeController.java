/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Produit;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModelListeController implements Initializable {
    @FXML
    private ImageView img;
    @FXML
    private Label titre;
    @FXML
    private Label prix;
    @FXML
    private JFXButton ajouter;
    
public static Produit p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print(p.getId());
        titre.setText(p.getLibelle());
        System.out.println(p.getImageName());
         File file = new File("C:\\Users\\Public\\Pictures\\"+p.getImageName());
        String pic;               
    
        try {
            pic= file.toURI().toURL().toString();
              Image image = new Image(pic);
              img.setImage(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ModelListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        

       
             
                String s = String.valueOf(p.getPrixProduit());
               
        prix.setText(s);
        

        
       
      ajouter.setId(String.valueOf(p.getId()));


        
    }    

     

    
     

   
    
}
