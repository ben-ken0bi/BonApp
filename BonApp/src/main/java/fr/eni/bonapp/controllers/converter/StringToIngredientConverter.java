package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.IngredientService;
import fr.eni.bonapp.bo.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {

    private final IngredientService ingredientService;

    public StringToIngredientConverter(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public Ingredient convert(String idIngredient) {

        long idIngredientLong = Long.parseLong(idIngredient);

        return ingredientService.chercherIngredientParId(idIngredientLong).get();
    }
}
