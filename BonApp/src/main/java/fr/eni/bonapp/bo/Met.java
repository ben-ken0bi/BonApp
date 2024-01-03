package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Met {
  private long idMet;
  @NotNull private String nom;

  public Met() {}

  public Met(long idMet, String nom) {
    this.idMet = idMet;
    this.nom = nom;
  }

  public long getIdMet() {
    return idMet;
  }

  public void setIdMet(long idMet) {
    this.idMet = idMet;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String toString() {
    return "Met{" + "idMet=" + idMet + ", nom='" + nom + '\'' + '}';
  }
}
