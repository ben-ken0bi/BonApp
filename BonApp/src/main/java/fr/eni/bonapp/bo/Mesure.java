package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Mesure {
  private long idMesure;
  @NotNull private String mesure;

  public Mesure() {}

  public Mesure(long idMesure, String mesure) {
    this.idMesure = idMesure;
    this.mesure = mesure;
  }

  public long getIdMesure() {
    return idMesure;
  }


  public void setIdMesure(long idMesure) {
    this.idMesure = idMesure;
  }

  public String getMesure() {
    return mesure;
  }

  public void setMesure(String mesure) {
    this.mesure = mesure;
  }

  @Override
  public String toString() {
    return "Mesure{" + "idMesure=" + idMesure + ", mesure='" + mesure + '\'' + '}';
  }
}
