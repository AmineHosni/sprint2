/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Black SouL
 */
public interface Produits {
    public int GetUserCartCount();
    public void afficheProduit();
    public void ajoutprodpanier(int quantite, int id_prod,float prix, String nom_prod, int count, int nouvquan);
    
}
