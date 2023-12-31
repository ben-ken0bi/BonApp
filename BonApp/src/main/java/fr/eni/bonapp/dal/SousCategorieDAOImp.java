package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Categorie;
import fr.eni.bonapp.bo.SousCategorie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SousCategorieDAOImp implements SousCategorieDAO {
    private JdbcTemplate jdbcTemplate;
    private final CategorieDAO categorieDAO;
    Logger logger = LoggerFactory.getLogger(SousCategorieDAOImp.class);

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    SousCategorieDAOImp(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    /**
     * RowMapper de Sous-Catégorie
     */
    class SousCategorieRowMapper implements RowMapper<SousCategorie> {

        @Override
        public SousCategorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            logger.info("Dans le rowMapper de sous-catégorie");
            SousCategorie sousCategorie = new SousCategorie();
            sousCategorie.setIdSousCategorie(rs.getLong("id_sous_categorie"));
            sousCategorie.setNom(rs.getString("nom"));

            Optional<Categorie> optCategorie =
                    categorieDAO.chercherCategorieParId(rs.getLong("id_categorie"));
            optCategorie.ifPresent(sousCategorie::setCategorie);
            return sousCategorie;
        }
    }

    /**
     * Permet de chercher une sous-catégorie via son id. Si aucune sous-catégorie n'est trouvée,
     * renvoie un logger le precisant et un optional vide.
     *
     * @param idSousCategorie
     * @return
     */
    @Override
    public Optional<SousCategorie> chercherSousCategorie(long idSousCategorie) {
        logger.info("Dans chercher sous catégorie pour l'id {}", idSousCategorie);
        String sql =
                "Select id_sous_categorie, sc.nom,c.id_categorie, c.nom "
                        + "from sous_categorie sc "
                        + "join categorie c on c.id_categorie=sc.id_categorie "
                        + "where id_sous_categorie = ?";
        Optional<SousCategorie> optSousCategorie;
        try {
            SousCategorie sousCategorie =
                    jdbcTemplate.queryForObject(sql, new SousCategorieRowMapper(), idSousCategorie);
            optSousCategorie = Optional.of(sousCategorie);

        } catch (DataAccessException dae) {
            logger.error("Aucune sous-catégorie trouvée à cet id {}", idSousCategorie);
            return Optional.empty();
        }
        return optSousCategorie;
    }

    /**
     * Permet d'afficher la liste de toutes les sous-catégories par ordre alphabétique.
     *
     * @return
     */
    @Override
    public List<SousCategorie> listerSousCategories() {
        logger.info("Dans lister les sous-catégories");
        String sql = "SELECT id_sous_categorie,nom,id_categorie FROM sous_categorie order by nom ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SousCategorie.class));
    }
}
