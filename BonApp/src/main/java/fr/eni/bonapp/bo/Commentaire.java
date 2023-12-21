package fr.eni.bonapp.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class Commentaire {
    private long idCommentaire;
    @Size(min = 1, max = 250)
    private String commentaire;
    @NotNull
    private LocalDateTime date;
    public Commentaire() {
    }
    public Commentaire(long idCommentaire, String commentaire, LocalDateTime date) {
        this.idCommentaire = idCommentaire;
        this.commentaire = commentaire;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "idCommentaire=" + idCommentaire +
                ", commentaire='" + commentaire + '\'' +
                ", date=" + date +
                '}';
    }
}
