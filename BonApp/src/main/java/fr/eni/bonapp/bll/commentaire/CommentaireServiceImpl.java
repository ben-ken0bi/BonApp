package fr.eni.bonapp.bll.commentaire;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.dal.commentaire.CommentaireDAO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {
  private CommentaireDAO commentaireDAO;

  @Override
  public List<Commentaire> listerCommentaireParIdRecette(long idRecette) {
    return commentaireDAO.listerCommentairesParRecetteId(idRecette);
  }
}
