package fr.eni.bonapp.dal.categorie;

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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategorieDAOImpl implements CategorieDAO {
    Logger logger = LoggerFactory.getLogger(CategorieDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    }

    @Override
    public Optional<Categorie> chercherCategorieParId(long idCategorie) {
        String sql = "SELECT id_categorie, nom FROM categorie WHERE id_categorie=?";
        Optional<Categorie> optCategorie = Optional.empty();

        try {
            Categorie categorie =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) -> new Categorie(rs.getLong(1), rs.getString(2)),
                            idCategorie);
            optCategorie = Optional.of(categorie);
        } catch (DataAccessException dae) {
            logger.error("Erreur chercherCategorieParId");
        }
        return optCategorie;
    }

    @Override
    public List<Categorie> listerCategories() {
        String sql = "SELECT id_categorie, nom FROM categorie";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
