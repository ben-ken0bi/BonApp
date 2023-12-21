package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Etat {
    private long idEtat;
    @NotNull
    private String etat;

    public Etat() {
    }

    public Etat(long idEtat, String etat) {
        this.idEtat = idEtat;
        this.etat = etat;
    }

    public long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(long idEtat) {
        this.idEtat = idEtat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "idEtat=" + idEtat +
                ", etat='" + etat + '\'' +
                '}';
    }
}
