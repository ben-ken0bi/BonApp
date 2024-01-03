package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.*;
import fr.eni.bonapp.bo.*;
import fr.eni.bonapp.dto.CommentaireDTO;
import fr.eni.bonapp.dto.IngredientDTO;
import fr.eni.bonapp.dto.PreparationDTO;
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

    private final MesureService mesureService;

    RecetteController(
            RecetteService recetteService,
            MetService metService,
            EtatService etatService,
            IngredientService ingredientService,
            MesureService mesureService) {
        this.recetteService = recetteService;
        this.metService = metService;
        this.etatService = etatService;
        this.ingredientService = ingredientService;
        this.mesureService = mesureService;
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
    public String formulaireAjoutRecette(Model model, RecetteDTO recetteDTO, IngredientDTO ingredientDTO, PreparationDTO preparationDTO, CommentaireDTO commentaireDTO) {
        List<Met> mets = metService.listerMets();
        List<Etat> etats = etatService.listerEtats();
        List<Ingredient> ingredients = ingredientService.listerIngredients();
        List<Mesure> mesures = mesureService.listerMesures();

        model.addAttribute("recetteDTO", recetteDTO);
        model.addAttribute("ingredientDTO", ingredientDTO);
        model.addAttribute("preparationDTO", preparationDTO);
        model.addAttribute("commentaireDTO", commentaireDTO);

        model.addAttribute("mets", mets);
        model.addAttribute("etats", etats);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("mesures",mesures);

        return "ajoutRecette";
    }

    @PostMapping("/ajoutRecette")
    public String ajoutRecette(
            Model model, @Valid @ModelAttribute Recette recette, BindingResult bindingResult) {
        return null;
    }
}
