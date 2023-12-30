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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PreparationDAOImpl implements PreparationDAO {
    Logger logger = LoggerFactory.getLogger(PreparationDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    private RecetteDAO recetteDAO;

    PreparationDAOImpl(RecetteDAO recetteDAO) {
        this.recetteDAO = recetteDAO;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
        String sql =
                "SELECT id_preparation, numero, texte, id_recette"
                        + " FROM preparation"
                        + " WHERE id_recette = ?"
                        + " ORDER BY numero";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Preparation.class), idRecette);
    }

    /**
     * Permet de récuperer une préparation par son id
     *
     * @param idPreparation
     * @return
     */
    @Override
    public Optional<Preparation> chercherPreparationParId(long idPreparation) {
        String sql =
                "Select id_preparation, numero, texte, id_recette from preparation where id_preparation =?";
        Preparation preparation =
                jdbcTemplate.queryForObject(sql, new PreparationRowMapper(), idPreparation);

        return Optional.of(preparation);
    }
}
