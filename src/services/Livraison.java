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
public interface Livraison {
      
               
    public void ValidLivraison(int userid, int id_comm, String adr, String tel,String cp,String del,String gouv,String paie,String methode); 
    public void afficheDataUser();
    }

