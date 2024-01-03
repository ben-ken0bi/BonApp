package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Preparation;
import fr.eni.bonapp.dal.PreparationDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PreparationServiceImpl implements PreparationService {
    private final PreparationDAO preparationDAO;

    PreparationServiceImpl(PreparationDAO preparationDAO) {
        this.preparationDAO = preparationDAO;
    }

    @Override
    public List<Preparation> listerPreparationsParIdRecette(long idRecette) {
        return preparationDAO.listerPreparationsParIdRecette(idRecette);
    }

    @Override
    public Optional<Preparation> chercherPreparationParId(long idPreparation) {
        return preparationDAO.chercherPreparationParId(idPreparation);
    }
}
