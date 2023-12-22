package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentaireDAOImpl implements CommentaireDAO {
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Commentaire> listerCommentairesParRecetteId(long idRecette) {

    String sql =
        "SELECT id_commentaire, date, commentaire" + " FROM ingredient" + " WHERE id_recette = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Commentaire.class), idRecette);
  }
}
