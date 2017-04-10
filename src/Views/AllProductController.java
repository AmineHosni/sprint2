/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class AllProductController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> Name;
    @FXML
    private TableColumn<?, ?> Marque;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private TableColumn<?, ?> Etat;
    @FXML
    private TableColumn<?, ?> Stock;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private Label lblSearch;
    @FXML
    private Label lblMaxPrix;
    @FXML
    private Label lblMinPrix;
    @FXML
    private JFXSlider prixMinSlider;
    @FXML
    private JFXSlider prixMaxSlider;
    @FXML
    private JFXComboBox<?> cmbCategorie;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnNextPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
