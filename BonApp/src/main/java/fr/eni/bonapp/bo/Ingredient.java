package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Ingredient {
    private long idIngredient;
    @NotNull
    private String nom;
    @NotNull
    private SousCategorie sousCategorie;
    @Min(0)
    private double quantite;
    @NotNull
    private Mesure mesure;


    public Ingredient() {
    }

    public Ingredient(long idIngredient, String nom) {
        this.idIngredient = idIngredient;
        this.nom=nom;
    }

    public Ingredient(long idIngredient, String nom, SousCategorie sousCategorie, double quantite, Mesure mesure) {
        this.idIngredient = idIngredient;
        this.nom = nom;
        this.sousCategorie = sousCategorie;
        this.quantite = quantite;
        this.mesure = mesure;
    }

    public long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Mesure getMesure() {
        return mesure;
    }

    public void setMesure(Mesure mesure) {
        this.mesure = mesure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", nom='" + nom + '\'' +
                ", sousCategorie=" + sousCategorie +
                ", quantite=" + quantite +
                ", mesure=" + mesure +
                '}';
    }
}
