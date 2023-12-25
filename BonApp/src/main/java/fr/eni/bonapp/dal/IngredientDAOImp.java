package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDAOImp implements IngredientDAO {
    Logger logger = LoggerFactory.getLogger(IngredientDAOImp.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

    @Override
    public void ajouterIngredient(Ingredient ingredient) {
        KeyHolder generatedKey = new GeneratedKeyHolder();
        String sql = "INSERT INTO ingredient (nom,id_sous_categorie) VALUES (:nom,:id_sous_categorie)";

        MapSqlParameterSource namedParameterSource =
                new MapSqlParameterSource()
                        .addValue("nom", ingredient.getNom())
                        .addValue("id_sous_categorie", ingredient.getSousCategorie().getIdSousCategorie());

        namedParameterJdbcTemplate.update(sql, namedParameterSource, generatedKey);

        ingredient.setIdIngredient(generatedKey.getKey().longValue());
    }
}
