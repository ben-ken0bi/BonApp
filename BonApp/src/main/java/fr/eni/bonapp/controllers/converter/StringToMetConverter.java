package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.MetService;
import fr.eni.bonapp.bo.Met;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToMetConverter implements Converter<String, Met> {

    private MetService metService;

    public StringToMetConverter(MetService metServicee) {
        this.metService = metServicee;
    }

    @Override
    public Met convert(String idMet) {

        Long idMetLong = Long.parseLong(idMet);

        return metService.chercherMetParId(idMetLong).get();
    }
}
