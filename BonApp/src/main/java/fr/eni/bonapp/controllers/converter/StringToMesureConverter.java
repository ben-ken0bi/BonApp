package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.MesureService;
import fr.eni.bonapp.bo.Mesure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToMesureConverter implements Converter<String, Mesure> {

    private final MesureService mesureService;

    public StringToMesureConverter(MesureService mesureService) {
        this.mesureService = mesureService;
    }

    @Override
    public Mesure convert(String idMesure) {

        long idMesureLong = Long.parseLong(idMesure);

        return mesureService.chercherMesureParId(idMesureLong).get();
    }
}
