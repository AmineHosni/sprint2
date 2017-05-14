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
public class User {
    private int id_user;
    private String nom_user,email_user,adr_user;

    public User(int id_user, String nom_user, String email_user, String adr_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.email_user = email_user;
        this.adr_user = adr_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getAdr_user() {
        return adr_user;
    }

    public void setAdr_user(String adr_user) {
        this.adr_user = adr_user;
    }
    
}
