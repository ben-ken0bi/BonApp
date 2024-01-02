package fr.eni.bonapp.bll.sousCategorie;

import fr.eni.bonapp.bo.SousCategorie;
import fr.eni.bonapp.dal.sousCategorie.SousCategorieDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service("SousCategorieService")
public class SousCategorieServiceImpl implements SousCategorieService {
    public final SousCategorieDAO sousCategorieDAO;

    SousCategorieServiceImpl(SousCategorieDAO sousCategorieDAO) {
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
