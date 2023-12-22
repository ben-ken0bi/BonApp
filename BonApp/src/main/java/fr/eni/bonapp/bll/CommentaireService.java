package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;

public interface CommentaireService {
  List<Commentaire> listerCommentaireParIdRecette(long idRecette);
}
