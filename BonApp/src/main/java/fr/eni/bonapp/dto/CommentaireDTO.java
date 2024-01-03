package fr.eni.bonapp.dto;

import java.util.Date;

public class CommentaireDTO {
    private long id;

    private String commentaire;

    private Date date;
    private long idRecette;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(long idRecette) {
        this.idRecette = idRecette;
    }
}
