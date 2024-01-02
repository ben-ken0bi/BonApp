package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.PreparationService;
import fr.eni.bonapp.bo.Preparation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPreparationConverter implements Converter<String, Preparation> {

    private final PreparationService preparationService;

    public StringToPreparationConverter(PreparationService preparationService) {
        this.preparationService = preparationService;
    }

    @Override
    public Preparation convert(String idPreparation) {

        long idPreparationLong = Long.parseLong(idPreparation);

        return preparationService.chercherPreparationParId(idPreparationLong).get();
    }
}
