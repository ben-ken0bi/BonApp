package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Preparation {
    private long idPreparation;
    @NotNull
    private int numero;
    @NotNull
    private String texte;

    public Preparation() {
    }

    public Preparation(long idPreparation, int numero, String texte) {
        this.idPreparation = idPreparation;
        this.numero = numero;
        this.texte = texte;
    }

    public long getIdPreparation() {
        return idPreparation;
    }

    public void setIdPreparation(long idPreparation) {
        this.idPreparation = idPreparation;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "Preparation{" +
                "idPreparation=" + idPreparation +
                ", numero=" + numero +
                ", texte='" + texte + '\'' +
                '}';
    }
}
