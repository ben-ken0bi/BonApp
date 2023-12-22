package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Mesure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MesureDAOImp implements MesureDAO {
    Logger logger = LoggerFactory.getLogger(MesureDAOImp.class);
    private JdbcTemplate jdbcTemplate;

    MesureDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Mesure> chercherMesureParId(long idMesure) {
        return Optional.empty();
    }

    @Override
    public List<Mesure> listerMesures() {
        String sql = "SELECT id_mesure, mesure FROM mesure";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Mesure.class));
    }
}
