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
public class Commande {
    private int id_commande;
    private int id_user;
    private float commande_tot;

    public Commande(int id_commande, int id_user, float commande_tot) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.commande_tot = commande_tot;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getCommande_tot() {
        return commande_tot;
    }

    public void setCommande_tot(float commande_tot) {
        this.commande_tot = commande_tot;
    }
    
    
}
