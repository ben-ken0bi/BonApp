package fr.eni.bonapp.dal.mappers;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.SousCategorie;
import fr.eni.bonapp.dal.CategorieDAO;
import fr.eni.bonapp.dal.SousCategorieDAO;
import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class IngredientRowMapper implements RowMapper<Ingredient> {
    private CategorieDAO categorieDAO;
    private SousCategorieDAO sousCategorieDAO;

IngredientRowMapper(CategorieDAO categorieDAO,SousCategorieDAO sousCategorieDAO){
    this.categorieDAO=categorieDAO;
    this.sousCategorieDAO=sousCategorieDAO;

}


    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
    Ingredient ingredient = new Ingredient();
    ingredient.setIdIngredient(rs.getLong("id_ingredient"));
    ingredient.setNom(rs.getString("nom"));

    Optional<SousCategorie> optSousCategorie = sousCategorieDAO.chercherSousCategorie(rs.getLong("id_sous_categorie"));
    ingredient.setSousCategorie(optSousCategorie.get());


    return ingredient;
    }


//    @Override
//    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Film film = new Film();
//        film.setId(rs.getLong("id"));
//        film.setTitre(rs.getString("titre"));
//        film.setSynopsis(rs.getString("synopsis"));
//        film.setAnnee(rs.getInt("annee"));
//        film.setDuree(rs.getInt("duree"));
//
//        Optional<Genre> optGenre = genreDAO.findGenreById(rs.getLong("genre_id"));
//        film.setGenre(optGenre.get());
//
//        Optional<Participant> optRealisateur = participantDAO.findParticipantById(rs.getLong("realisateur_id"));
//        film.setRealisateur(optRealisateur.get());
//
//        List<Participant> acteurs = participantDAO.findActorsByFilmId(rs.getLong("id"));
//        for (Participant acteur : acteurs) {
//            film.ajouterActeur(acteur);
//        }
//        return film;
//    }
@Override
public int[] getRowsForPaths(TreePath[] path) {
    return new int[0];
}
}
