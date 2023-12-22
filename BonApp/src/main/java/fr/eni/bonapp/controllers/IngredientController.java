package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IngredientController {
    Logger logger = LoggerFactory.getLogger(IngredientController.class);
    private IngredientService ingredientService;

    IngredientController(IngredientService ingredientService) {
        logger.debug("Dans constructeur controller");
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public String ingredients(Model model) {
        model.addAttribute("ingredients", ingredientService.listerIngredients());
        logger.info("Dans la liste des ingrédients");
        return ("/ingredients");
    }
}
