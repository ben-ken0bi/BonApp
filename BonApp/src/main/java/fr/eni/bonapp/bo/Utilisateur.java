package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class Utilisateur {
    private long idUtilisateur;
    @NotNull
    @Max(50)
    private String nom, prenom, pseudo, mdp;
    @NotNull
    @Email
    private String email;

}
