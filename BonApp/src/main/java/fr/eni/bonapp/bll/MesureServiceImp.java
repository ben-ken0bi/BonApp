package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Mesure;
import fr.eni.bonapp.dal.MesureDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MesureServiceImp implements MesureService {
    private final MesureDAO mesureDAO;

    public MesureServiceImp(MesureDAO mesureDAO) {
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
