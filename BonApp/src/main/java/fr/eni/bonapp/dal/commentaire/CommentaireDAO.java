package fr.eni.bonapp.dal.commentaire;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;

public interface CommentaireDAO {
  List<Commentaire> listerCommentairesParRecetteId(long idRecette);
}
