package fr.eni.bonapp.bll.met;

import fr.eni.bonapp.bo.Met;

import java.util.List;
import java.util.Optional;

public interface MetService {
    Optional<Met> chercherMetParId(long idMet);
    List<Met> listerMets();

}
