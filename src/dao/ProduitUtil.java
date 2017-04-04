/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProduitUtil extends UtilInterface {

    public ProduitUtil() {
        initConnection();
    }

    public boolean ajouterObject(Object p) {
        Produit produit = (Produit) p;

        String INSERT_PRODUIT = "insert into produit(libelle, description, "
                + "Photo,marque,etat,prixProduit,"
                + "quantiteStock,created_date,duree) values (?, ?, ?,?,?,?,?,?"
                + ",?)";
        FileInputStream fis ;
        PreparedStatement ps ;
        try {
            fis = new FileInputStream(produit.getImageFile());
            ps = conn.prepareStatement(INSERT_PRODUIT);
            ps.setString(1, produit.getLibelle());
            ps.setString(2, produit.getDescription());
            ps.setBinaryStream(3, fis, (int) produit.getImageFile().length());
            ps.setString(4, produit.getMarque());
            ps.setString(5, produit.getEtat());
            ps.setDouble(6, produit.getPrixProduit());
            ps.setInt(7, produit.getQuantiteStock());
            ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setInt(9, produit.getDuree());
            ps.executeUpdate();
        } catch (SQLException | FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }

        return true;
    }

    @Override
    public boolean modifierObject(Object p) {
        Produit produit = (Produit) p;
        PreparedStatement ps = null;

        int i = 0;
        try {
            String MODIF_Produit = "  UPDATE `produit` SET `libelle`=?,"
                    + "`description`=?,`marque`=?,"
                    + "`prixProduit`=?,`quantiteStock`=? ,`updated_at`=? WHERE `id`=?";
            ps = conn.prepareStatement(MODIF_Produit);
            ps.setString(1, produit.getLibelle());
            ps.setString(2, produit.getDescription());
            ps.setString(3, produit.getMarque());
            ps.setDouble(4, produit.getPrixProduit());
            ps.setInt(5, produit.getQuantiteStock());
            ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setInt(7, produit.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean supprimerObject(Integer i) {
        try {
            String Delete_Produit = "DELETE FROM `produit` WHERE `id`=" + i;
             stmt.executeUpdate(Delete_Produit);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public ObservableList<Produit> afficher() {
        Produit p = new Produit();
        String req3 = "select * from produit ";
        ObservableList<Produit> products = FXCollections.observableArrayList();

        try {

            ResultSet res = stmt.executeQuery(req3);
            while (res.next()) {
                Produit produit = new Produit();

                produit.setId(res.getInt("id"));
                produit.setLibelle(res.getString("libelle"));
                produit.setDescription(res.getString("description"));
                produit.setMarque(res.getString("marque"));
                produit.setEtat(res.getString("etat"));
                produit.setPrixProduit(res.getDouble("prixProduit"));
                produit.setQuantiteStock(res.getInt("quantiteStock"));
                produit.setCreatedDate(res.getDate("created_date"));
                produit.setDuree(res.getInt("duree"));
                produit.setApprouver(res.getString("approuver"));
                produit.setSeller(res.getInt("seller"));
                produit.setUpdatedAt(res.getDate("updated_at"));

                Blob blob = res.getBlob("Photo");
                InputStream is = blob.getBinaryStream();

                products.add(produit);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public InputStream returnImage(int i) {
        InputStream image = null;
        try {
            String req3 = "select * from produit where " + i;
            ResultSet res = stmt.executeQuery(req3);
            System.out.println(res);

            while (res.next()) {
                image = res.getBinaryStream("Photo");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    public Produit afficherUnProduit(Integer p) {
        Produit produit = new Produit();
        String req3 = "select * from produit Where `id`=" + p;
        try {
            ResultSet res = stmt.executeQuery(req3);
            res.next();
            produit.setId(res.getInt("id"));
            produit.setLibelle(res.getString("libelle"));
            produit.setDescription(res.getString("description"));
            produit.setMarque(res.getString("marque"));
            produit.setEtat(res.getString("etat"));
            produit.setPrixProduit(res.getDouble("prixProduit"));
            produit.setQuantiteStock(res.getInt("quantiteStock"));
            produit.setCreatedDate(res.getDate("created_date"));
            produit.setDuree(res.getInt("duree"));
            produit.setApprouver(res.getString("approuver"));
            produit.setSeller(res.getInt("seller"));
            if (produit.getImageFile() == null) {
                System.out.println("null");
            } else {
                System.out.println("not null");
            }
            produit.setUpdatedAt(res.getDate("updated_at"));

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit;
    }
    @Override
    public void afficherObject() {
        String req3 = "select * from produit";

        try {

            ResultSet res = stmt.executeQuery(req3);
            while (res.next()) {

                String id = res.getString("id");
                String libelle = res.getString("libelle");
                String description = res.getString("description");
                String marque = res.getString("marque");
                String etat = res.getString("etat");
                String prixProduit = res.getString("prixProduit");
                String quantiteStock = res.getString("quantiteStock");
                String pourcentagePromotion = res.getString("pourcentagePromotion");
                String created_date = res.getString("created_date");
                String duree = res.getString("duree");
                String approuver = res.getString("approuver");
                String seller = res.getString("seller");
                String image_name = res.getString("image_name");
                String updated_at = res.getString("updated_at");
                System.out.format("%s, %s , %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                        id, libelle, description, marque, etat, prixProduit, quantiteStock, pourcentagePromotion, created_date, duree, approuver, seller, image_name, updated_at);

            }

            //  }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Produit> listProduitsOrderByCreationDate() {
        List<Produit> listProduits = null;
        try {
            String query = "select * from produit order by created_date desc";
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println(resultSet);
            while (resultSet.next()) {
                listProduits = new ArrayList<>();

                Produit produit = new Produit();
                produit.setId(resultSet.getInt("id"));
                produit.setLibelle(resultSet.getString("libelle"));
                produit.setDescription(resultSet.getString("description"));
                produit.setMarque(resultSet.getString("marque"));
                produit.setEtat(resultSet.getString("etat"));

                listProduits.add(produit);
            }

            System.out.println(listProduits);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduits;
    }

}
