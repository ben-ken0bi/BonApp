package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Utilisateur;
import fr.eni.bonapp.dal.UtilisateurDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilisateurDetailService implements UserDetailsService {
    private final UtilisateurDAO utilisateurDAO;

    UtilisateurDetailService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        Optional<Utilisateur> optUtilisateur = utilisateurDAO.chercherUtilisateurParPseudo(pseudo);

        if (optUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("pseudo inconnu : " + pseudo);
        }
        
        Utilisateur utilisateur = optUtilisateur.get();

        return User.builder()
                .username(utilisateur.getPseudo())
                .password(utilisateur.getMdp())
                .roles(utilisateur.getAdmin() ? "ADMIN" : "USER")
                .build();
    }
}
