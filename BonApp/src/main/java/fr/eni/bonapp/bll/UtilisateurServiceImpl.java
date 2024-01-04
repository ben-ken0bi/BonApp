package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Utilisateur;
import fr.eni.bonapp.dal.UtilisateurDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    public final UtilisateurDAO utilisateurDAO;

    UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
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

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.ajouterUtilisateur(utilisateur);
    }


}

