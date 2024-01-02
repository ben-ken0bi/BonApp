package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Utilisateur;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurDAOImp implements UtilisateurDAO {
  Logger logger = LoggerFactory.getLogger(UtilisateurDAOImp.class);
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Permet de chercher un utilisateur via son id. Si aucun utilisateur n'est trouvé, renvoie un
   * logger le precisant et un optional vide.
   * @param idUtilisateur
   * @return
   */
  @Override
  public Optional<Utilisateur> chercherUtilisateurParId(long idUtilisateur) {
    logger.info("Dans Utilisateur avec la methode pour trouver son id numéro {}", idUtilisateur);
    String sql =
        "Select id_utilisateur, pseudo, mdp, nom, prenom, email from utilisateur where id_utilisateur =?";
    Optional<Utilisateur> optUtilisateur = Optional.empty();

    try {
      Utilisateur utilisateur =
          jdbcTemplate.queryForObject(
              sql,
              (ResultSet rs, int rowNum) ->
                  new Utilisateur(
                      rs.getLong(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getString(4),
                      rs.getString(5),
                      rs.getString(6)),
              idUtilisateur);
      optUtilisateur = Optional.of(utilisateur);
    } catch (DataAccessException dae) {
      logger.error("Aucun utilisateur existe avec l'id suivant : {}", idUtilisateur);
      return Optional.empty();
    }
    return optUtilisateur;
  }

  @Override
  public List<Utilisateur> listerUtilisateurs() {
    return null;
  }

  @Override
  public Optional<Utilisateur> chercherUtilisateurParRecette(long idRecette) {
    return Optional.empty();
  }
}
