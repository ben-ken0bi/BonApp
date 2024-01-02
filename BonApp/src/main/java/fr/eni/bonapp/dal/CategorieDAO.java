package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieDAO {
    Optional<Categorie> chercherCategorieParId(long idCategorie);

    List<Categorie> listerCategories();

}
