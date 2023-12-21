package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Size;

public class Categorie {
  private long idCategorie;

  @Size(min = 1, max = 50)
  private String nom;

  public Categorie() {}

  public Categorie(long idCategorie, String nom) {
    this.idCategorie = idCategorie;
    this.nom = nom;
  }

  public long getIdCategorie() {
    return idCategorie;
  }

  public void setIdCategorie(long idCategorie) {
    this.idCategorie = idCategorie;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String toString() {
    return "Categorie{" + "idCategorie=" + idCategorie + ", nom='" + nom + '\'' + '}';
  }
}
