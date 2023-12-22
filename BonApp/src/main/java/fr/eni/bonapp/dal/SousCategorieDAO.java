package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.SousCategorie;

import java.util.List;
import java.util.Optional;

public interface SousCategorieDAO {
    Optional<SousCategorie> chercherSousCategorie(long idSousCategorie);
    List<SousCategorie> listerSousCategories();
}
