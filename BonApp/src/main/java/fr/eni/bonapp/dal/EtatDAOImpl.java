package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Etat;

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
public class EtatDAOImpl implements EtatDAO {
    Logger logger = LoggerFactory.getLogger(EtatDAOImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Permet de chercher un état via son id. Si aucun état n'est trouvé, renvoie un logger le
     * precisant et un optional vide.
     *
     * @param idEtat
     * @return
     */
    @Override
    public Optional<Etat> chercherEtatParId(long idEtat) {
        logger.info("Dans Etat avec la méthode pour trouver son id numéro {}", idEtat);
        String sql = "Select id_etat, libelle from etat where id_etat=?";
        Optional<Etat> optEtat = Optional.empty();

        try {
            Etat etat =
                    jdbcTemplate.queryForObject(
                            sql, (ResultSet rs, int rowNum) -> new Etat(rs.getLong(1), rs.getString(2)), idEtat);
            optEtat = Optional.of(etat);
        } catch (DataAccessException dae) {
            logger.debug("il n'existe pas d'état à l'id suivant : {}", 1);
            return Optional.empty();
        }
        return optEtat;
    }

    /**
     * Permet d'afficher la liste de tous les états.
     *
     * @return
     */
    @Override
    public List<Etat> listerEtats() {
        String sql = "SELECT id_etat, libelle FROM etat";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Etat.class));
    }
}
