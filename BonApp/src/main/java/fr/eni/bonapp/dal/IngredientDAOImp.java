package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Ingredient;
import fr.eni.bonapp.bo.Mesure;
import fr.eni.bonapp.bo.SousCategorie;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientDAOImp implements IngredientDAO {
    Logger logger = LoggerFactory.getLogger(IngredientDAOImp.class);
    private SousCategorieDAO sousCategorieDAO;
    private final MesureDAO mesureDAO;

    IngredientDAOImp(SousCategorieDAO sousCategorieDAO, MesureDAO mesureDAO) {
        this.sousCategorieDAO = sousCategorieDAO;
        this.mesureDAO = mesureDAO;
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

            Optional<Mesure> optMesure = mesureDAO.chercherMesureParId(rs.getLong("id_mesure"));
            optMesure.ifPresent(ingredient::setMesure);

            ingredient.setQuantite(rs.getLong("quantite"));
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
     * Permet de récuperer la liste des ingrédients contenus dans une recette.
     *
     * @param idRecette
     * @return
     */
    @Override
    public List<Ingredient> listerIngredientPourRecette(long idRecette) {
        String sql =
                "Select re.id_recette_ingredient, re.quantite, re.id_recette,titre,re.id_ingredient,i.nom,re.id_mesure, m.mesure, s.id_sous_categorie, s.nom "
                        + "from recette_ingredient re "
                        + "join ingredient i on i.id_ingredient=re.id_ingredient "
                        + "join sous_categorie s on s.id_sous_categorie=i.id_sous_categorie "
                        + "join mesure m on m.id_mesure=re.id_mesure "
                        + "join recette r on r.id_recette=re.id_recette "
                        + "where re.id_recette=?";
        return jdbcTemplate.query(sql, new IngredientRowMapper(), idRecette);
    }
}
