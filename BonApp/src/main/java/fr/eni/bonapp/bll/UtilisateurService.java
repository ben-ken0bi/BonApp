package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Utilisateur;
import fr.eni.bonapp.dal.UtilisateurDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService implements UserDetailsService {

  public final UtilisateurDAO utilisateurDAO;

  UtilisateurService(UtilisateurDAO utilisateurDAO) {
    this.utilisateurDAO = utilisateurDAO;
  }

  public Optional<Utilisateur> chercherUtilisateurParId(long idUtilisateur) {
    return utilisateurDAO.chercherUtilisateurParId(idUtilisateur);
  }

  public List<Utilisateur> listerUtilisateurs() {
    return null;
  }

  public Optional<Utilisateur> chercherUtilisateurParRecette(long idRecette) {
    return Optional.empty();
  }

  @Override
  public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

    Optional<Utilisateur> optUtilisateur = utilisateurDAO.chercherUtilisateurParPseudo(pseudo);
    Utilisateur utilisateur = optUtilisateur.get();

    return User.builder()
        .username(utilisateur.getPseudo())
        .password(utilisateur.getMdp())
        .roles(utilisateur.getAdmin() ? "ADMIN" : "USER")
        .build();
  }
}
