package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.EtatService;
import fr.eni.bonapp.bo.Etat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEtatConverter implements Converter<String, Etat> {
    private EtatService etatService;

    public StringToEtatConverter(EtatService etatService) {
        this.etatService = etatService;
    }

    @Override
    public Etat convert(String idEtat) {
        long idEtatLong = Long.parseLong(idEtat);

        return etatService.chercherEtatParId(idEtatLong).get();
    }
}
