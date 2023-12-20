package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Etat;

import java.util.List;
import java.util.Optional;

public interface EtatDAO {
    Optional<Etat> ChercherEtatParId(long id);
    List<Etat> ListerEtats();
}
