package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.dal.CommentaireDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {
  private CommentaireDAO commentaireDAO;

  CommentaireServiceImpl(@Lazy CommentaireDAO commentaireDAO) {
    this.commentaireDAO = commentaireDAO;
  }

  @Override
  public List<Commentaire> listerCommentaireParIdRecette(long idRecette) {
    return commentaireDAO.listerCommentairesParRecetteId(idRecette);
  }

  @Override
  public Optional<Commentaire> chercherCommentaireParId(long idCommentaire) {
    return commentaireDAO.chercherCommentaireParId(idCommentaire);
  }
}
