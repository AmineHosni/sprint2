/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;

/**
 *
 * @author Black SouL
 */
public interface Panier {
    public void affichePanier();
    public void supPanier();
    public void modifPanier()throws SQLException;
}
