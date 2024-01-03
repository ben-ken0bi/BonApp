package fr.eni.bonapp.dto;

public class RecetteDTO {
    private long id;
    private String titre;
    private String image;
    private long idEtat;
    private long idUtilisateur;
    private long idMet;

    public RecetteDTO() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(long idEtat) {
        this.idEtat = idEtat;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public long getIdMet() {
        return idMet;
    }

    public void setIdMet(long idMet) {
        this.idMet = idMet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
