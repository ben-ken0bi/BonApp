package fr.eni.bonapp.bll;


import fr.eni.bonapp.bo.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
//    Optional<Ingredient> chercherIngredientParId(long idIngredient);

    List<Ingredient> listerTousLesIngredients();
}
