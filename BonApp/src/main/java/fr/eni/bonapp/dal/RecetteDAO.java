package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Recette;

import java.util.List;
import java.util.Optional;

public interface RecetteDAO {
    Optional<Recette> chercherRecetteParId(long idRecette);
    List<Recette> listerRecettesParUtilisateur(long idUtilisateur);
    List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet);

}
