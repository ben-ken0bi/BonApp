package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Commentaire;

import java.util.List;
import java.util.Optional;

public class CommentaireDAOImpl implements CommentaireDAO{
    @Override
    public Optional<Commentaire> chercherCommentaireParId(long idCommentaire) {
        return Optional.empty();
    }

    @Override
    public List<Commentaire> listerCommentairesParRecetteId(long idRecette) {
        return null;
    }
}
