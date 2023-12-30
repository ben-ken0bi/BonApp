package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Etat;
import fr.eni.bonapp.dal.EtatDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatServiceImp implements EtatService {
    private EtatDAO etatDAO;

    EtatServiceImp(EtatDAO etatDAO) {
        this.etatDAO = etatDAO;
    }

    @Override
    public Optional<Etat> chercherEtatParId(long idEtat) {
        return etatDAO.chercherEtatParId(idEtat);
    }

    @Override
    public List<Etat> listerEtats() {
        return etatDAO.listerEtats();
    }
}
