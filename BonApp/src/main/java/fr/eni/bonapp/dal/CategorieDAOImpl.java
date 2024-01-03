package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Categorie;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategorieDAOImpl implements CategorieDAO {
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(CategorieDAOImpl.class);

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Permet de chercher une catégorie via son id. Si aucune catégorie n'est trouvée, renvoie un
     * logger le precisant et un optional vide.
     *
     * @param idCategorie
     * @return
     */
    @Override
    public Optional<Categorie> chercherCategorieParId(long idCategorie) {
        logger.info("Dans la recherche de catégorie pour l'id suivant {}", idCategorie);
        String sql = "SELECT id_categorie, nom FROM categorie WHERE id_categorie=?";
        Optional<Categorie> optCategorie;

        try {
            Categorie categorie =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) -> new Categorie(rs.getLong(1), rs.getString(2)),
                            idCategorie);
            optCategorie = Optional.of(categorie);
        } catch (DataAccessException dae) {
            logger.error("Pas de catégorie pour l'id suivant {}", idCategorie);
            return Optional.empty();
        }
        return optCategorie;
    }

    /**
     * Permet d'afficher la liste de toutes les catégories
     *
     * @return
     */
    @Override
    public List<Categorie> listerCategories() {
        logger.info("Dans lister les catégories");
        String sql = "SELECT id_categorie, nom FROM categorie";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
