package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PreparationDAOImpl implements PreparationDAO {
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Preparation> listerPreparationsParIdRecette(long idRecette) {
    String sql =
        "SELECT id_preparation, numero, texte, id_recette"
            + " FROM preparation"
            + " WHERE id_recette = ?"
            + " ORDER BY numero";
      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Preparation.class), idRecette);
  }
}
