/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import dao.CategorieUtil;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ProduitService;
import services.PieChartSample;
import util.DatabaseConnection;
import view.UserConnection;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AllProductController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    TableColumn<Produit, Float> Prix;
    @FXML
    TableColumn<Produit, Float> Description;
    @FXML
    TableColumn<Produit, String> Name;
    @FXML
    TableColumn<Produit, String> Marque;
    @FXML
    TableColumn<Produit, String> Etat;
    @FXML
    TableColumn<Produit, String> Stock;
    @FXML
    private Label lblSearch, lblUser;
    @FXML
    private Label lblMaxPrix;
    @FXML
    private Label lblMinPrix;
    @FXML
    private JFXSlider prixMinSlider;
    @FXML
    private JFXSlider prixMaxSlider;
    @FXML
    private JFXComboBox cmbCategorie;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnNextPhoto;
    @FXML
    private JFXHamburger btnHamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    JFXButton btnLogout;
    @FXML
    JFXButton btnStatistique;

    ProduitService produitservice = new ProduitService();
    CategorieUtil categorieUtil = new CategorieUtil();
    Integer i = 1;
    ResultSet rs = null;
    Connection con = DatabaseConnection.getInstance().getConnection();
    @FXML
    private JFXButton mycart;
    @FXML
    private JFXButton slt;
    @FXML
    private Label label;
    @FXML
    private Label qnt;
    @FXML
    private Label prixu;
    @FXML
    private Label cid_prod;

    @FXML
    public void NextPhoto() {

        if (table.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (i == 1) {
            new HomeController().imageDisplay(imgView, table, "image_name2");
            i++;
        } else if (i == 2) {
            new HomeController().imageDisplay(imgView, table, "image_name3");
            i++;
        } else if (i == 3) {
            new HomeController().imageDisplay(imgView, table, "image_name");
            i = 1;
        }
    }

    /**
     * Initializes the controller class.
     */
    private void setcellValue() {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Produit pl = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                label.setText(pl.getLibelle());
                qnt.setText(Integer.toString(pl.getQuantiteStock()));
                cid_prod.setText(Integer.toString(pl.getId()));
                prixu.setText(Double.toString(pl.getPrixProduit()));
                slt.setDisable(false);

            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("el id "+UserConnectionController.user.getId());
        mycart.setText("Mon Panier (" + String.valueOf(GetUserCartCount()) + ")");

        lblUser.setText(UserConnectionController.user.getNom());

        lblMaxPrix.setText(String.valueOf(produitservice.maxPrice()));
        lblMinPrix.setText(String.valueOf(produitservice.maxPrice()));
        prixMaxSlider.setMax(produitservice.maxPrice());
        prixMinSlider.setMax(produitservice.maxPrice());
        cmbCategorie.setItems(categorieUtil.listerCategorie());
        new HomeController().tableUpdate(table, Stock, Etat, Marque, Name, Description, Prix);
        new HomeController().Mousepress(table, imgView);
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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

        txtSearch.setOnKeyReleased(e -> {

            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByName(txtSearch.getText()));
        });
        cmbCategorie.setOnAction(e -> {
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByCategorie(cmbCategorie.getValue().toString()));

        });

        prixMaxSlider.setOnMouseClicked(e -> {
            lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        prixMinSlider.setOnMouseClicked(e -> {
            lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
            new HomeController().tableAfterSearch(table, Stock, Etat, Marque, Name, Description, Prix, produitservice.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });
        btnLogout.setOnAction(e -> {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
            new UserConnection().start(stage);

        });
        btnStatistique.setOnAction(e -> {

            Stage stage = new Stage();
            new PieChartSample().inserData(stage, produitservice.Nouveau(), produitservice.Occasion());
        });
        setcellValue();

    }

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
    private void sltButtonAction(ActionEvent event) {
        int quantite = qte();
        int id_prod = Integer.valueOf(cid_prod.getText());
        float prix = Float.valueOf(prixu.getText());
        String nom_prod = label.getText();
        int count = 0;
        int nouvquan = 0;
        ajoutprodpanier(quantite, id_prod, prix, nom_prod, count, nouvquan);
    }

    public void ajoutprodpanier(int quantite, int id_prod, float prix, String nom_prod, int count, int nouvquan) {
        try {
            // create a mysql database connection

            Statement stmt = con.createStatement();

            rs = stmt.executeQuery("select COUNT(*) AS count from panier where id_prod =" + id_prod);
            while (rs.next()) {
                count = rs.getInt("count");

            }
            rs = stmt.executeQuery("select * from panier where id_prod =" + id_prod);
            while (rs.next()) {

                nouvquan = rs.getInt("qte_pan") + quantite;
            }
            // note that i'm leaving "date_created" out of this insert statement

            if (count == 0) {
                String sql = ("INSERT INTO `panier` ( `id_prod`, `id_user`, `qte_pan`, `prix_tot`, `prix_unitaire`, `nom_prod`) VALUES (?,?,?,?,?,?)");
                PreparedStatement pstmt = con.prepareStatement(sql);
                float ptot = quantite * prix;
                pstmt.setInt(1, id_prod);
                pstmt.setInt(2, UserConnectionController.user.getId());

                pstmt.setInt(3, quantite);
                pstmt.setFloat(4, ptot);
                pstmt.setFloat(5, prix);
                pstmt.setString(6, nom_prod);

                pstmt.executeUpdate();
            }
            if (count > 0) {
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

    public int qte() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Quantiter");
        dialog.setHeaderText("Donner la Quantiter ");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        int qnte = Integer.valueOf(qnt.getText());
        int quantit = Integer.parseInt(result.get());
        try {
            if (result.isPresent() && ((qnte > quantit) || (qnte == quantit))) {
                return quantit;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("");
                alert.setContentText("Ooops, Quantite Incorrecte!");

                alert.showAndWait();
                return 0;
            }
        } catch (NumberFormatException e) {

            return 0;
        }

// The Java 8 way to get the response value (with lambda expression).
    }

    public int GetUserCartCount() {
        int count = 0;
        try {

            Statement stmt = con.createStatement();

            rs = stmt.executeQuery("select SUM(qte_pan) AS count from panier where id_user =" + UserConnectionController.user.getId());
            while (rs.next()) {
                return count;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
        //To change body of generated methods, choose Tools | Templates.
    }
}
