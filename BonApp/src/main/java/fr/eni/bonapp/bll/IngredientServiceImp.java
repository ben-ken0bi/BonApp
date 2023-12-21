package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.dal.IngredientDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService{
    public final IngredientDAO ingredientDAO;

    IngredientServiceImp(IngredientDAO ingredientDAO){
        this.ingredientDAO=ingredientDAO;
    }

    @Override
//    public Optional<Ingredient> chercherIngredientParId(long idIngredient) {
//        return Optional.empty();
//    }

    @Override
    public List<Ingredient> listerTousLesIngredients() {
        return ingredientDAO.listerTousLesIngredients();
    }
}
