package fr.eni.bonapp.dto;

public class IngredientDTO {
    private long id;
    private String nom;
    private double quantite;
    private long idMesure;
    private long idRecette;

    public IngredientDTO() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public long getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(long idMesure) {
        this.idMesure = idMesure;
    }

    public long getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(long idRecette) {
        this.idRecette = idRecette;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
