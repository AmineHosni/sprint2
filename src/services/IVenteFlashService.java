/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.VenteFlash;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Salma
 */
public interface IVenteFlashService {
    
    public boolean ajouterVenteFlash(VenteFlash f);
    public boolean modifierVenteFlash(VenteFlash f ,String nom,String description,Date debut,Date fin );
    public boolean supprimerVenteFlash(VenteFlash f);
    public List<VenteFlash> afficherVenteFlash();
 
}
