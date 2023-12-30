package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Etat;

import java.util.List;
import java.util.Optional;

public interface EtatService {
    Optional<Etat> chercherEtatParId(long idEtat);

    List<Etat> listerEtats();
}
