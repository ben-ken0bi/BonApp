package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Recette;
import fr.eni.bonapp.dal.RecetteDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {
    private final RecetteDAO recetteDAO;

    RecetteServiceImpl(RecetteDAO recetteDAO) {
        this.recetteDAO = recetteDAO;
    }

    @Override
    public Optional<Recette> chercherRecetteParId(long idRecette) {
        return recetteDAO.chercherRecetteParId(idRecette);
    }

    @Override
    public List<Recette> listerRecettesParUtilisateur(long idUtilisateur) {
        return recetteDAO.listerRecettesParUtilisateur(idUtilisateur);
    }

    @Override
    public List<Recette> listerRecettesParUtilisateurMet(long idUtilisateur, long idMet) {
        return recetteDAO.listerRecettesParUtilisateurMet(idUtilisateur, idMet);
    }

    @Override
    public List<Recette> listerRecettesParUtilisateurEtat(long idUtilisateur, long idEtat) {
        return recetteDAO.listerRecettesParUtilisateurEtat(idUtilisateur, idEtat);
    }
}
