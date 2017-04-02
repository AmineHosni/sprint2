
import entities.Produit;
import java.io.File;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import services.ProduitService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jamel_pc
 */
@RunWith(JUnit4.class)
public class TestProduit {
    
    @Test
    public void should_add_product_with_image(){
        ProduitService produitService = new ProduitService();
        Produit produit = null ;
        boolean result = produitService.ajouterProduit(produit);
        
        Assert.assertTrue(result);
        
        
    }
    
}
