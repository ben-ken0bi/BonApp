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
        // id_commentaire
        String sql =
                "Select id_recette,titre,image,id_etat,id_utilisateur,id_met from recette where id_recette =?";
        Recette recette = jdbcTemplate.queryForObject(sql, new RecetteRowMapper(), idRecette);
        if (recette == null) {
            logger.error("Aucune recette de trouvée à l'id suivant {}", idRecette);
            return Optional.empty();
        }
        return Optional.of(recette);
    }

    /**
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<Recette> listerRecettesParUtilisateur(long idUtilisateur) {
        return null;
    }

    /**
     * @param idUtilisateur
     * @param idMet
     * @return
     */
    @Override
    public List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet) {
        return null;
    }

    /**
     * Permet de créer une nouvelle recette
     *
     * @param recette
     */
    @Override
    public void ajouterRecette(Recette recette) {
    }
}
