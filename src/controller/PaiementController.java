/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
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
public class PaiementController implements Initializable {

    @FXML
    private ToggleGroup transporteur;
    @FXML
    private Button payer;
    @FXML
    private JFXRadioButton cash;
    @FXML
    private JFXRadioButton paypal;
    @FXML
    private JFXTextArea com_cash;
    @FXML
    private JFXTextArea com_cpaypal;
     ArrayList al = new ArrayList();
     private String methode  = "cash";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void PaiementMeth(ActionEvent event) throws IOException {
        al.add(methode);
        ((Node) (event.getSource())).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Confirmer.fxml"));
                    loader.load();
                    ConfirmerController display = loader.getController();
                    display.setTexte(al);
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();  
    }


    @FXML
    private void SelectPai(ActionEvent event) {
             if(cash.isSelected()){
            methode = "cash";
        }
      if(paypal.isSelected()){
        methode = "paypal";

      }
    }
    
      public ArrayList setTexte(ArrayList list){
        for(Iterator it=list.iterator();
                it.hasNext();){
             al.add(it.next());
        } 
        return list;
    }
    
}
