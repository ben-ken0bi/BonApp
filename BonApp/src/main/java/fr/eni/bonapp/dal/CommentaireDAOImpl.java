package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Commentaire;
import fr.eni.bonapp.bo.Recette;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommentaireDAOImpl implements CommentaireDAO {
  private JdbcTemplate jdbcTemplate;
  private RecetteDAO recetteDAO;
  Logger logger = LoggerFactory.getLogger(CommentaireDAOImpl.class);

  CommentaireDAOImpl(RecetteDAO recetteDAO) {
    this.recetteDAO = recetteDAO;
  }

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /** RowMapper de Commentaire */
  class CommentaireRowMapper implements RowMapper<Commentaire> {

    @Override
    public Commentaire mapRow(ResultSet rs, int rowNum) throws SQLException {
      Commentaire commentaire = new Commentaire();
      commentaire.setIdCommentaire(rs.getLong("id_commentaire"));
      commentaire.setDate(rs.getDate("date"));
      commentaire.setCommentaire(rs.getString("commentaire"));

      Optional<Recette> optRecette = recetteDAO.chercherRecetteParId(rs.getLong("id_recette"));
      optRecette.ifPresent(commentaire::setRecette);

      return commentaire;
    }
  }

  /**
   * Permet de retourner une liste de commentaires via l'id de la recette auxquels ils sont
   * associés.
   *
   * @param idRecette
   * @return
   */
  @Override
  public List<Commentaire> listerCommentairesParRecetteId(long idRecette) {
    logger.info("Liste des commentaires pour la recette à l'id suivant : {}", idRecette);
    String sql =
        "SELECT id_commentaire, date, commentaire" + " FROM commentaire" + " WHERE id_recette = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Commentaire.class), idRecette);
  }

  /**
   * Permet de chercher un commentaire via son id.
   *
   * @param idCommentaire
   * @return
   */
  @Override
  public Optional<Commentaire> chercherCommentaireParId(long idCommentaire) {
    logger.info("Affiche le commentaire avec l'id suivant : {}", idCommentaire);
    String sql =
        "Select id_commentaire,date, commentaire,id_recette from commentaire where id_commentaire = ?";

    Commentaire commentaire =
        jdbcTemplate.queryForObject(sql, new CommentaireRowMapper(), idCommentaire);

    return Optional.of(commentaire);
  }
}
