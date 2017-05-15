package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controller.FlashDataController;
import controller.FlashDataController;
import controller.GestionFlashController;
import controller.GestionFlashController;
import view.Home;
import view.Home;
import dao.VenteFlashService;
import entities.VenteFlash;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.restfb.BinaryAttachment;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDateTimeTextField;
//import jfxtras.scene.control.LocalDateTimeTextField;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import view.Home;

/**
 * FXML Controller class
 *
 * @author JAWHAR
 */
public class FlashListController implements Initializable {

    @FXML
    private TableView<VenteFlash> table;


    @FXML
    private TableColumn<VenteFlash, String> nom;

    @FXML
    private TableColumn<VenteFlash, String> description;

    @FXML
    private TableColumn<VenteFlash, Date> debut;

    @FXML
    private TableColumn<VenteFlash, Date> fin;
     @FXML
    public ObservableList<VenteFlash> flash = FXCollections.observableArrayList();

    @FXML
    private Button delete;
            
    @FXML
    private Button update;
    @FXML
    public static AnchorPane root;
  
  
 @FXML
    
    private TextField flashnom;
     @FXML
    private StackPane acContent;

    @FXML
    private TextArea descriptionflash;

    @FXML
    private LocalDateTimeTextField flashdebut;

    @FXML
    private LocalDateTimeTextField flashfin;
    @FXML
    private JFXButton add;

    @FXML
    private JFXButton seemore;

    @FXML
    private JFXButton facebook;
    @FXML
    private JFXButton AddFlash;

    @FXML
    private JFXButton produits;

    @FXML
    private ImageView imageview;
      @FXML
    private JFXDrawer drawer;
     @FXML
    private VBox box;
    @FXML
   
    private JFXHamburger btnHamburger;
     @FXML
    public static AnchorPane rootP;
     
     
     
     
      public void AddFlash() throws IOException 
        {
            Stage stage = new Stage();
            
        Parent root = FXMLLoader.load(getClass().getResource("/view/addFlash.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.APPLICATION_MODAL);
       
        stage.initOwner(AddFlash.getScene().getWindow());
        stage.showAndWait();}
        
     
//    @FXML
//
//    void facebook() throws FileNotFoundException {
//        
//        
//        VenteFlash vente = (VenteFlash) table.getSelectionModel().getSelectedItem();
//        String domain = "http://radixcode.com";
//        
//        String appId = "400512403657393";
//
//        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,"
//                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
//                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
//                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
//                + "manage_pages,publish_actions,read_page_mailboxes,rsvp_event";
//
//        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get(authUrl);
//        String accessToken;
//        while (true) {
//
//            if (!driver.getCurrentUrl().contains("facebook.com")) {
//                String url = driver.getCurrentUrl();
//                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
//
//                driver.quit();
//
//                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                User user = fbClient.fetchObject("me", User.class);
//                VenteFlash f = (VenteFlash) table.getSelectionModel().getSelectedItem();
//                      
//
//                String path= vente.getImage();
//         FileInputStream file = new FileInputStream( new File("D:\\y.png"));
//                FacebookType r = fbClient.publish("me/photos", FacebookType.class,BinaryAttachment.with("flash sale",file),Parameter.with("message", "vente flash:"+vente.getNom()+" commance a "+vente.getDatedebut())
//                        
//                );
//                System.out.println("fb.com/" + r.getId());
//            }
//        }
//    }

    @FXML
    void delete() {
        VenteFlash f = (VenteFlash) table.getSelectionModel().getSelectedItem();
        VenteFlashService venteflashservice;
        venteflashservice = new VenteFlashService();
        boolean result = venteflashservice.supprimerVenteFlash(f);
        load();
       
        if (result) {
              TrayNotification tray = new TrayNotification("sucess","supression avec sucess",NotificationType.SUCCESS);
                                   tray.showAndDismiss(Duration.seconds(5));
        } else {
            TrayNotification tray = new TrayNotification("fail","probleme lors de la supression",NotificationType.ERROR);
                                   tray.showAndDismiss(Duration.seconds(5));
        }


    }

    @FXML
    public void changed() throws SQLException {
        if (table.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        VenteFlash venteflash = (VenteFlash) table.getSelectionModel().getSelectedItem();
        Image image = new Image(venteflash.getImage());
        imageview.setImage(image);
        this.flashnom.setText(venteflash.getNom());
        this.descriptionflash.setText(venteflash.getDescription());

        Date start = venteflash.getDatedebut();
        Date finish = venteflash.getDatefin();

        java.sql.Timestamp datedebut = new java.sql.Timestamp(start.getTime());
        java.sql.Timestamp datefin = new java.sql.Timestamp(finish.getTime());

        Instant instant1 = datedebut.toInstant();
        Instant instant2 = datefin.toInstant();

        LocalDateTime x = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        this.flashdebut.setLocalDateTime(x);

        LocalDateTime y = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        this.flashdebut.setLocalDateTime(x);

        this.flashdebut.setLocalDateTime(x);
        this.flashfin.setLocalDateTime(y);

    }
  

    @FXML
    public void UpdateVenteFlash() throws ParseException {
        VenteFlash venteflash = (VenteFlash) table.getSelectionModel().getSelectedItem();
        VenteFlashService venteflashservice;

        venteflashservice = new VenteFlashService();
        String nom = this.flashnom.getText();
        String description = this.descriptionflash.getText();

        DateFormat df = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");

        String start = this.flashdebut.getText();
        String finish = this.flashfin.getText();

        Date debut = df.parse(start);
        Date fin = df.parse(finish);
       

        boolean result = venteflashservice.modifierVenteFlash(venteflash, nom, description, debut, fin);
            load();
        if (result) {
              TrayNotification tray = new TrayNotification("sucess","modification avec sucess",NotificationType.SUCCESS);
                                   tray.showAndDismiss(Duration.seconds(5));
        } else {
            TrayNotification tray = new TrayNotification("fail","probleme lors de la modification",NotificationType.ERROR);
                                   tray.showAndDismiss(Duration.seconds(5));
        }

    }

    @FXML
    public void gestionproduits(ActionEvent event) throws IOException {
        
         VenteFlash venteflash = (VenteFlash) table.getSelectionModel().getSelectedItem();

     FXMLLoader loader = new FXMLLoader();
     GestionFlashController c = loader.getController();
     c.idd=venteflash.getId();
       Stage primaryStage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/Views/gestionflash.fxml"));
		
		
		
		Scene scene = new Scene(root);
	 
		primaryStage.setScene(scene);
		primaryStage.show();
                
    }

    @FXML
    public void seemore(ActionEvent event ) throws IOException {

      
        VenteFlash venteflash = (VenteFlash) table.getSelectionModel().getSelectedItem();

      
      Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    Scene scene = stage.getScene();

           FXMLLoader loader = new FXMLLoader();
         FlashDataController c = new  FlashDataController();
           c.vente=venteflash;
           c.date=venteflash.getDatefin();
        
           
       
FlashDataController.class.getResource("Views/y.png");

                loader.load(getClass().getResource("/Views/FlashData.fxml").openStream());
               

        Parent parent = loader.getRoot();

               parent.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalFo‌​rm());


        
             scene.setRoot(parent);}
   


    

    public void load() {
        VenteFlashService venteflashservice;
        venteflashservice = new VenteFlashService();
        flash = venteflashservice.afficherVenteFlash();
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        debut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        fin.setCellValueFactory(new PropertyValueFactory<>("datefin"));

        table.setItems(flash);
    }

    public void add() throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(table.getScene().getWindow());
        stage.showAndWait();
        load();
        

    }
   

   public void sales(ActionEvent event ) throws IOException  {

        Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    Scene scene = stage.getScene();

           FXMLLoader loader = new FXMLLoader();
       
      //FlashsalesController.class.getResource("view/electricity.png");

                loader.load(getClass().getResource("/view/flashsales.fxml").openStream());
               

        Parent parent = loader.getRoot();

               parent.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalFo‌​rm());


        
             scene.setRoot(parent);


            


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
          rootP = root;
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(btnHamburger);
            transition.setRate(-1);
            btnHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
       
        load();
    }

}
