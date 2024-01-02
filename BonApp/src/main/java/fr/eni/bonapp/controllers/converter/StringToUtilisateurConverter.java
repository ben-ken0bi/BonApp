package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.UtilisateurService;
import fr.eni.bonapp.bo.Utilisateur;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUtilisateurConverter implements Converter<String, Utilisateur> {

    private final UtilisateurService utilisateurService;

    public StringToUtilisateurConverter(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public Utilisateur convert(String idUtilisateur) {

        long idUtilisateurLong = Long.parseLong(idUtilisateur);

        return utilisateurService.chercherUtilisateurParId(idUtilisateurLong).get();
    }
}
