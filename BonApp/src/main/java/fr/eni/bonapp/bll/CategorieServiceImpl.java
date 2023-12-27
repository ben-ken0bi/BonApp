package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.Categorie;
import fr.eni.bonapp.dal.CategorieDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategorieServiceImpl implements CategorieService {
  private CategorieDAO categorieDAO;

  @Override
  public Optional<Categorie> chercherCategorieParId(long idCategorie) {
    return categorieDAO.chercherCategorieParId(idCategorie);
  }

  @Override
  public List<Categorie> listerCategories() {
    return categorieDAO.listerCategories();
  }
}
