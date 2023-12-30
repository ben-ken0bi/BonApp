package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Recette {

    private long idRecette;
    @NotNull
    private String titre;
    private String image;
    @NotNull
    private List<Ingredient> listeIngredients;
    @NotNull
    private List<Preparation> listePreparation;
    @NotNull
    private Etat etat;
    private List<Commentaire> listeCommentaires;
    @NotNull
    private Utilisateur utilisateur;
    @NotNull
    private Met met;

    public Recette() {
    }

    public Recette(
            long idRecette,
            String titre,
            String image,
            List<Ingredient> listeIngredients,
            List<Preparation> listePreparation,
            Etat etat,
            List<Commentaire> listeCommentaires,
            Utilisateur utilisateur,
            Met met) {
        this.idRecette = idRecette;
        this.titre = titre;
        this.image = image;
        this.listeIngredients = listeIngredients;
        this.listePreparation = listePreparation;
        this.etat = etat;
        this.listeCommentaires = listeCommentaires;
        this.utilisateur = utilisateur;
        this.met = met;
    }

    public long getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(long idRecette) {
        this.idRecette = idRecette;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    public void setListeIngredients(List<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    public List<Preparation> getListePreparation() {
        return listePreparation;
    }

    public void setListePreparation(List<Preparation> listePreparation) {
        this.listePreparation = listePreparation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<Commentaire> getListeCommentaires() {
        return listeCommentaires;
    }

    public void setListeCommentaires(List<Commentaire> listeCommentaires) {
        this.listeCommentaires = listeCommentaires;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Met getMet() {
        return met;
    }

    public void setMet(Met met) {
        this.met = met;
    }

    @Override
    public String toString() {
        return "Recette{"
                + "idRecette="
                + idRecette
                + ", titre='"
                + titre
                + '\''
                + ", image="
                + image
                + ", listeIngredients="
                + listeIngredients
                + ", listePreparation="
                + listePreparation
                + ", etat="
                + etat
                + ", listeCommentaires="
                + listeCommentaires
                + ", utilisateur="
                + utilisateur
                + ", met="
                + met
                + '}';
    }
}
