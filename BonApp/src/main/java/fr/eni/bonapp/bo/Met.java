package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Met {
    private long idMet;
    @NotNull
    private String typeMet;

    public Met() {
    }

    public Met(long idMet, String typeMet) {
        this.idMet = idMet;
        this.typeMet = typeMet;
    }

    public long getIdMet() {
        return idMet;
    }

    public void setIdMet(long idMet) {
        this.idMet = idMet;
    }

    public String getTypeMet() {
        return typeMet;
    }

    public void setTypeMet(String typeMet) {
        this.typeMet = typeMet;
    }

    @Override
    public String toString() {
        return "Met{" +
                "idMet=" + idMet +
                ", typeMet='" + typeMet + '\'' +
                '}';
    }
}
