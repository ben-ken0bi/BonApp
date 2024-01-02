package fr.eni.bonapp.bll.ingredient;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.dal.ingredient.IngredientDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    public final IngredientDAO ingredientDAO;

    IngredientServiceImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public Optional<Ingredient> chercherIngredientParId(long idIngredient) {
        return ingredientDAO.chercherIngredientParId(idIngredient);
    }

    @Override
    public List<Ingredient> listerIngredients() {
        return ingredientDAO.listerIngredients();
    }

    @Override
    public void ajouterIngredient(Ingredient ingredient) {
        ingredientDAO.ajouterIngredient(ingredient);
    }
}
