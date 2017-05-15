package services;

import entities.Magasin;

public abstract interface MagasinInterfaceService
{
  public abstract void add(Magasin paramMagasin);
  
  public abstract void remove(Magasin paramMagasin);
  
  public abstract void update(Magasin paramMagasin);
  
  public abstract void findAll();
  
  public abstract Magasin Afficher();
}
