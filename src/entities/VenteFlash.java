/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author Salma
 */
public class VenteFlash {
     private int id;
     private String image;

    public VenteFlash(int id, String nom, String description, Date Datedebut, Date Datefin,String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.Datedebut = Datedebut;
        this.Datefin = Datefin;
        this.image=image;
        
    }

   

    VenteFlash(String name, String desc, Date debut, Date fin) {
           this.nom = name;
        this.description = desc;
        this.Datedebut =debut;
        this.Datefin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     private String nom;
     private String description;
     private Date Datedebut ;
     private Date Datefin ;
  


      private String theme;

    public VenteFlash( String nom, String description, Date Datedebut, Date Datefin,String image) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.Datedebut = Datedebut;
        this.Datefin = Datefin;
        this.image=image;
    }
   
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDatedebut() {
        return Datedebut;
    }

    public void setDatedebut(Date Datedebut) {
        this.Datedebut = Datedebut;
    }

    public Date getDatefin() {
        return Datefin;
    }

    public void setDatefin(Date Datefin) {
        this.Datefin = Datefin;
    }

    @Override
    public String toString() {
        return "VenteFlash{" + "nom=" + id + ", description=" + description + ", theme=" + theme + ", Datedebut=" + Datedebut + ", Datefin=" + Datefin + '}';
    }

     
}