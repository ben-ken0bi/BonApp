package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO {
    Optional<Ingredient> chercherIngredientParId(long idIngredient);

    List<Ingredient> listerIngredients();

    void ajouterIngredient(Ingredient ingredient);
}
