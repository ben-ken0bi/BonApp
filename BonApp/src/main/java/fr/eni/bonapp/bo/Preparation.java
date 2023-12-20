package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Preparation {
    private long idPreparation;
    @NotNull
    private int numero;
    @NotNull
    private String texte;
}
