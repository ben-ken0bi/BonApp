package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Mesure;

import java.util.List;
import java.util.Optional;

public interface MesureService {

    List<Mesure> listerMesures();

    Optional<Mesure> chercherMesureParId(long idMesure);
}
