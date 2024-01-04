package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Optional<Utilisateur> chercherUtilisateurParId(long idUtilisateur);

    List<Utilisateur> listerUtilisateurs();

    Optional<Utilisateur> chercherUtilisateurParRecette(long idRecette);

    void ajouterUtilisateur(Utilisateur utilisateur);
}
