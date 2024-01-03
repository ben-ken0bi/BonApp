package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        KeyHolder generatedKey = new GeneratedKeyHolder();
        String sql =
                "insert into recette (titre, image, id_etat, id_utilisateur, id_met) "
                        + "values (:titre, :image, :id_etat, :id_utilisateur, :id_met)";

        SqlParameterSource paramSrc =
                new MapSqlParameterSource()
                        .addValue("titre", recette.getTitre())
                        .addValue("image", recette.getImage())
                        .addValue("id_etat", recette.getEtat().getIdEtat())
                        .addValue("id_utilisateur", recette.getUtilisateur().getIdUtilisateur())
                        .addValue("id_met", recette.getMet().getIdMet());

        recette.setIdRecette(generatedKey.getKey().longValue());

        ajoutPreparation(recette.getListePreparation(), recette.getIdRecette());
        ajouterCommentaire(recette.getListeCommentaires(), recette.getIdRecette());

        ajouterIngredient(recette.getListeIngredients(), recette.getIdRecette());

        namedParameterJdbcTemplate.update(sql, paramSrc, generatedKey);
    }

    /**
     * Permet d'ajouter une liste de préparation pour la création d'une recette.
     * Cette methode est implémentée dans ajouterRecette()
     *
     * @param preparations
     * @param idRecettte
     */
    @Override
    public void ajoutPreparation(List<Preparation> preparations, long idRecettte) {
        logger.info("ajout de la liste de préparations pour la création d'une recette");
        String sql =
                "INSERT INTO preparation (numero,texte,id_recette) VALUES(:numero,:texte,:id_recette)";

        jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, preparations.get(i).getNumero());
                        ps.setString(2, preparations.get(i).getTexte());
                        ps.setLong(3, idRecettte);
                    }

                    @Override
                    public int getBatchSize() {
                        return preparations.size();
                    }
                });
    }

    /**
     * Permet d'ajouter une liste de commentaires pour la création d'une recette.
     * Cette methode est implémentée dans ajouterRecette().
     *
     * @param commentaires
     * @param idRecette
     */
    @Override
    public void ajouterCommentaire(List<Commentaire> commentaires, long idRecette) {
        logger.info("ajout de la liste de commentaires pour la création d'une recette");
        String sql =
                "Insert into commentaire (date, commentaire, id_recette) Values (:date,:commentaire,:id_recette)";
        jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setDate(1, Date.valueOf(LocalDate.now()));
                        ps.setString(2, commentaires.get(i).getCommentaire());
                        ps.setLong(3, idRecette);
                    }

                    public int getBatchSize() {
                        return commentaires.size();
                    }
                });
    }

    /**
     * Permet d'ajouter une liste d'ingrédients pour la création d'une recette.
     * Cette methode est implémentée dans ajouterRecette().
     *
     * @param ingredients
     * @param idRecettte
     */
    @Override
    public void ajouterIngredient(List<Ingredient> ingredients, long idRecettte) {
        logger.info("ajout de la liste d'ingrédients pour la création d'une recette");
        String sql =
                "insert into recette_ingredient (id_recette, id_ingredient, quantite, id_mesure) "
                        + "values (:id_recette,:id_ingredient, :quantite, :id_mesure)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, idRecettte);
                ps.setLong(2, ingredients.get(i).getIdIngredient());
                ps.setDouble(3, ingredients.get(i).getQuantite());
                ps.setLong(4, ingredients.get(i).getMesure().getIdMesure());
            }

            @Override
            public int getBatchSize() {
                return ingredients.size();
            }
        });
    }
}
