package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

import java.awt.*;
import java.util.List;

public class Recette {

    private long idRecette;
    @NotNull
    private String titre;
    private Image image;
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

}