package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;

public class Met {
    private long idMet;
    @NotNull
    private String typeMet;
}
