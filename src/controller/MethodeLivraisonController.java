/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Black SouL
 */
public class MethodeLivraisonController implements Initializable {

    @FXML
    private JFXRadioButton aramex;
    @FXML
    private ToggleGroup transporteur;
    @FXML
    private JFXRadioButton poste;
    @FXML
    private Button payer;
    private String Transporteure = "aramex";
 ArrayList al = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    

    @FXML
    private void SelectTrans(ActionEvent event) {
        if(aramex.isSelected()){
            Transporteure = "aramex";
        }
      if(poste.isSelected()){
        Transporteure = "poste";

      }
    
}

    @FXML
    private void PaiementMeth(ActionEvent event) throws IOException {
        al.add(Transporteure);
        ((Node) (event.getSource())).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Paiement.fxml"));
                    loader.load();
                    PaiementController display = loader.getController();
                    display.setTexte(al);
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();         
    }


    
    
    public ArrayList setTexte(ArrayList list){
        for(Iterator it=list.iterator();
                it.hasNext();){
               al.add(it.next());
        } 
        return list;
    }
}
