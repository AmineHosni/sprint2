/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import dao.ProduitUtil;
import entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.*;
import dao.CategorieUtil;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class HomeController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger btnHamburger;
    Integer idUser = 9;
    @FXML
    JFXComboBox cmbCategorie;
    @FXML
    JFXSlider prixMaxSlider, prixMinSlider;
    @FXML
    TableView<Produit> table = new TableView<Produit>();
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
    JFXButton btnModifier, btnSupprimer, btnAjouter, btnDetails,btnLogout;
    @FXML
    TextField txtStock, txtprixProduit, txtquantiteStock, txtmarque, txtlibelle;
    @FXML
    JFXTextField txtSearch;
    @FXML
    Label lblPhoto, lblDuree, lblEtat, lblMinPrix, lblMaxPrix, lblSearch,lblUser;
    @FXML
    TextArea txtdescription;
    @FXML
    ImageView imgView;
    Produit produit = new Produit();
    ProduitUtil produitUtil = new ProduitUtil();
    private Stage stage;
        ProduitService produitService = new ProduitService();

    /**
     * Initializes the controller class.
     *
     *
     *
     */
    public void imageDisplay(ImageView imgView,TableView<Produit> table,String imageUrl) {
                
        File file = new File("C:\\Users\\jamel_pc\\Desktop\\SprintJava\\"+produitUtil.returnImage(table.getSelectionModel().getSelectedItem().getId(),imageUrl));
        String img;
        try {
            img = file.toURI().toURL().toString();
            Image image = new Image(img);
              imgView.setImage(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

      
    }

    public void Mousepress(TableView<Produit> table ,ImageView imgView) {
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (table.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                imageDisplay(imgView,table,"image_name");
                if (txtprixProduit==null) {
                    
                }else {
                txtprixProduit.setText(table.getSelectionModel().getSelectedItem().getPrixProduit().toString());
                txtlibelle.setText(table.getSelectionModel().getSelectedItem().getLibelle());
                txtmarque.setText(table.getSelectionModel().getSelectedItem().getMarque());
                txtStock.setText(table.getSelectionModel().getSelectedItem().getQuantiteStock().toString());
                txtdescription.setText(table.getSelectionModel().getSelectedItem().getDescription());
            }
            }
        });
    }
public void tableUpdate(TableView<Produit> table,
            TableColumn<Produit, String> Stock,TableColumn<Produit, String> Etat
        ,TableColumn<Produit, String> Marque,TableColumn<Produit, String> Name           
        ,    TableColumn<Produit, Float> Description ,
        TableColumn<Produit, Float>Prix   
) {
        ObservableList<Produit> listProduit = produitUtil.afficher();
        
        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();
    }
    public void tableUpdate(Integer IdUser) {
        ObservableList<Produit> listProduit = produitUtil.afficher(IdUser);
        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();
    }

    public void tableAfterSearch(TableView<Produit> table,
            TableColumn<Produit, String> Stock,TableColumn<Produit, String> Etat
        ,TableColumn<Produit, String> Marque,TableColumn<Produit, String> Name           
        ,    TableColumn<Produit, Float> Description ,
        TableColumn<Produit, Float>Prix,ObservableList listProduit) {
        
        Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        Name.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(listProduit);
        table.setTableMenuButtonVisible(true);
        table.refresh();
    }
  

    void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void supprimer() {
        if (table.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Integer i = table.getSelectionModel().getSelectedItem().getId();
        produitUtil.supprimerObject(i);
        this.tableUpdate(9);
    }
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUser.setText("Jamel Mustapha");
        CategorieUtil categorieUtil = new CategorieUtil();
        lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
        lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
        prixMaxSlider.setMax(produitService.maxPrice());
        prixMinSlider.setMax(produitService.maxPrice());
        tableUpdate(idUser);
        Mousepress(table,imgView);
        
          
        txtSearch.setOnKeyReleased(e -> {
            if (txtSearch.getText().equals("")) {
                this.tableUpdate(9);
            }else
            tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitService.SearchByName(txtSearch.getText()));
        });
        cmbCategorie.setOnAction(e -> {
            tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitService.SearchByCategorie(cmbCategorie.getValue().toString()));

        });

        prixMaxSlider.setOnMouseClicked(e -> {
            lblMaxPrix.setText(String.valueOf(prixMaxSlider.getValue()));
            prixMinSlider.setMax(prixMaxSlider.getValue());
            tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitService.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });

        prixMinSlider.setOnMouseClicked(e -> {
            lblMinPrix.setText(String.valueOf(prixMinSlider.getValue()));
            tableAfterSearch(table,Stock,Etat,Marque,Name,Description,Prix,produitService.SearchByPrice(prixMinSlider.getValue(), prixMaxSlider.getValue()));
        });
        cmbCategorie.setItems(categorieUtil.listerCategorie());
        btnModifier.setOnMouseClicked(e -> {
            if (table.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            Integer i = table.getSelectionModel().getSelectedItem().getId();
            produit.setPrixProduit(Double.parseDouble(txtprixProduit.getText()));
            produit.setLibelle(txtlibelle.getText());
            produit.setMarque(txtmarque.getText());
            produit.setQuantiteStock(Integer.parseInt(txtStock.getText()));
            produit.setDescription(txtdescription.getText());
            produit.setId(i);
            produitUtil.modifierObject(produit);
            this.tableUpdate(9);

        });
         
        btnAjouter.setOnMouseClicked(e -> {

            AddProduct addProduct = new AddProduct(this);
            Stage stage = new Stage();
            try {
                addProduct.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        
        btnDetails.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem().getId() != null) {
                DetailsProduct detailsProduct = new DetailsProduct(this, table.getSelectionModel().getSelectedItem());

                try {
                    Stage stage = new Stage();
                    detailsProduct.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("null");
            }

        });
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            
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
            btnLogout.setOnAction(e->{
                Stage stage = (Stage)btnLogout.getScene().getWindow();
                stage.close();
                new UserConnection().start(stage);
            });
            
    }

   

}
