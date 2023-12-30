package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;
import java.util.Optional;

public interface PreparationService {
  List<Preparation> listerPreparationsParIdRecette(long idRecette);
  public Optional<Preparation> chercherPreparationParId(long idPreparation);
}
