package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Preparation;

import java.util.List;
import java.util.Optional;

public interface PreparationDAO {
    Optional<Preparation> chercherPreparationParId(long idPreparation);
    List<Preparation> listerPreparationParRecette(long idRecette);
}
