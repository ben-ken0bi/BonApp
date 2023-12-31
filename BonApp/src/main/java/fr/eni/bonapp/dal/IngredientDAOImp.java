package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.SousCategorie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDAOImp implements IngredientDAO {
    Logger logger = LoggerFactory.getLogger(IngredientDAOImp.class);
    private SousCategorieDAO sousCategorieDAO;

    IngredientDAOImp(SousCategorieDAO sousCategorieDAO) {
        this.sousCategorieDAO = sousCategorieDAO;
    }

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = this.namedParameterJdbcTemplate.getJdbcTemplate();
    }

    /**
     * RowMapper d'ingrédient.
     */
    class IngredientRowMapper implements RowMapper<Ingredient> {
        public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ingredient ingredient = new Ingredient();
            ingredient.setIdIngredient(rs.getLong("id_ingredient"));
            ingredient.setNom(rs.getString("nom"));

            Optional<SousCategorie> optSousCategorie =
                    sousCategorieDAO.chercherSousCategorie(rs.getLong("id_sous_categorie"));

            optSousCategorie.ifPresent(ingredient::setSousCategorie);

            return ingredient;
        }
    }

    /**
     * Permet de chercher un ingrédient via son id. Si aucun ingrédient n'est trouvé, renvoie un
     * logger le precisant et un optional vide.
     *
     * @param idIngredient
     * @return
     */
    @Override
    public Optional<Ingredient> chercherIngredientParId(long idIngredient) {
        String sql =
                "select id_ingredient, nom,id_sous_categorie from ingredient where id_ingredient =?";

        Ingredient ingredient =
                jdbcTemplate.queryForObject(sql, new IngredientRowMapper(), idIngredient);

        if (ingredient == null) {
            logger.error("Aucun ingrédient trouvé à cet id {}", idIngredient);
            return Optional.empty();
        }
        return Optional.of(ingredient);
    }

    /**
     * Permet d'afficher la liste de tous les ingrédients par ordre alphabétique.
     *
     * @return
     */
    @Override
    public List<Ingredient> listerIngredients() {
        logger.info("Dans la methode pour lister les ingrédients");
        String sql = "SELECT id_ingredient, nom, id_sous_categorie  FROM ingredient ORDER BY nom ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ingredient.class));
    }

    /**
     * Permet d'ajouter un ingrédient.
     *
     * @param ingredient
     */
    @Override
    public void ajouterIngredient(Ingredient ingredient) {
        KeyHolder generatedKey = new GeneratedKeyHolder();
        String sql = "INSERT INTO ingredient (nom,id_sous_categorie) VALUES (:nom,:id_sous_categorie)";

        SqlParameterSource paramSrc =
                new MapSqlParameterSource()
                        .addValue("nom", ingredient.getNom())
                        .addValue("id_sous_categorie", ingredient.getSousCategorie().getIdSousCategorie());

        namedParameterJdbcTemplate.update(sql, paramSrc, generatedKey);
    }

    /**
     * Permet de récupere la liste des ingrédients contenus dans une recette.
     *
     * @param idRecette
     * @return
     */
    @Override
    public List<Ingredient> listerIngredientPourRecette(long idRecette) {
        String sql =
                "Select id_recette_ingredient, quantite, id_recette,id_ingredient,id_mesure from recette_ingredient where id_recette=?";
        // todo : changer la requete pour recupere les données manquantes : [Ingredient{idIngredient=40,
        // nom='null', sousCategorie=null, quantite=250.0, mesure=null}
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ingredient.class), idRecette);
    }
}
