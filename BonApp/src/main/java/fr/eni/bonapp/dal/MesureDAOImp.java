package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Mesure;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MesureDAOImp implements MesureDAO {
    Logger logger = LoggerFactory.getLogger(MesureDAOImp.class);
    private final JdbcTemplate jdbcTemplate;

    MesureDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
