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
public class DetailsCommande {
    private int id_detcom,id_prod,id_commande,qte_prod;

    public DetailsCommande(int id_detcom, int id_prod, int id_commande, int qte_prod) {
        this.id_detcom = id_detcom;
        this.id_prod = id_prod;
        this.id_commande = id_commande;
        this.qte_prod = qte_prod;
    }

    public int getId_detcom() {
        return id_detcom;
    }

    public void setId_detcom(int id_detcom) {
        this.id_detcom = id_detcom;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(int qte_prod) {
        this.qte_prod = qte_prod;
    }
    
}
