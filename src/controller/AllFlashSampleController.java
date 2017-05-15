/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.VenteFlash;
import com.jfoenix.controls.JFXButton;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import jfxtras.scene.control.ImageViewButton;
import view.Home;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import static org.openqa.selenium.lift.Finders.table;

/**
 * FXML Controller class
 *
 * @author JAWHAR
 */
public class AllFlashSampleController implements Initializable {
       @FXML
    private ImageView img;
       
       
          @FXML
    private ImageView hourglass;
           @FXML
    private ImageViewButton facebook;

    @FXML
    private Label nom;

    @FXML
    private Label description;

    @FXML
    private JFXButton acceder;
    
public static VenteFlash f;
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
    

    

//public void facebook() throws FileNotFoundException{
//
//VenteFlash vente = f;
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
//                VenteFlash v =f;
//                      
//
//                String path= vente.getImage();
//         FileInputStream file = new FileInputStream( new File("D:\\y.png"));
//                FacebookType r = fbClient.publish("me/photos", FacebookType.class,BinaryAttachment.with("flash sale",file),Parameter.with("message", "vente flash"+vente.getNom()+" commance a "+"vente flash"+vente.getDatedebut())
//                        
//                );
//                System.out.println("fb.com/" + r.getId());
//            }
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image clock = new Image("/media/fb.jpg");
         Image time = new Image("/media/hourglass.png");
         Image share = new Image("/media/post.png");
                  Image next = new Image("/media/x.png");



        
        
        
       facebook.setImage(share);
              hourglass.setImage(time);
              

        nom.setText(f.getNom());
        Image m = new Image(f.getImage());
        img.setImage(m);
        description.setText(f.getDescription());
        

        
       
      acceder.setId(String.valueOf(f.getId()));
          
       startTimer(hrs, mins, secs,days);



        
    }    

     private void startTimer(Text hrsText, Text minsText, Text secsText,Text daysText) {
        boolean x=true;

         

        Thread timerThread = new Thread(() -> {
            while (x) {
                try {
                    Date  startDate = new Date();
                    sleep(1000); 
                    long different = f.getDatefin().getTime()- startDate.getTime();
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
         
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        });
        timerThread.start();
    }
     

    
     
 public void save(){
              startTimer(hrs, mins, secs,days);

  }
   
    
}


