package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Preparation;
import java.util.List;

public interface PreparationDAO {
  List<Preparation> listerPreparationsParIdRecette(long idRecette);
}
