package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDAOImp implements IngredientDAO {
  Logger logger = LoggerFactory.getLogger(IngredientDAOImp.class);
  private JdbcTemplate jdbcTemplate;

  IngredientDAOImp(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Ingredient mapRow
  @Override
  public Optional<Ingredient> chercherIngredientParId(long idIngredient) {
    String sql="SELECT id_ingredient, i.nom, sc.nom, c.nom FROM ingredient i " +
            "left join sous_categorie sc on i.id_sous_categorie=sc.id_sous_categorie " +
            "left join categorie c on sc.id_categorie=c.id_categorie " +
            "where id_ingredient = ?";
  Ingredient ingredient = null;

    try {
      ingredient = jdbcTemplate.queryForObject(sql, new FilmRowMapper(), idIngredient);
    } catch (DataAccessException dae) {
      return Optional.empty();
    }
    return Optional.of(film);
  }

  @Override
  public List<Ingredient> listerTousLesIngredients() {
    String sql = "SELECT id_ingredient, nom, id_sous_categorie  FROM ingredient";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Ingredient>(Ingredient.class));
  }
}
