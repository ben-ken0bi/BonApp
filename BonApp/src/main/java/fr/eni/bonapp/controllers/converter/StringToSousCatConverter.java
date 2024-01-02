package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.SousCategorieService;
import fr.eni.bonapp.bo.SousCategorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSousCatConverter implements Converter<String, SousCategorie> {

    private final SousCategorieService sousCategorieService;

    public StringToSousCatConverter(SousCategorieService sousCategorieService) {
        this.sousCategorieService = sousCategorieService;
    }

    @Override
    public SousCategorie convert(String idSousCategorie) {

        long idSousCategorieLong = Long.parseLong(idSousCategorie);

        return sousCategorieService.chercherSousCategorie(idSousCategorieLong).get();
    }
}
