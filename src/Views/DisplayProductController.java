/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.*;
import dao.*;
/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class DisplayProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label lblnomproduit,lblprixprodduit;
    @FXML
    ImageView produitphoto;
    
           
      
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        Produit p = new Produit();
        ProduitUtil pr = new ProduitUtil();
        Integer n = new Integer(43);
        p = pr.afficherUnProduit(n);
        lblnomproduit.setText(p.getLibelle());
        lblprixprodduit.setText(p.getPrixProduit().toString());
        
        
       
       
    }
    
    public void getphoto() {
         
        Produit p = new Produit();
        ProduitUtil pr = new ProduitUtil();
        Integer n = new Integer(43);
        p = pr.afficherUnProduit(n);
        System.out.println(p.getLibelle());
        
       
    }
    
}
