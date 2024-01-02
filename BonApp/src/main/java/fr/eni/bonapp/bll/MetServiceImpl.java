package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Met;
import fr.eni.bonapp.dal.MetDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MetServiceImpl implements MetService {
    MetDAO metDAO;

    public MetServiceImpl(MetDAO metDAO) {
        this.metDAO = metDAO;
    }

    @Override
    public Optional<Met> chercherMetParId(long idMet) {
        return metDAO.chercherMetParId(idMet);
    }

    @Override
    public List<Met> listerMets() {
        return metDAO.listerMets();
    }
}
