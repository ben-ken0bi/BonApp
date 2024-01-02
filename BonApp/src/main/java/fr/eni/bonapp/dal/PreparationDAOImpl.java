package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Preparation;
import fr.eni.bonapp.bo.Recette;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PreparationDAOImpl implements PreparationDAO {
    Logger logger = LoggerFactory.getLogger(PreparationDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    private final RecetteDAO recetteDAO;

    PreparationDAOImpl(RecetteDAO recetteDAO) {
        this.recetteDAO = recetteDAO;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Dans le RowMapper de préparation
     */
    class PreparationRowMapper implements RowMapper<Preparation> {

        @Override
        public Preparation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Preparation preparation = new Preparation();
            preparation.setIdPreparation(rs.getLong("id_preparation"));
            preparation.setNumero(rs.getInt("numero"));
            preparation.setTexte(rs.getString("texte"));

            Optional<Recette> optRecette = recetteDAO.chercherRecetteParId(rs.getLong("id_recette"));
            optRecette.ifPresent(preparation::setRecette);
            return preparation;
        }
    }

    /**
     * Permet de lister les préparations d'une recette.
     *
     * @param idRecette
     * @return
     */
    @Override
    public List<Preparation> listerPreparationsParIdRecette(long idRecette) {
        logger.info(
                "Dans la methode pour lister les préparations pour la recette à l'id {}", idRecette);
        String sql =
                "SELECT id_preparation, numero, texte, id_recette"
                        + " FROM preparation"
                        + " WHERE id_recette = ?"
                        + " ORDER BY numero";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Preparation.class), idRecette);
    }

    /**
     * Permet de récuperer une préparation par son id. Si aucune préparation n'est trouvée, renvoie un
     * logger le precisant et un optional vide.
     *
     * @param idPreparation
     * @return
     */
    @Override
    public Optional<Preparation> chercherPreparationParId(long idPreparation) {
        logger.info("Dans chercher les preparations pour l'id {}", idPreparation);
        String sql =
                "Select id_preparation, numero, texte, id_recette from preparation where id_preparation =?";
        Optional<Preparation> optPreparation;

        try {
            Preparation preparation =
                    jdbcTemplate.queryForObject(sql, new PreparationRowMapper(), idPreparation);
            optPreparation = Optional.of(preparation);
        } catch (DataAccessException dae) {
            logger.error("Aucun ingrédient trouvé à cet id {}", idPreparation);
            return Optional.empty();
        }
        return optPreparation;
    }
}
