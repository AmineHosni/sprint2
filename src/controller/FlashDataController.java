/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VenteFlashService;
import entities.VenteFlash;
import entities.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author JAWHAR
 */
public class FlashDataController implements Initializable {
       @FXML
    private ImageView image;
               
     @FXML
    private HBox root;

    @FXML
    private Text days;

    @FXML
    private Text colon0;

    @FXML
    private Text hrs;

    @FXML
    private Text colon1;

    @FXML
    private Text mins;

    @FXML
    private Text colon2;

    @FXML
    private Text secs;
    
        @FXML
    private JFXListView liste;

  public static Date date;
  
  public static VenteFlash vente;
  
      @FXML
    private ImageView pic;
         @FXML
    private JFXButton retour;
  
  
  
  ObservableList<Parent> produits = FXCollections.observableArrayList();
          
   
      
    
        
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

         
        root.setBackground(Background.EMPTY);
        DropShadow shadow = new DropShadow(3, 2, 5, Color.GRAY);
        Glow glow = new Glow(0.5);
        root.setEffect(shadow);
        

        startTimer(hrs, mins, secs,days);
        

         liste.getItems().clear();
       
        System.out.println(vente.getId());
        for (Produit prod : new VenteFlashService().taken(vente.getId())){
            try {
                ModelListeController c = new ModelListeController();
                c.p=prod;
                System.out.println(prod.getId());
                System.out.println(prod.getImageName());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ModelListe.fxml"));
                Parent model = (Parent)fxmlLoader.load();
                
                  
                
                
                
                produits.add(model);
            } catch (IOException ex) {
                Logger.getLogger(FlashDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        liste.setItems(produits);
        }
    }    
        
        

    
 
            
        
        
    
        // TODO
       

  
        
    private void startTimer(Text hrsText, Text minsText, Text secsText,Text daysText) {
        boolean x=true;

         

        Thread timerThread = new Thread(() -> {
            while (x) {
                
                    Date  startDate = new Date();
                    long different = date.getTime() - startDate.getTime();
                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;
                    long elapsedDays = different / daysInMilli;
                    different = different % daysInMilli;
                    long elapsedHours = different / hoursInMilli;
                    different = different % hoursInMilli;
                    
                    long elapsedMinutes = different / minutesInMilli;
                    different = different % minutesInMilli;
                    
                    
                    long elapsedSeconds = different / secondsInMilli;
                                   
                    secsText.setText(String.format("%02d",elapsedSeconds)); 
                    hrsText.setText(String.format("%02d",elapsedHours));
                    minsText.setText(String.format("%02d",elapsedMinutes));;
                    daysText.setText(String.format("%02d",elapsedDays));
         
                    
            }
           
        });
        timerThread.start();
    }
  
  public void save(){
              startTimer(hrs, mins, secs,days);

  }
  public void  retour(ActionEvent event) throws IOException
  {
  Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("liste.fxml"));
		
		Scene scene = new Scene(root);
	 
		primaryStage.setScene(scene);
		primaryStage.show();
                 Stage stage = (Stage) hrs.getScene().getWindow();
        // do what you have to do
        stage.close();

			 

  } 
    public void start(Stage primaryStage) throws Exception {
        
        
      

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

       
        primaryStage.show();

       
    }
    }


