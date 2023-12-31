package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.MetService;
import fr.eni.bonapp.bo.Met;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToMetConverter implements Converter<String, Met> {

    private final MetService metService;

    public StringToMetConverter(MetService metServicee) {
        this.metService = metServicee;
    }

    @Override
    public Met convert(String idMet) {

        long idMetLong = Long.parseLong(idMet);

        return metService.chercherMetParId(idMetLong).get();
    }
}
