package fr.eni.bonapp.bll.preparation;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;

public interface PreparationService {
  List<Preparation> listerPreparationsParIdRecette(long idRecette);
}
