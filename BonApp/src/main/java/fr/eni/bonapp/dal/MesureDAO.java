package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Mesure;

import java.util.List;
import java.util.Optional;

public interface MesureDAO {
    Optional<Mesure> chercherMesureParId(long idMesure);
    List<Mesure> listerMesures();
}
