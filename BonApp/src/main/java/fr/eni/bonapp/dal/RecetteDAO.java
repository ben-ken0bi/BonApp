package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Preparation;
import fr.eni.bonapp.bo.Recette;

import java.util.List;
import java.util.Optional;

public interface RecetteDAO {
    Optional<Recette> chercherRecetteParId(long idRecette);

    List<Recette> listerRecettesParUtilisateur(long idUtilisateur);

    List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet);

    void ajouterRecette(Recette recette);

    void ajoutPreparation(List<Preparation> preparations, long idRecettte);

    public void ajouterCommentaire(List<Commentaire> commentaires, long idRecette);

    public void ajouterIngredient(List<Ingredient> ingredients, long idRecettte);
}
