/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import util.DatabaseConnection;
import dao.VenteFlashService;
import entities.VenteFlash;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.sql.Connection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDateTimeTextField;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AddFlashController {

    @FXML
    private ResourceBundle resources;

    private File mainfile;

    @FXML
    private URL location;

    @FXML
    private Pane root;

        @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXTimePicker startTime;

    @FXML
    private JFXTimePicker endTime;
    private LocalDateTimeTextField finish;
    @FXML
    private ImageView imageview;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXTextArea description;

    @FXML
    private Button ajoutimage;
        public    File chosenFile=null;
    @FXML
    public void addimage() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Images (.png, .jpg, .bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extentionFilter);
//        String userDirectoryString = System.getProperty("user.home");
//        File userDirectory = new File(userDirectoryString);
//        if (!userDirectory.canRead()) {
//            userDirectory = new File("c:/");
//        }
//        fileChooser.setInitialDirectory(userDirectory);

        //Choose the file
         chosenFile = fileChooser.showOpenDialog(null);
        String imgpath;
        if (chosenFile != null) {
            
                imgpath = chosenFile.toURI().toURL().toString();
               
                                                                           
                
               Image image = new Image(imgpath);
               imageview.setImage(image);
           

        } else {

        }
    }

   
    @FXML
    void save(ActionEvent event) throws ParseException, IOException {
        

         if  (nom.getText().trim().isEmpty())   {
           
        TrayNotification tray = new TrayNotification("Error","champs du nom est vide ",NotificationType.ERROR);
      
        tray.showAndDismiss(Duration.seconds(5));
        
            return;

            //  System.out.println("Sorry! unable to save record");  
        }
           if (description.getText().trim().isEmpty())   {
           
        TrayNotification tray = new TrayNotification("Error","champs de la description de debut est vide ",NotificationType.ERROR);
      
        
        tray.showAndDismiss(Duration.seconds(5));
            return;

            //  System.out.println("Sorry! unable to save record");  
        }
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        String name = this.nom.getText();

        String desc = this.description.getText();
                Date d  = new Date();
                
                
          
           if (startDate.getValue()==null)  {
           
        TrayNotification tray = new TrayNotification("Error","champs de la date du debut est vide ",NotificationType.ERROR);
      
        tray.showAndWait();
            return;

            //  System.out.println("Sorry! unable to save record");  
        }
           else if (startTime.getValue()==null)  {
           
        TrayNotification tray = new TrayNotification("Error","champs de l'heure du debut est vide ",NotificationType.ERROR);
      
       
        tray.showAndDismiss(Duration.seconds(5));
            return;

            //  System.out.println("Sorry! unable to save record");  
        }
           else if  (endDate.getValue()==null)  {
           
        TrayNotification tray = new TrayNotification("Error","champs de la date de fin est vide ",NotificationType.ERROR);
      
        
        tray.showAndDismiss(Duration.seconds(5));
            return;

            //  System.out.println("Sorry! unable to save record");  
        }
           else if   (endTime.getValue()==null)  {
           
        TrayNotification tray = new TrayNotification("Error","champs de l'heure  de fin est vide ",NotificationType.ERROR);
      
        
        tray.showAndDismiss(Duration.seconds(5));
            return;

        } 
          
         
           else if   (chosenFile==null )  {
           
        TrayNotification tray = new TrayNotification("Error","choisir une image s'il vous plait",NotificationType.ERROR);
      
        
        tray.showAndDismiss(Duration.seconds(5));
            return;

        }  
         
               
                                                            
      
          
           
                
                
        
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");
        
                        LocalDateTime d1= startDate.getValue().atTime(startTime.getValue());
                           LocalDateTime d2=endDate.getValue().atTime(endTime.getValue());
                           Date start = Date.from(d1.atZone(ZoneId.systemDefault()).toInstant());
                             Date finish = Date.from(d2.atZone(ZoneId.systemDefault()).toInstant());

                        
     
          
        VenteFlashService venteflashservice;
        venteflashservice = new VenteFlashService();
        VenteFlash f = new VenteFlash(name, desc, start,finish, chosenFile.toURI().toURL().toString());

        boolean result = venteflashservice.ajouterVenteFlash(f);
        if( result==true) {
                    TrayNotification tray = new TrayNotification("success","ajout de vente flash effecteé",NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(5));

        }
                
        else  {                   
            TrayNotification tray = new TrayNotification("fail","probleme lors de l'ajout de la vente flash",NotificationType.ERROR);
                                   tray.showAndDismiss(Duration.seconds(5));
        
    }
        
    Stage stage = (Stage) ajoutimage.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    public void retour() throws IOException{
     Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FlashList.fxml"));
		
		Scene scene = new Scene(root);
	 
		primaryStage.setScene(scene);
		primaryStage.show();
                 Stage stage = (Stage) endDate.getScene().getWindow();
        // do what you have to do
        stage.close();
}
    @FXML
    void initialize() {

    }
}
