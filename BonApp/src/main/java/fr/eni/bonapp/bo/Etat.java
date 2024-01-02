package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Etat {
    private long idEtat;
    @NotNull
    private String libelle;

    public Etat() {
    }

    public Etat(long idEtat, String libelle) {
        this.idEtat = idEtat;
        this.libelle = libelle;
    }

    public long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(long idEtat) {
        this.idEtat = idEtat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Etat{" + "idEtat=" + idEtat + ", libelle='" + libelle + '\'' + '}';
    }
}
