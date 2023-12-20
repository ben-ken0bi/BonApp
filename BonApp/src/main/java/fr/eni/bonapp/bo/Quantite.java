package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Quantite {
    private long idQuantite;
    @NotNull
    @Min(0)
    private double quantite;
    @NotNull
    private Mesure mesure;


}
