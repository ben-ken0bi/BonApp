package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;

public interface PreparationService {
  List<Preparation> listerPreparationsParIdRecette(long idRecette);
}
