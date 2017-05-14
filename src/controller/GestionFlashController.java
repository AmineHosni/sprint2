/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ModelListeController.p;
import dao.VenteFlashService;
import entities.Produit;
import com.jfoenix.controls.JFXButton;
import com.sun.prism.impl.Disposer;
import entities.VenteFlash;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;





public class GestionFlashController implements Initializable {



 @FXML
    private TableView<Produit> disponibles;
  
    @FXML
    private TableColumn<Produit,String> libelle;

    @FXML
    private TableColumn<Produit,Integer> prixProduit;
 
   
     
    
     @FXML
    private TableColumn<Produit, Integer> prixflash;

    
    
    @FXML
    private TableColumn action;
   

    
  @FXML
    private JFXButton add;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton retour;
    @FXML
    private ImageView image ;

    
    
    
    
   
    
  
    
      @FXML
    private TableView<Produit> taken;
  @FXML
    private TableColumn<Produit, Boolean> check1;
   

    @FXML
    private TableColumn<Produit,String> nom1;

    @FXML
    private TableColumn<Produit,Integer> prix1;
    
     public	ObservableList<Produit> free = FXCollections.observableArrayList();
          public	ObservableList<Produit> list = FXCollections.observableArrayList();
                    public	ObservableList<Produit> deletecheck = FXCollections.observableArrayList();


     public	ObservableList<Produit> engaged = FXCollections.observableArrayList();
  
    public static int idd;
    
    
    @FXML
    private Label flash;
    
    @FXML
    private Label labb;
    @FXML
    private JFXButton setprice;
    public void change() throws MalformedURLException{
               Produit p=disponibles.getSelectionModel().getSelectedItem();
        System.out.println(p.getImageName());
     File file = new File("C:\\Users\\Public\\Pictures\\"+p.getImageName());
        String pic;               
    
      
            pic= file.toURI().toURL().toString();
              Image im = new Image(pic);
              image.setImage(im);}
     public void change1() throws MalformedURLException{  
         Produit p=taken.getSelectionModel().getSelectedItem();
        System.out.println(p.getImageName());

     File file = new File("C:\\Users\\Public\\Pictures\\"+p.getImageName());
        String pic;               
    
      
            pic= file.toURI().toURL().toString();
              Image im = new Image(pic);
              image.setImage(im);}


    @FXML
   public void adding() throws SQLException{
      
       Produit p=disponibles.getSelectionModel().getSelectedItem();
      
        
        if (p==null){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("probeleme d'ajout");
            alert.setHeaderText("pas de produit choisit");
            alert.setContentText("selectionner un element ");
            alert.show();
            return;
            
        }
         
        
           else{
        
            TrayNotification tray = new TrayNotification("sucess","ajout avec sucess",NotificationType.SUCCESS);
                                   tray.showAndDismiss(Duration.seconds(5));
        }
        
        
        VenteFlashService venteflashservice = new VenteFlashService();
        
          venteflashservice.ajouterproduit(idd, p);
          again();
          
                        

 

          
                                
                    


}

    
    
    @FXML
    void delete() throws SQLException {
        
         Produit p=(Produit)taken.getSelectionModel().getSelectedItem();
         VenteFlashService venteflashservice;
          venteflashservice = new VenteFlashService();
          if (p==null){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("probleme de supression");
            alert.setHeaderText("pas de produit choisit");
            alert.setContentText("selectionner un element ");
            alert.show();
            return;
            
        }
          else  
          {TrayNotification tray = new TrayNotification("sucess","supression avec sucess",NotificationType.SUCCESS);
                                   tray.showAndDismiss(Duration.seconds(5));}
          venteflashservice .supprimerproduit(p);
          again();

    }
    
    
 
      public void again() {
          
         VenteFlashService venteflashservice;
          venteflashservice = new VenteFlashService();
        free = venteflashservice.produitdslibres();
        System.out.print(free);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        prixflash.setCellValueFactory(new PropertyValueFactory<>("prixflash"));
        action.setCellFactory((param) -> new ButtonCell());
         VenteFlashService s=new VenteFlashService();
                    
        disponibles.setItems(free);
        free.size();
                engaged = venteflashservice.taken(idd);
                        System.out.println(engaged.size());

		nom1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		prix1.setCellValueFactory(new PropertyValueFactory<>("prixflash"));
                    
          
           

          

   
                        engaged.size();
                        
               taken.setItems(engaged);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         
        VenteFlashService s = new VenteFlashService();
     try {
         VenteFlash f = s.getByid(idd);
         this.flash.setText(""+f.getNom() );
     } catch (SQLException ex) {
         Logger.getLogger(GestionFlashController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        again();
    }
     

    
       
   private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final JFXButton cellButton = new JFXButton(" prix flash");       
              
        ButtonCell(){
            this.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        cellButton.setOnAction((t) -> {
               
                Produit current = (Produit) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
TextInputDialog dialog = new TextInputDialog("prix flash");

dialog.setTitle("choisir prix flash");
dialog.setContentText("prix flash:");

// Traditional way to get the response value.
Optional<String> result = dialog.showAndWait();

        if ( Float.parseFloat(result.get())>current.getPrixProduit()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("prix élevé !!");
            alert.setContentText("prixflash superieur au prix  ");
             alert.showAndWait();
            return;
            
        }
  
if (result.isPresent()){

      VenteFlashService venteflashservice;
          venteflashservice = new VenteFlashService();
        venteflashservice.setFlash(current,Float.parseFloat(result.get()));
         
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);

            a.showAndWait();
}
 
            again(); });
           

        }

       
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }}}
   public void retour() throws IOException
   {
      Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/FlashList.fxml"));
		
		Scene scene = new Scene(root);
	 
		primaryStage.setScene(scene);
		primaryStage.show();
                 Stage stage = (Stage) add.getScene().getWindow();
        // do what you have to do
        stage.close();

			 

   
   
   
   
   }   
   
   
   

    
}