package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class SousCategorie {
    private long idSousCategorie;
    @NotNull
    private String nom;
    @NotNull
    private Categorie categorie;
}
