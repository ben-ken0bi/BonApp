package fr.eni.bonapp.dto;

public class PreparationDTO {
    private long id;

    private int numero;
    private String texte;
    private long idRecette;

    public PreparationDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public long getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(long idRecette) {
        this.idRecette = idRecette;
    }
}
