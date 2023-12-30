package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class Utilisateur {
    private long idUtilisateur;

    @NotNull
    @Max(50)
    private String nom;

    @NotNull
    private String prenom;
    @NotNull
    private String pseudo;
    @NotNull
    private String mdp;
    @NotNull
    @Email
    private String email;

    public Utilisateur() {
    }

    public Utilisateur(
            long idUtilisateur, String nom, String prenom, String pseudo, String mdp, String email) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.email = email;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utilisateur{"
                + "idUtilisateur="
                + idUtilisateur
                + ", nom='"
                + nom
                + '\''
                + ", prenom='"
                + prenom
                + '\''
                + ", pseudo='"
                + pseudo
                + '\''
                + ", mdp='"
                + mdp
                + '\''
                + ", email='"
                + email
                + '\''
                + '}';
    }
}
