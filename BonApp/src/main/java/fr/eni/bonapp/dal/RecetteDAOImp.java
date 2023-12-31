package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecetteDAOImp implements RecetteDAO {
  Logger logger = LoggerFactory.getLogger(RecetteDAOImp.class);
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private JdbcTemplate jdbcTemplate;
  private final IngredientDAO ingredientDAO;
  private final PreparationDAO preparationDAO;
  private final CommentaireDAO commentaireDAO;
  private final UtilisateurDAO utilisateurDAO;
  private final EtatDAO etatDAO;
  private final MetDAO metDAO;

  @Autowired
  public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = this.namedParameterJdbcTemplate.getJdbcTemplate();
  }

  RecetteDAOImp(
      @Lazy IngredientDAO ingredientDAO,
      @Lazy PreparationDAO preparationDAO,
      @Lazy CommentaireDAO commentaireDAO,
      @Lazy UtilisateurDAO utilisateurDAO,
      @Lazy EtatDAO etatDAO,
      @Lazy MetDAO metDAO) {
    this.ingredientDAO = ingredientDAO;
    this.preparationDAO = preparationDAO;
    this.commentaireDAO = commentaireDAO;
    this.utilisateurDAO = utilisateurDAO;
    this.etatDAO = etatDAO;
    this.metDAO = metDAO;
  }

  class RecetteRowMapper implements RowMapper<Recette> {

    @Override
    public Recette mapRow(ResultSet rs, int rowNum) throws SQLException {
      logger.info("dans le rowMapper de Recette");
      Recette recette = new Recette();

      recette.setIdRecette(rs.getLong("id_recette"));
      recette.setTitre(rs.getString("titre"));
      recette.setImage(rs.getString("image"));

      recette.setListeIngredients(
          ingredientDAO.listerIngredientPourRecette(recette.getIdRecette()));

      recette.setListePreparation(
          preparationDAO.listerPreparationsParIdRecette(recette.getIdRecette()));

      Optional<Etat> optEtat = etatDAO.chercherEtatParId(rs.getLong("id_etat"));
      optEtat.ifPresent(recette::setEtat);

      recette.setListeCommentaires(
          commentaireDAO.listerCommentairesParRecetteId(recette.getIdRecette()));

      Optional<Utilisateur> optUtilisateur =
          utilisateurDAO.chercherUtilisateurParId(rs.getLong("id_utilisateur"));
      optUtilisateur.ifPresent(recette::setUtilisateur);

      Optional<Met> optMet = metDAO.chercherMetParId(rs.getLong("id_met"));
      optMet.ifPresent(recette::setMet);

      return recette;
    }
  }

  /**
   * Permet de chercher une recette via son id. Si aucune recette n'est trouvée, renvoie un logger
   * le precisant et un optional vide.
   *
   * @param idRecette
   * @return
   */
  @Override
  public Optional<Recette> chercherRecetteParId(long idRecette) {
    logger.info("Dans la recherche de recette pour l'id {}", idRecette);
    String sql =
        "Select id_recette,titre,image,id_etat,id_utilisateur,id_met from recette where id_recette =?";
    Optional<Recette> optionalRecette;

    try {
      Recette recette = jdbcTemplate.queryForObject(sql, new RecetteRowMapper(), idRecette);
      optionalRecette = Optional.of(recette);
    } catch (DataAccessException dae) {
      logger.error("Aucune recette de trouvée à l'id suivant {}", idRecette);
      return Optional.empty();
    }

    return optionalRecette;
  }

  /**
   * Permet de lister les recettes par utilisateur
   *
   * @param idUtilisateur
   * @return
   */
  public List<Recette> listerRecettesParUtilisateur(long idUtilisateur) {
    logger.info("Dans lister recette par utilisateur avec l'id {}", idUtilisateur);
    String sql =
        "SELECT R.id_recette, R.titre, R.image, R.id_etat, E.libelle, R.id_utilisateur, R.id_met, M.nom "
            + "FROM recette R "
            + "JOIN etat E ON R.id_etat = E.id_etat "
            + "JOIN met M ON R.id_met = M.id_met "
            + "WHERE R.id_utilisateur=?";

    List<Recette> recettes =
        jdbcTemplate.query(sql, new RecetteDAOImp.RecetteRowMapper(), idUtilisateur);

    // Load ingredients separately for each recette
    for (Recette recette : recettes) {
      List<Ingredient> ingredients =
          ingredientDAO.listerIngredientPourRecette(recette.getIdRecette());
      recette.setListeIngredients(ingredients);
    }

    return recettes;
  }

  /**
   * Permet de lister toutes les recettes par utilisateur et catégorisées par le type de mets
   *
   * @param idUtilisateur
   * @param idMet
   * @return
   */
  public List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet) {
    logger.info(
        "Dans lister les recettes pour l'utilisateur {} et le type de met id {}",
        idUtilisateur,
        idMet);
    String sql =
        "SELECT R.id_recette, R.titre, R.image, R.id_etat, E.libelle, R.id_utilisateur, R.id_met, M.nom "
            + "FROM recette R "
            + "JOIN etat E ON R.id_etat = E.id_etat "
            + "JOIN met M ON R.id_met = M.id_met "
            + "WHERE R.id_utilisateur=? AND R.id_met=?";
    return jdbcTemplate.query(sql, new RecetteDAOImp.RecetteRowMapper(), idUtilisateur, idMet);
  }

  /**
   * Permet de lister les recettes par utilisateur et classée par leur état
   *
   * @param idUtilisateur
   * @param idEtat
   * @return
   */
  @Override
  public List<Recette> listerRecettesParUtilisateurEtat(long idUtilisateur, long idEtat) {
    logger.info(
        "Dans lister les recettes pour l'utilisateur {} et le type de met id {} ",
        idUtilisateur,
        idEtat);
    String sql =
        "SELECT R.id_recette, R.titre, R.image, R.id_etat, E.libelle, R.id_utilisateur, R.id_met, M.nom "
            + "FROM recette R JOIN etat E ON R.id_etat = E.id_etat "
            + "JOIN met M ON R.id_met = M.id_met "
            + "WHERE R.id_utilisateur=? AND R.id_etat=?";
    return jdbcTemplate.query(sql, new RecetteDAOImp.RecetteRowMapper(), idUtilisateur, idEtat);
  }

  /**
   * Permet de créer une nouvelle recette
   *
   * @param recette
   */
  @Override
  public void ajouterRecette(Recette recette) {}
}
