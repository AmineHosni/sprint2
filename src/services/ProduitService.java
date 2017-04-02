/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import dao.ProduitUtil;
import entities.Produit;
import java.io.File;
import java.util.List;

/**
 *
 * @author jamel_pc
 */
public class ProduitService {
    ProduitUtil produitUtil;
    
    public ProduitService(){
        produitUtil = new ProduitUtil();
    }
    
    public List<Produit> listProduitsOrderByCreationDate(){
        return produitUtil.listProduitsOrderByCreationDate();
    }
    
    public boolean ajouterProduit(Produit produitToAdd){
       
        
        return produitUtil.ajouterObject(produitToAdd);        
    }
    
}
