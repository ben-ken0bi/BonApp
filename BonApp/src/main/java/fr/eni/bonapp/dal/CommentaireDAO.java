package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Commentaire;

import java.util.List;
import java.util.Optional;

public interface CommentaireDAO {
    List<Commentaire> listerCommentairesParRecetteId(long idRecette);

    Optional<Commentaire> chercherCommentaireParId(long idCommentaire);
}
