package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Utilisateur;
import fr.eni.bonapp.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UtilisateurServiceImp implements UtilisateurService{

    public final UtilisateurDAO utilisateurDAO;

    UtilisateurServiceImp(UtilisateurDAO utilisateurDAO){
        this.utilisateurDAO=utilisateurDAO;
    }
    @Override
    public Optional<Utilisateur> chercherUtilisateurParId(long idUtilisateur) {
        return utilisateurDAO.chercherUtilisateurParId(idUtilisateur);
    }

    @Override
    public List<Utilisateur> listerUtilisateurs() {
        return null;
    }

    @Override
    public Optional<Utilisateur> chercherUtilisateurParRecette(long idRecette) {
        return Optional.empty();
    }
}
