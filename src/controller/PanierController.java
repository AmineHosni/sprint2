/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Panier;
import java.io.IOException;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class PanierController implements Initializable,services.Panier {

    @FXML
    private TableView<Panier> table_panier;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom_p;
    @FXML
    private TableColumn<?, ?> qte_p;
    @FXML
    private TableColumn<?, ?> pu_p;
    @FXML
    private TableColumn<?, ?> put_p;
    @FXML
    private JFXButton pc;
     private ObservableList<Panier> data;
     ArrayList al = new ArrayList();
    @FXML
    private JFXButton ep;
    private int id_user = UserConnectionController.user.getId();
  private int nbrelist = 0;
    @FXML
    private Label prodid;
    @FXML
    private JFXButton lps;
    @FXML
    private JFXTextField qte;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton BtnMod;
    @FXML
    private Label prix;
    //private Image image;
   
    private int idp =0 ;
    private int qp = 0;
    @FXML
    private Label photo;
    String photos;
    Connection con = DatabaseConnection.getInstance().getConnection();
              ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setsCllTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        
        setcellValue();
        nbrelist = table_panier.getItems().size();
        
   

        // TODO
    }
    public void setsCllTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        qte_p.setCellValueFactory(new PropertyValueFactory<>("qte_pan"));
        pu_p.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        put_p.setCellValueFactory(new PropertyValueFactory<> ("prix_tot"));
    }
    private void loadDataFromDatabase() {
affichePanier();
    }

    private void setcellValue() {
        table_panier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Panier pl = table_panier.getItems().get(table_panier.getSelectionModel().getSelectedIndex());
              prodid.setText(Integer.toString(pl.getId_prod()));
              prix.setText(Float.toString(pl.getPrix_unitaire()));
              qte.setVisible(true);
              BtnMod.setVisible(true);
              photo.setText(pl.getImage());
              //photos = photo.getText();
              //image = new Image("/Images/"+photos);
              //img.setImage(image);
              qte.setText(Integer.toString(pl.getQte_pan()));
            }
        });
    }

    @FXML
    private void SuppProd(ActionEvent event)  {
     supPanier();
    }

    @FXML
    private void ModifQuan(ActionEvent event) throws SQLException {
    
      modifPanier();
    }

   

    @FXML
    private int testdigit() {
        int nouvquan = Integer.valueOf(qte.getText());
        try {
          
           
             return nouvquan;
        
        } catch (NumberFormatException e) {
            return  nouvquan = 0;
        }
      
    }

    @Override
    public void affichePanier() {
        try {
          
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT image_name, panier.id , produit.id ,qte_pan, prix_unitaire, prix_tot, nom_prod FROM panier,produit where produit.id = panier.id_prod and panier.id_user =" + UserConnectionController.user.getId());
//ss image_name
            while (rs.next()) {

                int id_panier = rs.getInt("panier.id");
                int id_produit = rs.getInt("produit.id");
                int qte = rs.getInt("qte_pan");
                int prix_prod = rs.getInt("prix_unitaire");
                int prix_tot = rs.getInt("prix_tot");
                photos = rs.getString("image_name");
                String nom_prod = rs.getString("nom_prod");

                // print the results
                data.add(new Panier(id_panier, id_user, id_produit, qte, prix_prod,prix_tot,nom_prod,photos));
            }
           
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        table_panier.setItems(data);
        if(table_panier.getItems().size() == 0){
        pc.setDisable(true);
    }
    }

    @Override
    public void supPanier() {
           try {
  
                Statement stmt = con.createStatement();
            int id_prod = Integer.valueOf(prodid.getText());
            String sql = ("DELETE FROM `panier` where id_prod = (?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id_prod);
            pstmt.executeUpdate();
                   
           
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
     data.clear();
  loadDataFromDatabase();
        nbrelist = table_panier.getItems().size();
    }

    @Override
    public void modifPanier() throws SQLException{
         int qte_produit =0;
            int id_prod = Integer.valueOf(prodid.getText());
          int nouvquan = testdigit();
          float prixu = Float.valueOf(prix.getText());
         
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT produit.id, quantiteStock FROM panier,produit where produit.id = panier.id_prod and panier.id_user =" + id_user);
            //ss
            while (rs.next()) {
                qte_produit = rs.getInt("quantiteStock");
              
            if(qte_produit  >= nouvquan){
                     String sql = ("UPDATE `panier` SET `qte_pan` = (?), `prix_tot` = (?) WHERE `panier`.`id_prod` = (?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            float ptot = nouvquan * prixu;
            pstmt.setInt(1, nouvquan);
            pstmt.setFloat(2, ptot);
            pstmt.setInt(3, id_prod);
            pstmt.executeUpdate();
           }
            
           data.clear();
  loadDataFromDatabase();
        nbrelist = table_panier.getItems().size();
            }
            if(qte_produit  < nouvquan){
                Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("");
alert.setContentText("Ooops, Quantité Incorrecte!");

alert.showAndWait();
            }
            
    }



    private static class ObjectImpl extends Object {

        public ObjectImpl() {
        }
    }

 


private void handleButtonAction(ActionEvent event) {
        
    }
     @FXML
    private void ReturnAction(ActionEvent event) throws IOException {
           ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AllProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
      @FXML
    private void PasserCommandeAction(ActionEvent event) throws IOException, SQLException {
        int count = 0;
        int oldqte = 0;
int idc = 0;
              ResultSet res = null;
                Statement stmt = con.createStatement();
               rs = stmt.executeQuery("select SUM(prix_tot) AS count from panier where id_user =" + UserConnectionController.user.getId());
            while (rs.next()) {
               count = rs.getInt("count");
               
            }
        String sql = ("INSERT INTO `commande` (`id_user`, `commande_tot`) VALUES (?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id_user);
            pstmt.setInt(2, count);
            pstmt.executeUpdate();
             rs = stmt.executeQuery("Select max(id_commande) as cmid from commande");
                while (rs.next()) {
              idc = rs.getInt("cmid");
                
                }
                rs = stmt.executeQuery("select * from panier,produit where panier.id_prod = produit.id and panier.id_user  = " + id_user);
            while (rs.next()) {
                 idp = rs.getInt("id_prod");
                
                 qp = rs.getInt("qte_pan");
                   String req = ("INSERT INTO `details_commande` (`id_prod`, `id_commande`, `qte_prod`) VALUES (?,?,?)");
            PreparedStatement stmtt = con.prepareStatement(req);
            stmtt.setInt(1, idp);       
            stmtt.setInt(2, idc);
            stmtt.setInt(3, qp);
            stmtt.executeUpdate();
            
              oldqte = rs.getInt("quantiteStock");
             int newQte = oldqte -qp;
            String reqq ="Update produit Set quantiteStock = (?) where id = (?)";
            
            PreparedStatement stmet = con.prepareStatement(reqq);
            stmet.setInt(1, newQte);       
            stmet.setInt(2, idp);
            stmet.executeUpdate();
            }
            res = stmt.executeQuery("SELECT * FROM `produit` where id = " + idp);
             while (res.next()) {
           
             }
             al.add(idc);
                 String sqle = ("DELETE FROM `panier` where id_user = (?)");
            PreparedStatement pstmte = con.prepareStatement(sqle);
            pstmte.setInt(1, id_user);
            pstmte.executeUpdate();
        
              
           ((Node) (event.getSource())).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/ShippingOrder.fxml"));
                    loader.load();
                    ShippingOrderController display = loader.getController();
                    display.setTexte(al);
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show(); 
    }
}
