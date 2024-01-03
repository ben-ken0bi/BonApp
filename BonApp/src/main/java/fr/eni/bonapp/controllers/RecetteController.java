package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.RecetteService;
import fr.eni.bonapp.bo.Recette;
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

  RecetteController(RecetteService recetteService) {
    this.recetteService = recetteService;
  }

  @GetMapping("/recettes")
  public String afficherToutesLesRecettes(Model model, long idUtilisateur) {
    model.addAttribute("recettes", recetteService.listerRecettesParUtilisateur( idUtilisateur));
    return ("/recettes");
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
}
