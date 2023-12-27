package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class SousCategorie {
    private long idSousCategorie;
    @NotNull
    private String nom;
    @NotNull
    private Categorie categorie;

    public SousCategorie() {
    }

    public SousCategorie(long idSousCategorie, String nom, Categorie categorie) {
        this.idSousCategorie = idSousCategorie;
        this.nom = nom;
        this.categorie = categorie;
    }

    public SousCategorie(long idSousCategorie, String nom) {
        this.idSousCategorie = idSousCategorie;
        this.nom = nom;
    }

    public long getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(long idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "SousCategorie{"
                + "idSousCategorie="
                + idSousCategorie
                + ", nom='"
                + nom
                + '\''
                + ", categorie="
                + categorie
                + '}';
    }
}
