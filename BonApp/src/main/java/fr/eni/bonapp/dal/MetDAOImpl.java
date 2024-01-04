package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Met;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MetDAOImpl implements MetDAO {
  private JdbcTemplate jdbcTemplate;
  Logger logger = LoggerFactory.getLogger(MetDAOImpl.class);

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Permet de chercher un met via son id. Si aucun met n'est trouvé, renvoie un logger le precisant
   * et un optional vide.
   *
   * @param idMet
   * @return
   */
  @Override
  public Optional<Met> chercherMetParId(long idMet) {
    logger.info("Dans la recherche de met pour l'id suivant {}", idMet);
    String sql = "SELECT id_met, nom FROM met WHERE id_met=?";
    Optional<Met> optMet;
    try {
      Met met =
          jdbcTemplate.queryForObject(
              sql, (ResultSet rs, int rowNum) -> new Met(rs.getLong(1), rs.getString(2)), idMet);
      optMet = Optional.of(met);
    } catch (DataAccessException dae) {
      logger.info("Pas de met trouvé à l'id suivant {}", idMet);
      return Optional.empty();
    }
    return optMet;
  }

  /**
   * Permet d'afficher la liste de tous les mets
   *
   * @return
   */
  @Override
  public List<Met> listerMets() {
    logger.info("Dans lister les mets");
    String sql = "SELECT id_met, nom FROM met";

    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Met>(Met.class));
  }
}
