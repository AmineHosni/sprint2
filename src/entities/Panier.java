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
public class Panier {
    private int id_panier,id_user,id_prod,qte_pan;
    private String nom_prod,Image;
    private float prix_unitaire,prix_tot;

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public float getPrix_tot() {
        return prix_tot;
    }

    public void setPrix_tot(float prix_tot) {
        this.prix_tot = prix_tot;
    }

    public Panier(int id_panier, int id_user, int id_prod, int qte_pan, float prix_unitaire, float prix_tot, String nom_prod, String Image) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_prod = id_prod;
        this.qte_pan = qte_pan;
        this.prix_unitaire = prix_unitaire;
        this.prix_tot = prix_tot;
        this.nom_prod = nom_prod;
          this.Image = Image;
    }



   

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getQte_pan() {
        return qte_pan;
    }

    public void setQte_pan(int qte_pan) {
        this.qte_pan = qte_pan;
    }
    
}
