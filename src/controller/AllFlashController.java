/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXButton;
import dao.VenteFlashService;
import entities.VenteFlash;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JAWHAR
 */
public class AllFlashController implements Initializable {
     
               
     
    
        @FXML
    private JFXListView liste;

  
  
      @FXML
    private ImageView pic;
      
    @FXML
    private JFXButton retour;
 
 
  
  
  
  ObservableList<Parent> ventes = FXCollections.observableArrayList();
          
   
      
    
        
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

         Image clock = new Image("/media/electricity.png");
        
        
        
        
        
       pic.setImage(clock);
        
        

        
        

         liste.getItems().clear();
       
        
        
        
        for (VenteFlash v : new VenteFlashService().afficherVenteFlash()){
            try {
                System.out.print(v);
                        
                AllFlashSampleController.f=v;
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AllFlashSample.fxml"));
                Parent model = (Parent)fxmlLoader.load();
                
                  
                
                
                
                ventes.add(model);
            } catch (IOException ex) {
                Logger.getLogger(AllFlashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        liste.setItems(ventes);
        }
    }    
        
    public void retour(ActionEvent event) throws IOException
    {
         
    
      Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FlashList.fxml"));
		
		Scene scene = new Scene(root);
	 
		primaryStage.setScene(scene);
		primaryStage.show();
                 Stage stage = (Stage) pic.getScene().getWindow();
        // do what you have to do
        stage.close();


            

}

}
 
            
        
        
    
        // TODO
       

  
    
     
      