package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Recette;

import java.util.List;
import java.util.Optional;

public interface RecetteService {
    Optional<Recette> chercherRecetteParId(long idRecette);

    List<Recette> listerRecettesParUtilisateur(long idUtilisateur);

    List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet);
}
