package fr.eni.bonapp.dal.commentaire;

import fr.eni.bonapp.bo.Commentaire;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentaireDAOImpl implements CommentaireDAO {
  private JdbcTemplate jdbcTemplate;
  @Autowired
  public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
      this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
  }
  @Override
  public List<Commentaire> listerCommentairesParRecetteId(long idRecette) {

    String sql =
        "SELECT id_commentaire, date, commentaire" + " FROM ingredient" + " WHERE id_recette = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Commentaire.class), idRecette);
  }
}
