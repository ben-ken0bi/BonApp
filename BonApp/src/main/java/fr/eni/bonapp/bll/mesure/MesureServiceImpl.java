package fr.eni.bonapp.bll.mesure;

import fr.eni.bonapp.bo.Mesure;
import fr.eni.bonapp.dal.mesure.MesureDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesureServiceImpl implements MesureService {
    public final MesureDAO mesureDAO;

    public MesureServiceImpl(MesureDAO mesureDAO) {
        this.mesureDAO = mesureDAO;
    }

    @Override
    public List<Mesure> listerMesures() {
        return mesureDAO.listerMesures();
    }

    @Override
    public Optional<Mesure> chercherMesureParId(long idMesure) {
        return mesureDAO.chercherMesureParId(idMesure);
    }
}
