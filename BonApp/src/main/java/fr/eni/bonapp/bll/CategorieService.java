package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {
    Optional<Categorie> chercherCategorieParId(long idCategorie);

    List<Categorie> listerCategories();
}
