package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Commentaire {
    private long idCommentaire;

    @Size(min = 1, max = 250)
    private String commentaire;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private Recette recette;

    public Commentaire() {
    }

    public Commentaire(long idCommentaire, String commentaire, Date date, Recette recette) {
        this.idCommentaire = idCommentaire;
        this.commentaire = commentaire;
        this.date = date;
        this.recette = recette;
    }

    public long getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(long idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    @Override
    public String toString() {
        return "Commentaire{"
                + "idCommentaire="
                + idCommentaire
                + ", commentaire='"
                + commentaire
                + '\''
                + ", date="
                + date
                + '}';
    }
}
