package fr.eni.bonapp.bll.commentaire;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;

public interface CommentaireService {
  List<Commentaire> listerCommentaireParIdRecette(long idRecette);
}
