package fr.eni.bonapp.dal.mappers;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.SousCategorie;
import fr.eni.bonapp.dal.SousCategorieDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class IngredientRowMapper implements RowMapper<Ingredient> {
    private final SousCategorieDAO sousCategorieDAO;

    IngredientRowMapper(SousCategorieDAO sousCategorieDAO) {
        this.sousCategorieDAO = sousCategorieDAO;
    }

    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setIdIngredient(rs.getLong("id_ingredient"));
        ingredient.setNom(rs.getString("nom"));

        Optional<SousCategorie> optSousCategorie =
                sousCategorieDAO.chercherSousCategorie(rs.getLong("id_sous_categorie"));

        optSousCategorie.ifPresent(ingredient::setSousCategorie);


        return ingredient;
    }
}
