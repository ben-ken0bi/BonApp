package fr.eni.bonapp.bo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Date;

public class Commentaire {
    private long idCommentaire;
    @Size(min = 1, max = 250)
    private String commentaire;
    @NotNull
    private LocalDateTime date;


}
