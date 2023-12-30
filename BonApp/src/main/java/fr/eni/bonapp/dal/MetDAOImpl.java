package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Met;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MetDAOImpl implements MetDAO {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Met> chercherMetParId(long idMet) {
    String sql = "SELECT id_met, met FROM met WHERE id_met=?";
    Optional<Met> optMet = Optional.empty();
    try {
      Met met =
          jdbcTemplate.queryForObject(
              sql,
              new Object[] {idMet},
              (ResultSet rs, int rowNum) -> new Met(rs.getLong(1), rs.getString(2)));
      optMet = Optional.of(met);
    } catch (DataAccessException dae) {
      System.out.println("Erreur chercherMetParId");
    }
    return optMet;
  }

  @Override
  public List<Met> listerMets() {
    String sql = "SELECT id_met, met" + " FROM met";

    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Met>(Met.class));
  }
}
