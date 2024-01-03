package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.EtatService;
import fr.eni.bonapp.bll.IngredientService;
import fr.eni.bonapp.bll.MetService;
import fr.eni.bonapp.bll.RecetteService;
import fr.eni.bonapp.bo.Etat;
import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Met;
import fr.eni.bonapp.bo.Recette;
import fr.eni.bonapp.dto.RecetteDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RecetteController {
    Logger logger = LoggerFactory.getLogger(RecetteController.class);
    private final RecetteService recetteService;
    private final MetService metService;
    private final EtatService etatService;
    private final IngredientService ingredientService;

    RecetteController(
            RecetteService recetteService,
            MetService metService,
            EtatService etatService,
            IngredientService ingredientService) {
        this.recetteService = recetteService;
        this.metService = metService;
        this.etatService = etatService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recettes")
    public String afficherToutesLesRecettes() {
        return "recettes";
    }

    @GetMapping("/recette/{id}")
    public String afficherDetail(@PathVariable("id") long idRecette, Model model) {
        logger.info("dans l'affichage de la recette avec l'id {}", idRecette);
        Recette recette;
        Optional<Recette> optRecette = recetteService.chercherRecetteParId(idRecette);
        recette = optRecette.get();

        model.addAttribute("recette", recette);
        return "recette";
    }

    @GetMapping("/ajoutRecette")
    public String formulaireAjoutRecette(Model model, @ModelAttribute Recette recette) {
        List<Met> mets = metService.listerMets();
        List<Etat> etats = etatService.listerEtats();
        List<Ingredient> ingredients = ingredientService.listerIngredients();

        model.addAttribute("recette",recette);
        model.addAttribute("mets", mets);
        model.addAttribute("etats", etats);
        model.addAttribute("ingredients", ingredients);

        return "ajoutRecette";
    }

    @PostMapping("/ajoutRecette")
    public String ajoutRecette(
            Model model, @Valid @ModelAttribute Recette recette, BindingResult bindingResult) {
        return null;
    }
}
