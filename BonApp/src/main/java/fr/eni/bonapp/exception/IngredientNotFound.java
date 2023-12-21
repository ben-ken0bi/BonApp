package fr.eni.bonapp.exception;

import org.springframework.dao.DataAccessException;

public class IngredientNotFound extends Exception{
    public IngredientNotFound(){

    }
    public IngredientNotFound(DataAccessException dae){
        super (dae);
    }
    public IngredientNotFound(long idIngredient){
        super("l'ingrédient "+idIngredient+ " n'a pas été trouvé");
    }
}
