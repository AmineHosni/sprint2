/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Produit;
import services.Produits;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.ConnextionSingleton;


/**
 *
 * @author Black SouL
 */
public class ListeProduitsController implements Initializable,Produits {

    @FXML
    private Label label;
 
    @FXML
    private JFXButton slt;
    @FXML
    private TableView<Produit> tableproduit;
    @FXML
    private TableColumn<?, ?> cid;
    @FXML
    private TableColumn<?, ?> cnom;
    private TableColumn<?, ?> cemail;
    private TableColumn<?, ?> cadr;
    private ObservableList<Produit> data;
    
    @FXML
    private Label cid_prod;
    @FXML
    private JFXButton mycart;
    private int id_user = 1;
    @FXML
    private Label prixu;
    @FXML
    private TableColumn<?, ?> cprix;
    @FXML
    private TableColumn<?, ?> cqte;
   Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
    @FXML
    private Label qnt;
           


     @FXML
    private void MyCartAction(ActionEvent event) throws IOException, SQLException {
           ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Panier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
    

    @FXML
    public void sltButtonAction(ActionEvent event) {
   int quantite = qte();
            int id_prod = Integer.valueOf(cid_prod.getText());
            float prix = Float.valueOf(prixu.getText());
            String nom_prod = label.getText();
            int count = 0;
            int nouvquan = 0;
            ajoutprodpanier(quantite,id_prod,prix,nom_prod,count,nouvquan);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mycart.setText("Mon Panier (" + String.valueOf(GetUserCartCount()) + ")");
        setsCllTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        
        setcellValue();

    }

    public void setsCllTable() {
        cid.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
        cprix.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
        cqte.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
   

    }

    private void loadDataFromDatabase() {
     afficheProduit();

    }

    private void setcellValue() {
        tableproduit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Produit pl = tableproduit.getItems().get(tableproduit.getSelectionModel().getSelectedIndex());
                label.setText(pl.getLibelle());
                qnt.setText(Integer.toString(pl.getQuantiteStock()));
                cid_prod.setText(Integer.toString(pl.getQuantiteStock()));
                prixu.setText(Double.toString(pl.getPrixProduit()));
                slt.setDisable(false);

            }
        });
    }

    @Override
    public int GetUserCartCount() {
          int count = 0;
        try {

                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("select SUM(qte_pan) AS count from panier where id_user =" + id_user);
            while (rs.next()) {
                System.out.println(count = rs.getInt("count"));
                return count;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficheProduit() {
           try {
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM produit where quantiteStock > 0");
            //ss
            while (rs.next()) {

                int id = rs.getInt("id");
                String nomproduit = rs.getString("libelle");
                Double prix = rs.getDouble("prixProduit");
                int qte = rs.getInt("quantiteStock");

                // print the results
                data.add(new Produit(id, prix, qte, nomproduit));
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableproduit.setItems(data);
    }

    @Override
    public void ajoutprodpanier(int quantite, int id_prod,float prix, String nom_prod, int count, int nouvquan) {
        try {
            // create a mysql database connection
        
                Statement stmt = con.createStatement();
               
            rs = stmt.executeQuery("select COUNT(*) AS count from panier where id_prod =" + id_prod);
            while (rs.next()) {
                count = rs.getInt("count");
              
            }
                 rs = stmt.executeQuery("select* from panier where id_prod =" + id_prod);
            while (rs.next()) {
                
               nouvquan= rs.getInt("qte_pan")+quantite;
            }
            // note that i'm leaving "date_created" out of this insert statement
         

        if(count == 0){
            String sql = ("INSERT INTO `panier` ( `id_prod`, `id_user`, `qte_pan`, `prix_tot`, `prix_unitaire`, `nom_prod`) VALUES (?,?,?,?,?,?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            float ptot = quantite * prix;
            pstmt.setInt(1, id_prod);
            pstmt.setInt(2, id_user);
            pstmt.setInt(3, quantite);
             pstmt.setFloat(4, ptot);
              pstmt.setFloat(5, prix);
                          pstmt.setString(6, nom_prod);


            pstmt.executeUpdate();
            System.out.println(id_prod + id_user);
}
         if(count >0){
             String sql = ("UPDATE `panier` SET `qte_pan` = (?), `prix_tot` = (?) WHERE `panier`.`id_prod` = (?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            Float ptot = nouvquan * prix;
            pstmt.setInt(1, nouvquan);
            pstmt.setFloat(2, ptot);
            pstmt.setInt(3, id_prod);
            pstmt.executeUpdate();
         }
         
            
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        mycart.setText("Mon Panier (" + String.valueOf(GetUserCartCount()) + ")");
    }



    private static class ObjectImpl extends Object {

        public ObjectImpl() {
        }
    }

    public int qte() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Quantiter");
        dialog.setHeaderText("Donner la Quantiter ");
      

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        int qnte = Integer.valueOf(qnt.getText());
        int quantit = Integer.parseInt(result.get());
        try {
              if (result.isPresent() && ((qnte > quantit) ||(qnte == quantit) ) ) {
            System.out.println("Your name: " + result.get());
             return quantit;
        }else{
                             Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("");
alert.setContentText("Ooops, Quantite Incorrecte!");

alert.showAndWait();  return  0;
              } 
        } catch (NumberFormatException e) {

            return  0;
        }
      

// The Java 8 way to get the response value (with lambda expression).
       
    }
}
