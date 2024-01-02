package fr.eni.bonapp.bll;

import fr.eni.bonapp.bo.SousCategorie;
import fr.eni.bonapp.dal.SousCategorieDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service("SousCategorieService")
public class SousCategorieServiceImp implements SousCategorieService {
    private final SousCategorieDAO sousCategorieDAO;

    SousCategorieServiceImp(SousCategorieDAO sousCategorieDAO) {
        this.sousCategorieDAO = sousCategorieDAO;
    }

    @Override
    public Optional<SousCategorie> chercherSousCategorie(long idSousCategorie) {
        return sousCategorieDAO.chercherSousCategorie(idSousCategorie);
    }

    @Override
    public List<SousCategorie> listerSousCategories() {
        return sousCategorieDAO.listerSousCategories();
    }
}
