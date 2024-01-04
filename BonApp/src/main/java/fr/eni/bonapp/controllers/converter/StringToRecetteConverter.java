package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.RecetteService;
import fr.eni.bonapp.bo.Recette;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRecetteConverter implements Converter<String, Recette> {

    private final RecetteService recetteService;

    public StringToRecetteConverter(RecetteService recetteService) {
        this.recetteService = recetteService;
    }

    @Override
    public Recette convert(String idRecette) {

        long idRecetteLong = Long.parseLong(idRecette);

        return recetteService.chercherRecetteParId(idRecetteLong).get();
    }
}
