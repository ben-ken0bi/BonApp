package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.SousCategorie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class SousCategorieImp implements SousCategorieDAO{
    private JdbcTemplate jdbcTemplate;

    SousCategorieImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
       public Optional<SousCategorie> chercherSousCategorie(long idSousCategorie) {
        return Optional.empty();
    }

    @Override
    public List<SousCategorie> listerSousCategories() {
        String sql ="SELECT id_sous_categorie,nom,id_categorie FROM sous_categorie";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SousCategorie>(SousCategorie.class));
    }

    @Override
    public Optional<SousCategorie> chercherCategorie(long idSousCategorie, long idCategorie) {
        return Optional.empty();
    }
}
