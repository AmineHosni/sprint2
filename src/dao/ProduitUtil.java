/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Produit;
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
                + "image_name,marque,etat,prixProduit,"
                + "quantiteStock,created_date,duree,produitCategorie,image_name2,image_name3) values (?,?,?,?, ?, ?,?,?,?,?,?"
                + ",?)";
        
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_PRODUIT);
            ps.setString(1, produit.getLibelle());
            ps.setString(2, produit.getDescription());
            ps.setString(3, produit.getImageName());
            ps.setString(4, produit.getMarque());
            ps.setString(5, produit.getEtat());
            ps.setDouble(6, produit.getPrixProduit());
            ps.setInt(7, produit.getQuantiteStock());
            ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setInt(9, produit.getDuree());
            ps.setInt(10, produit.getProduitCategorie());
            ps.setString(11, produit.getImageName2());
            ps.setString(12, produit.getImageName3());
            ps.executeUpdate();
        } catch (SQLException ex) {
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
            statement.executeUpdate(Delete_Produit);
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

            ResultSet res = statement.executeQuery(req3);
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

                

                products.add(produit);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public String returnImage(int i,String imageUrl) {
        String image = null;
        try {
            String req3 = "select `"+imageUrl+"` from produit where `id`= " + i;
            ResultSet res = statement.executeQuery(req3);
            while (res.next()) {
                image = res.getString(imageUrl);
                

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
            ResultSet res = statement.executeQuery(req3);
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

            ResultSet resultSet = statement.executeQuery(req3);
            while (resultSet.next()) {

                String id = resultSet.getString("id");
                String libelle = resultSet.getString("libelle");
                String description = resultSet.getString("description");
                String marque = resultSet.getString("marque");
                String etat = resultSet.getString("etat");
                String prixProduit = resultSet.getString("prixProduit");
                String quantiteStock = resultSet.getString("quantiteStock");
                String pourcentagePromotion = resultSet.getString("pourcentagePromotion");
                String created_date = resultSet.getString("created_date");
                String duree = resultSet.getString("duree");
                String approuver = resultSet.getString("approuver");
                String seller = resultSet.getString("seller");
                String image_name = resultSet.getString("image_name");
                String updated_at = resultSet.getString("updated_at");
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
            ResultSet resultSet = statement.executeQuery(query);
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

    public ObservableList<Produit> SearchByName(String search) {
        ObservableList<Produit> products = FXCollections.observableArrayList();

        String requeteSearch = "SELECT * FROM produit  WHERE `libelle` LIKE '" + search + "%'";
        try {

            ResultSet resultSet = statement.executeQuery(requeteSearch);

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId(resultSet.getInt("id"));
                produit.setLibelle(resultSet.getString("libelle"));
                produit.setDescription(resultSet.getString("description"));
                produit.setMarque(resultSet.getString("marque"));
                produit.setEtat(resultSet.getString("etat"));
                produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                produit.setQuantiteStock(resultSet.getInt("quantiteStock"));
                produit.setCreatedDate(resultSet.getDate("created_date"));
                produit.setDuree(resultSet.getInt("duree"));
                produit.setApprouver(resultSet.getString("approuver"));
                produit.setSeller(resultSet.getInt("seller"));
                produit.setUpdatedAt(resultSet.getDate("updated_at"));
                
                products.add(produit);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public ObservableList<Produit> SearchByCategorie(String categorie) {
        ResultSet searchResultSet = null;
        ResultSet CategorieResultSet = null;
        Integer id = 0;
        ObservableList<Produit> products = FXCollections.observableArrayList();
        
        String requeteCategorie = "SELECT `id` FROM `categorie` WHERE  `nomCategorie`='"+categorie+"'";
        try {
            CategorieResultSet = statement.executeQuery(requeteCategorie);
            while (CategorieResultSet.next())
            id = CategorieResultSet.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        String requeteSearch = "SELECT * FROM produit  WHERE `produitCategorie` LIKE '" + id + "'";
                System.out.println(requeteSearch);

        try {

            searchResultSet = statement.executeQuery(requeteSearch);

            while (searchResultSet.next()) {
                Produit produit = new Produit();
                produit.setId(searchResultSet.getInt("id"));
                produit.setLibelle(searchResultSet.getString("libelle"));
                produit.setDescription(searchResultSet.getString("description"));
                produit.setMarque(searchResultSet.getString("marque"));
                produit.setEtat(searchResultSet.getString("etat"));
                produit.setPrixProduit(searchResultSet.getDouble("prixProduit"));
                produit.setQuantiteStock(searchResultSet.getInt("quantiteStock"));
                produit.setCreatedDate(searchResultSet.getDate("created_date"));
                produit.setDuree(searchResultSet.getInt("duree"));
                produit.setApprouver(searchResultSet.getString("approuver"));
                produit.setSeller(searchResultSet.getInt("seller"));
                produit.setUpdatedAt(searchResultSet.getDate("updated_at"));
              
                
                products.add(produit);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public ObservableList<Produit> SearchByPrice(Double minPrix,Double maxPrix) {
        ObservableList<Produit> products = FXCollections.observableArrayList();

        String requetePriceSearch = "SELECT * FROM `produit` WHERE `prixProduit` BETWEEN "+minPrix +"AND "+maxPrix;
        try {

            ResultSet resultSet = statement.executeQuery(requetePriceSearch);

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId(resultSet.getInt("id"));
                produit.setLibelle(resultSet.getString("libelle"));
                produit.setDescription(resultSet.getString("description"));
                produit.setMarque(resultSet.getString("marque"));
                produit.setEtat(resultSet.getString("etat"));
                produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                produit.setQuantiteStock(resultSet.getInt("quantiteStock"));
                produit.setCreatedDate(resultSet.getDate("created_date"));
                produit.setDuree(resultSet.getInt("duree"));
                produit.setApprouver(resultSet.getString("approuver"));
                produit.setSeller(resultSet.getInt("seller"));
                produit.setUpdatedAt(resultSet.getDate("updated_at"));
               
                products.add(produit);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }
public Double maxPrice() {
Double prix= null;
        String requeteMaxPrice = "SELECT MAX(`prixProduit`) FROM produit";
        try {

            ResultSet resultSet = statement.executeQuery(requeteMaxPrice);

            while (resultSet.next()) {
           
            prix =    resultSet.getDouble("MAX(`prixProduit`)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prix;
    }

public ObservableList<Produit> afficher(Integer IdUser) {
        Produit p = new Produit();
        String req3 = "select * from produit WHERE `seller`="+IdUser;
        ObservableList<Produit> products = FXCollections.observableArrayList();
        
        try {

            ResultSet res = statement.executeQuery(req3);
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

              

                products.add(produit);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }


}



