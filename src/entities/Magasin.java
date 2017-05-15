package entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Timestamp;

public class Magasin extends RecursiveTreeObject<Magasin>
{
  private Integer id;
  private String name;
  private String owner;
  private String address;
  private String image_name;
  private Timestamp updated_at;
  private String altitude;
  private String longitude;
  private String description;
  private Integer owner_id;
  
  public Magasin() {}
  
  public Magasin(String name, String owner, String address, String image_name, Timestamp updated_at, String altitude, String longitude, String description, Integer owner_id)
  {
    this.name = name;
    this.owner = owner;
    this.address = address;
    this.image_name = image_name;
    this.updated_at = updated_at;
    this.altitude = altitude;
    this.longitude = longitude;
    this.description = description;
    this.owner_id = owner_id;
  }
  
  public Magasin(Integer id, String name, String owner, String address, String image_name, Timestamp updated_at, String altitude, String longitude, String description, Integer owner_id)
  {
    this.id = id;
    this.name = name;
    this.owner = owner;
    this.address = address;
    this.image_name = image_name;
    this.updated_at = updated_at;
    this.altitude = altitude;
    this.longitude = longitude;
    this.description = description;
    this.owner_id = owner_id;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getOwner()
  {
    return this.owner;
  }
  
  public void setOwner(String owner)
  {
    this.owner = owner;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getImage_name()
  {
    return this.image_name;
  }
  
  public void setImage_name(String image_name)
  {
    this.image_name = image_name;
  }
  
  public Timestamp getUpdated_at()
  {
    return this.updated_at;
  }
  
  public void setUpdated_at(Timestamp updated_at)
  {
    this.updated_at = updated_at;
  }
  
  public String getAltitude()
  {
    return this.altitude;
  }
  
  public void setAltitude(String altitude)
  {
    this.altitude = altitude;
  }
  
  public String getLongitude()
  {
    return this.longitude;
  }
  
  public void setLongitude(String longitude)
  {
    this.longitude = longitude;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public Integer getOwner_id()
  {
    return this.owner_id;
  }
  
  public void setOwner_id(Integer owner_id)
  {
    this.owner_id = owner_id;
  }
  
  public String toString()
  {
    return "Magasin{id=" + this.id + ", name=" + this.name + ", owner=" + this.owner + ", address=" + this.address + ", image_name=" + this.image_name + ", updated_at=" + this.updated_at + ", altitude=" + this.altitude + ", longitude=" + this.longitude + ", description=" + this.description + ", owner_id=" + this.owner_id + '}';
  }
}
