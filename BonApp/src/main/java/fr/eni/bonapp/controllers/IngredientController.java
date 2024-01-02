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
     * Permet d'afficher la page avec la liste de tous les ingrédients.
     *
     * @param model
     * @return
     */
    @GetMapping("/ingredients")
    public String ingredients(Model model) {
        model.addAttribute("ingredients", ingredientService.listerIngredients());
        logger.info("Dans la liste des ingrédients");
        return ("/ingredients");
    }

    /**
     * Permet d'afficher la page avec le formulaire de création d'ingrédients.
     *
     * @param model
     * @return
     */
    @GetMapping("/ajoutIngredient")
    public String afficherAjoutIngredient(Model model) {
        // ajouter la gestion par l'administrateur
        model.addAttribute("ingredient", new Ingredient());
        return "/ajoutIngredient";
    }

    /**
     * Permet de valider et d'envoyer le formulaire en base de données. S'il y a des erreurs lors de
     * l'envoie du formulaire, redirige vers la page d'erreur
     *
     * @param ingredient
     * @param bindingResult
     * @return
     */
    @PostMapping("/ajoutIngredient")
    public String ajoutIngredient(@Valid Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "erreur";
        }
        ingredientService.ajouterIngredient(ingredient);

        return "redirect:/ingredients";
    }
}
