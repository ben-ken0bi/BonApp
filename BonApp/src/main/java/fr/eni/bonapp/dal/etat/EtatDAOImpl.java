package fr.eni.bonapp.dal.etat;

import fr.eni.bonapp.bo.Etat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class EtatDAOImpl implements EtatDAO {
    Logger logger = LoggerFactory.getLogger(EtatDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    }

    @Override
    public Optional<Etat> chercherEtatParId(long idEtat) {

        String sql = "Select id_etat, libelle from etat where id_etat=?";
        Optional<Etat> optEtat = Optional.empty();

        try {
            Etat etat =
                    jdbcTemplate.queryForObject(
                            sql, (ResultSet rs, int rowNum) -> new Etat(rs.getLong(1), rs.getString(2)));
            assert etat != null;
            optEtat = Optional.of(etat);
        } catch (DataAccessException dae) {
            logger.debug("il n'existe pas d'état à l'id suivant : {}", 1);
        }
        return optEtat;
    }

    @Override
    public List<Etat> listerEtats() {
        String sql = "SELECT id_etat, libelle FROM etat";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Etat.class));
    }
}
