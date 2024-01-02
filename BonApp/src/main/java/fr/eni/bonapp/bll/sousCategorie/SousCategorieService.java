package fr.eni.bonapp.bll.sousCategorie;

import fr.eni.bonapp.bo.SousCategorie;

import java.util.List;
import java.util.Optional;

public interface SousCategorieService {
    Optional<SousCategorie> chercherSousCategorie(long idSousCategorie);

    public List<SousCategorie> listerSousCategories();
}
