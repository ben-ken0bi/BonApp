package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;
import java.util.Optional;

public interface CommentaireService {
  List<Commentaire> listerCommentaireParIdRecette(long idRecette);
  Optional<Commentaire> chercherCommentaireParId(long idCommentaire);
}
