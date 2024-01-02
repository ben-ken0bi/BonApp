package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.EtatService;
import fr.eni.bonapp.bll.IngredientService;
import fr.eni.bonapp.bll.MetService;
import fr.eni.bonapp.bll.RecetteService;
import fr.eni.bonapp.bo.Etat;
import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Met;
import fr.eni.bonapp.bo.Recette;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecetteController {
    Logger logger = LoggerFactory.getLogger(RecetteController.class);
    private RecetteService recetteService;

    RecetteController(RecetteService recetteService,IngredientService ingredientService,EtatService etatService,MetService metService) {
        this.recetteService = recetteService;
        this.ingredientService=ingredientService;
        this.etatService=etatService;
        this.metService=metService;
    }

    /**
     * Permet d'afficher sur une page HTML toutes les recettes
     * renvoie sur la page recettes dédiée
     *
     * @return
     */
    @GetMapping("/recettes")
    public String afficherToutesLesRecettes() {
        return "recettes";
    }

    /**
     * Permet d'afficher sur une page HTML le détail d'une recette
     * renvoie sur la page recettes dédiée
     *
     * @param idRecette
     * @param model
     * @return
     */
    @GetMapping("/recette/{id}")
    public String afficherDetail(@PathVariable("id") long idRecette, Model model) {
        logger.info("dans l'affichage de la recette avec l'id {}", idRecette);
        Recette recette;
        Optional<Recette> optRecette = recetteService.chercherRecetteParId(idRecette);
        recette = optRecette.get();

        model.addAttribute("recette", recette);
        return "recette";
    }

    /**
     * Permet d'afficher sur une page HTML toutes les recettes par utilisateur
     * renvoie sur la page recettes dédiées
     *
     * @param idUtilisateur
     * @param model
     * @return
     */
    @GetMapping("/recettes/{idUtilisateur}")
    public String afficherRecettesParUtilisateur(
            @PathVariable("id") long idUtilisateur, Model model) {
        logger.info("Affichage des recettes pour l'utilisateur avec l'id {}", idUtilisateur);

        // Assuming recetteService has a method to retrieve recipes by user ID
        List<Recette> recettes = recetteService.listerRecettesParUtilisateur(idUtilisateur);

        // Add the list of recipes to the model
        model.addAttribute("recettes", recettes);

        return "recettes";
    }

    /**
     * Permet d'afficher sur une page HTML toutes les recettes par utilisateur et par met choisi
     * renvoie sur la page recettes dédiées
     *
     * @param idUtilisateur
     * @param idMet
     * @param model
     * @return
     */
    @GetMapping("/recettes/met/{idUtilisateur}/{idMet}")
    public String afficherRecettesParUtilisateurEtMet(
            @PathVariable("idUtilisateur") long idUtilisateur,
            @PathVariable("idMet") long idMet,
            Model model) {
        logger.info(
                "Affichage des recettes pour l'utilisateur avec l'id {} et le met avec l'id {}",
                idUtilisateur,
                idMet);

        List<Recette> recettes = recetteService.listerRecettesParUtilisateurMet(idUtilisateur, idMet);

        model.addAttribute("recettes", recettes);

        return "recettes";
    }

    /**
     * Permet d'afficher sur une page HTML toutes les recettes par utilisateur suivant l'état de la recette
     * renvoie sur la page recettes dédiées
     *
     * @param idUtilisateur
     * @param idEtat
     * @param model
     * @return
     */
    @GetMapping("/recettes/etat/{idUtilisateur}/{idEtat}")
    public String afficherRecettesParUtilisateurEtEtat(
            @PathVariable("idUtilisateur") long idUtilisateur,
            @PathVariable("idEtat") long idEtat,
            Model model) {
        logger.info(
                "Affichage des recettes pour l'utilisateur avec l'id {} et l'état avec l'id {}",
                idUtilisateur,
                idEtat);

        List<Recette> recettes = recetteService.listerRecettesParUtilisateurEtat(idUtilisateur, idEtat);

        model.addAttribute("recettes", recettes);

        return "recettes";
    }
    private IngredientService ingredientService;
    private MetService metService;
    private EtatService etatService;
    @GetMapping("/formRecette")
    public String formRecette(Model model, Recette recette){
        List<Ingredient> ingredients = ingredientService.listerIngredients();
        List<Met> mets = metService.listerMets();
        List<Etat> etats = etatService.listerEtats();

        model.addAttribute("ingredients",ingredients);
        model.addAttribute("etats",etats);
        model.addAttribute("mets",mets);
        model.addAttribute("recette", recette);
        return "formRecette";
    }

}
