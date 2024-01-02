package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Met;

import java.util.List;
import java.util.Optional;

public interface MetDAO {
    Optional<Met> chercherMetParId(long idMet);

    List<Met> listerMets();
}
