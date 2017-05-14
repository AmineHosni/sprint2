/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Coupon;
import util.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jamel_pc
 */
public class CouponUtil extends UtilInterface{       
    
    public CouponUtil() {
        initConnection();
    }
    
    
    @Override
    public boolean ajouterObject(Object p) {
        Coupon coupon = (Coupon)p;
        int i= 0;
        try {
            String req="INSERT INTO `Coupon` (`reference`, `img`) "
                    + "VALUES ('"+coupon.getReference()+"', "+coupon.getImg()+")";
            
         i= statement.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CouponUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i>0)
            return true;
        
             return  false;
    }


    
    
    @Override
    public boolean modifierObject(Object p) {
        Coupon coupon = (Coupon)p;
        int i= 0;
        try {
            String req="UPDATE `coupon` SET `reference`="+coupon.getReference()+
                    ",`img`='"+coupon.getImg()+"' WHERE "+coupon.getId();     
            
         i= statement.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CouponUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     return true;
    }


    

    @Override
    public boolean supprimerObject(Integer i) {


     //   Coupon coupon = (Coupon)p;
      //  int i= 0;
        try {
            String req="DELETE FROM `coupon` WHERE "+i;
         i= statement.executeUpdate(req);
         return true;       
        
        } catch (SQLException ex) {
            Logger.getLogger(CouponUtil.class.getName()).log(Level.SEVERE, null, ex);
        return  false;
        }
       
    }

    @Override
    public void afficherObject() {
        
        try {
            String req3="select * from Coupon";
            ResultSet res =  statement.executeQuery(req3);
            while (res.next()) {                
           String reference = res.getString("reference");
        String image = res.getString("img");

        System.out.format("%s, %s \n",  reference, image);

            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Coupon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
