package services;

import entities.Magasin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyConnection;

public class MagasinService
  implements MagasinInterfaceService
{
  private Connection connection = MyConnection.getInstance();
  
  public void add(Magasin magasin)
  {
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("INSERT INTO Magasin (name,owner,address,image_name,updated_at,altitude,longitude,description,owner_id) values (?,?,?,?,?,?,?,?,?)");
      
      ps.setString(1, magasin.getName());
      ps.setString(2, magasin.getOwner());
      ps.setString(3, magasin.getAddress());
      ps.setString(4, magasin.getImage_name());
      ps.setTimestamp(5, magasin.getUpdated_at());
      ps.setString(6, magasin.getAltitude());
      ps.setString(7, magasin.getLongitude());
      ps.setString(8, magasin.getDescription());
      ps.setInt(9, magasin.getOwner_id().intValue());
      
      ps.executeUpdate();
    }
    catch (SQLException localSQLException) {}
  }
  
  public void remove(Magasin magasin)
  {
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("DELETE from magasin where id=?");
      ps.setInt(1, magasin.getId().intValue());
      ps.executeUpdate();
    }
    catch (SQLException localSQLException) {}
  }
  
  public void update(Magasin m)
  {
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("UPDATE magasin set name=?,owner=?,address=?,image_name=?,updated_at=?,altitude=?,longitude=?,description=?,owner_id=? where id=? ");
      
      ps.setString(1, m.getName());
      ps.setString(2, m.getOwner());
      ps.setString(3, m.getAddress());
      ps.setString(4, m.getImage_name());
      ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
      ps.setString(6, m.getAltitude());
      ps.setString(7, m.getLongitude());
      ps.setString(8, m.getDescription());
      ps.setInt(9, m.getOwner_id().intValue());
      ps.setInt(10, m.getId().intValue());
      
      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
    }
  }
  
  public void findAll()
  {
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("SELECT * from Magasin");
      
      ps.execute();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) {
        System.out.println("name: " + rs.getString("name") + " owner: " + rs.getString("owner") + " address: " + rs.getString("address") + " updated at: " + rs
          .getTimestamp("updated_at"));
      }
    }
    catch (SQLException localSQLException) {}
  }
  
  public Magasin Afficher()
  {
    Magasin m = null;
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("SELECT * from Magasin");
      
      ps.execute();
      ResultSet res = ps.getResultSet();
      
      m = new Magasin(res.getString("name"), res.getString("owner"), res.getString("address"), res.getString("image_name"), res.getTimestamp("updated_at"), res.getString("altitude"), res.getString("longitude"), res.getString("description"), Integer.valueOf(2));
    }
    catch (SQLException localSQLException) {}
    return m;
  }
  
  public List<Magasin> afficherMagasins()
  {
    List<Magasin> magasins = new ArrayList();
    Magasin m = null;
    try
    {
      PreparedStatement ps = this.connection.prepareStatement("SELECT * from Magasin");
      ps.execute();
      ResultSet res = ps.getResultSet();
      while (res.next())
      {
        m = new Magasin();
        m.setId(res.getInt("id"));
        m.setName(res.getString("name"));
        m.setOwner(res.getString("owner"));
        m.setOwner_id(res.getInt("owner_id"));
        m.setAddress(res.getString("address"));
        m.setImage_name(res.getString("image_name"));
        m.setUpdated_at(res.getTimestamp("updated_at"));
        m.setAltitude(res.getString("altitude"));
        m.setLongitude(res.getString("longitude"));
        m.setDescription(res.getString("description"));
        magasins.add(m);
          //System.out.println(m);
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(MagasinService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return magasins;
  }
}
