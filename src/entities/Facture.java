/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Black SouL
 */
public class Facture {
    private int id_facture,qte_prod,meth_liv;
    private String nom_produit,Image;
    private float prix_prod,prix_tot,commande_tot;
    public Facture( float prix_prod, float commande_tot, int qte_prod, float prix_tot, String nom_produit, int meth_liv,String Image) {
      
        this.prix_prod = prix_prod;
        this.commande_tot = commande_tot;
        this.qte_prod = qte_prod;
        this.prix_tot = prix_tot;
        this.nom_produit = nom_produit;
        this.meth_liv = meth_liv;
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public float getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(float prix_prod) {
        this.prix_prod = prix_prod;
    }

    public float getCommande_tot() {
        return commande_tot;
    }

    public void setCommande_tot(float commande_tot) {
        this.commande_tot = commande_tot;
    }

    public int getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(int qte_prod) {
        this.qte_prod = qte_prod;
    }

    public float getPrix_tot() {
        return prix_tot;
    }

    public void setPrix_tot(float prix_tot) {
        this.prix_tot = prix_tot;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getMeth_liv() {
        return meth_liv;
    }

    public void setMeth_liv(int meth_liv) {
        this.meth_liv = meth_liv;
    }
            
    
}
