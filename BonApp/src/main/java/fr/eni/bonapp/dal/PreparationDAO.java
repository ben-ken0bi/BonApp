package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;
import java.util.Optional;

public interface PreparationDAO {
  List<Preparation> listerPreparationsParIdRecette(long idRecette);
  Optional<Preparation> chercherPreparationParId(long idPreparation);
}
