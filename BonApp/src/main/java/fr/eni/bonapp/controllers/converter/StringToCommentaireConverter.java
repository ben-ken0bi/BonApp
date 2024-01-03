package fr.eni.bonapp.controllers.converter;

import fr.eni.bonapp.bll.CommentaireService;
import fr.eni.bonapp.bo.Commentaire;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCommentaireConverter implements Converter<String, Commentaire> {
    private final CommentaireService commentaireService;

    public StringToCommentaireConverter(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @Override
    public Commentaire convert(String idCommentaire) {
        long idCommentaireLong = Long.parseLong(idCommentaire);

        return commentaireService.chercherCommentaireParId(idCommentaireLong).get();
    }
}
