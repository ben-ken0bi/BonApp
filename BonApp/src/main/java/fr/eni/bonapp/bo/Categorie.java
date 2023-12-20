package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Categorie {
    private long idCategorie;
    @Size(min = 1, max = 50)
    private String nom;

    public Categorie(long idCategorie, String nom) {
    }
}
