package fr.eni.bonapp.dto;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Preparation;

import java.awt.*;
import java.util.List;

public class RecetteDTO {
    private long id;
    private String titre;
    private String image;
    private long idEtat;
    private long idUtilisateur;
    private long idMet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
