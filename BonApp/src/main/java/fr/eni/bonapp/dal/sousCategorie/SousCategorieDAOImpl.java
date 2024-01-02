package fr.eni.bonapp.dal.sousCategorie;

import fr.eni.bonapp.bo.SousCategorie;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SousCategorieDAOImpl implements SousCategorieDAO {
    Logger logger = LoggerFactory.getLogger(SousCategorieDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    }
    /**
     * Permet de chercher une sous-catégorie via son id. Si aucune sous-catégorie n'est trouvée, renvoie un
     * logger le precisant et un optional vide.
     * @param idSousCategorie
     * @return
     */
    @Override
    public Optional<SousCategorie> chercherSousCategorie(long idSousCategorie) {
        String sql = "Select id_sous_categorie, nom from sous_categorie where id_sous_categorie = ? ";
        Optional<SousCategorie> optSousCategorie = Optional.empty();
        try {
            SousCategorie sousCategorie =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) -> new SousCategorie(rs.getLong(1), rs.getString(2)),
                            idSousCategorie);
            optSousCategorie = Optional.of(sousCategorie);

        } catch (DataAccessException dae) {
            logger.error("Erreur chercherCategorieParId");
            return Optional.empty();
        }
        return optSousCategorie;
    }

    /**
     * Permet d'afficher la liste de toutes les sous-catégories par ordre alphabétique.
     * @return
     */
    @Override
    public List<SousCategorie> listerSousCategories() {
        String sql = "SELECT id_sous_categorie,nom,id_categorie FROM sous_categorie order by nom ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SousCategorie.class));
    }

    @Override
    public Optional<SousCategorie> chercherCategorie(long idSousCategorie) {
        //        Select id_sous_categorie, sc.nom, c.nom from sous_categorie sc
        //        left join categorie c on sc.id_categorie=c.id_categorie
        //        where id_sous_categorie =1
        return Optional.empty();
    }
}
