package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Categorie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class CategorieDAOImpl implements CategorieDAO{
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(CategorieDAOImpl.class);
    CategorieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Categorie> chercherCategorieParId(long idCategorie) {
        String sql = "SELECT id_categorie, nom FROM categorie WHERE id_categorie=?";
        Optional<Categorie> optCategorie = Optional.empty();

        try {
            Categorie categorie = jdbcTemplate.queryForObject(sql,
                    (ResultSet rs, int rowNum) -> new Categorie(rs.getLong(1), rs.getString(2)));
            optCategorie = Optional.of(categorie);
        } catch (DataAccessException dae) {
            logger.error("Erreur chercherCategorieParId");
        }
        return optCategorie ;
    }

    @Override
    public List<Categorie> listerCategories() {
        String sql = "SELECT id_categorie, nom FROM categorie";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Categorie>(Categorie.class));
    }


}