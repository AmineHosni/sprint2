/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import dao.ProduitUtil;
import entities.Produit;
import java.util.List;
import javafx.collections.ObservableList;
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
    
    public ObservableList<Produit> SearchByName(String search){
       
        
        return produitUtil.SearchByName(search);
    }
     public ObservableList<Produit> SearchByPrice(Double minPrix,Double maxPrix){
       
        
        return produitUtil.SearchByPrice( minPrix, maxPrix);
    }
     public Double maxPrice() {
     
     return produitUtil.maxPrice();
     
     }

    public ObservableList SearchByCategorie(String categorie) {
        return produitUtil.SearchByCategorie(categorie);
    }
    
}
