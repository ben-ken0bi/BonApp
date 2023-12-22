package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Etat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EtatDAOImpl implements EtatDAO {

    private JdbcTemplate jdbcTemplate;

    EtatDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Etat> chercherEtatParId(long idEtat) {
        return Optional.empty();
    }

    @Override
    public List<Etat> listerEtats() {
        String sql = "SELECT id_etat, libelle FROM etat";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Etat.class));
    }
}
