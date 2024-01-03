package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Mesure;

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
public class MesureDAOImp implements MesureDAO {
    Logger logger = LoggerFactory.getLogger(MesureDAOImp.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Permet de chercher une mesure via son id. Si aucune mesure n'est trouvée, renvoie un logger le
     * precisant et un optional vide.
     *
     * @param idMesure
     * @return
     */
    @Override
    public Optional<Mesure> chercherMesureParId(long idMesure) {
        logger.info("Dans la recherche de mesure pour l'id {}", idMesure);
        String sql = "SELECT id_mesure, mesure FROM mesure where id_mesure =?";
        Optional<Mesure> optMesure;
        try {
            Mesure mesure =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) -> new Mesure(rs.getLong(1), rs.getString(2)),
                            idMesure);
            optMesure = Optional.of(mesure);

        } catch (DataAccessException dae) {
            logger.error("Aucune mesure trouvée à l'id suivant {}", idMesure);
            return Optional.empty();
        }
        return optMesure;
    }

    /**
     * Permet d'afficher la liste de toutes les mesures
     *
     * @return
     */
    @Override
    public List<Mesure> listerMesures() {
        logger.info("Dans lister mesures");
        String sql = "SELECT id_mesure, mesure FROM mesure";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Mesure.class));
    }
}
