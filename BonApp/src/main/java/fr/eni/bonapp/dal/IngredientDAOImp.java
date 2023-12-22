package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IngredientDAOImp implements IngredientDAO {
    Logger logger = LoggerFactory.getLogger(IngredientDAOImp.class);
    private JdbcTemplate jdbcTemplate;

    IngredientDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ingredient> chercherIngredientParId(long idIngredient) {
        return Optional.empty();
    }

    @Override
    public List<Ingredient> listerIngredients() {
        String sql = "SELECT id_ingredient, nom, id_sous_categorie  FROM ingredient";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ingredient.class));
    }
}
