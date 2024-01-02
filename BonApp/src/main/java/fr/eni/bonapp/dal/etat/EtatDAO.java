package fr.eni.bonapp.dal.etat;

import fr.eni.bonapp.bo.Etat;

import java.util.List;
import java.util.Optional;

public interface EtatDAO {
    Optional<Etat> chercherEtatParId(long idEtat);
    List<Etat> listerEtats();
}
