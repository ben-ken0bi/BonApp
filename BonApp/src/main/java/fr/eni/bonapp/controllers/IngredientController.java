package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.IngredientService;
import fr.eni.bonapp.bo.Ingredient;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
    Logger logger = LoggerFactory.getLogger(IngredientController.class);
    private IngredientService ingredientService;

    IngredientController(IngredientService ingredientService) {
        logger.debug("Dans constructeur controller");
        this.ingredientService = ingredientService;
    }

    /**
     * lISTES D'INGREDIENTS*
     */
    @GetMapping("/ingredients")
    public String ingredients(Model model) {
        model.addAttribute("ingredients", ingredientService.listerIngredients());
        logger.info("Dans la liste des ingrédients");
        return ("/ingredients");
    }

    /**
     * FORMULAIRE AJOUT D'INGREDIENTS*
     */
    @GetMapping("/ajoutIngredient")
    public String afficherAjoutIngredient(Model model) {
        // ajouter la gestion par l'administrateur
        model.addAttribute("ingredient", new Ingredient());
        return "/ajoutIngredient";
    }

    @PostMapping("/ajoutIngredient")
    public String ajoutIngredient(@Valid Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "accueil";
        }
        ingredientService.ajouterIngredient(ingredient);

        return "redirect:/ingredients";
    }
}
