package fr.eni.bonapp.controllers;

import fr.eni.bonapp.bll.UtilisateurService;
import fr.eni.bonapp.bo.Utilisateur;
import fr.eni.bonapp.dal.IngredientDAOImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class InscriptionController {
    Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    private final UtilisateurService utilisateurService;

    InscriptionController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }

    @GetMapping("/inscription")
    public String inscription(Model model){
        logger.info("Dans l'affichage du form");
        model.addAttribute("utilisateur", new Utilisateur());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String ajoutUtilisateur (@Valid Utilisateur utilisateur, BindingResult bindingResult){
        logger.info("DAns l'envoie du formulaire d'inscription");
        if (bindingResult.hasErrors()) {
            return "erreur";
        }
        utilisateurService.ajouterUtilisateur(utilisateur);
        return "/login";
    }
}
