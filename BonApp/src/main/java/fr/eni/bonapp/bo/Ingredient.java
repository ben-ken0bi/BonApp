package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Ingredient {
    private long idIngredient;
    @NotNull
    private String nom;
    @NotNull
    private SousCategorie sousCategorie;
    @NotNull
    private Quantite quantite;


}
