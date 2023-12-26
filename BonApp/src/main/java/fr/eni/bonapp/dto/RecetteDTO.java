package fr.eni.bonapp.dto;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Preparation;

import java.awt.*;
import java.util.List;

public class RecetteDTO {
    private long id;
    private String titre;
    private Image image;
    private List<Ingredient> listeIngredient;
    private List<Preparation> listePreparation;
    private long idEtat;
    private List<Commentaire> listeCommentaires;
    private long idUtilisateur;
    private long idMet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Ingredient> getListeIngredient() {
        return listeIngredient;
    }

    public void setListeIngredient(List<Ingredient> listeIngredient) {
        this.listeIngredient = listeIngredient;
    }

    public List<Preparation> getListePreparation() {
        return listePreparation;
    }

    public void setListePreparation(List<Preparation> listePreparation) {
        this.listePreparation = listePreparation;
    }

    public long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(long idEtat) {
        this.idEtat = idEtat;
    }

    public List<Commentaire> getListeCommentaires() {
        return listeCommentaires;
    }

    public void setListeCommentaires(List<Commentaire> listeCommentaires) {
        this.listeCommentaires = listeCommentaires;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public long getIdMet() {
        return idMet;
    }

    public void setIdMet(long idMet) {
        this.idMet = idMet;
    }
}
