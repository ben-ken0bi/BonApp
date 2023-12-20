package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Etat {
    private long idEtat;
    @NotNull
    private String etat;

}
