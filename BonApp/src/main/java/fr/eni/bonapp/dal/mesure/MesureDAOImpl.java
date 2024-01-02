package fr.eni.bonapp.dal.mesure;

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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MesureDAOImpl implements MesureDAO {
    Logger logger = LoggerFactory.getLogger(MesureDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    }

    @Override
    public Optional<Mesure> chercherMesureParId(long idMesure) {
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
            logger.error("Erreur chercherCategorieParId");
            return Optional.empty();
        }
        return optMesure;
    }

    @Override
    public List<Mesure> listerMesures() {
        String sql = "SELECT id_mesure, mesure FROM mesure";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Mesure.class));
    }
}
