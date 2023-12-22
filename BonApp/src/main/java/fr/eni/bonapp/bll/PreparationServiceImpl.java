package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Preparation;
import fr.eni.bonapp.dal.PreparationDAO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PreparationServiceImpl implements PreparationService {
  private PreparationDAO preparationDAO;

  @Override
  public List<Preparation> listerPreparationsParIdRecette(long idRecette) {
    return preparationDAO.listerPreparationsParIdRecette(idRecette);
  }
}
