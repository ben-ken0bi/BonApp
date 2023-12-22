package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.SousCategorie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SousCategorieDAOImp implements SousCategorieDAO {
    private JdbcTemplate jdbcTemplate;

    SousCategorieDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<SousCategorie> chercherSousCategorie(long idSousCategorie) {
        return Optional.empty();
    }

    @Override
    public List<SousCategorie> listerSousCategories() {
        String sql = "SELECT id_sous_categorie,nom,id_categorie FROM sous_categorie";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SousCategorie>(SousCategorie.class));
    }

    @Override
    public Optional<SousCategorie> chercherCategorie(long idSousCategorie) {
        //        Select id_sous_categorie, sc.nom, c.nom from sous_categorie sc
        //        left join categorie c on sc.id_categorie=c.id_categorie
        //        where id_sous_categorie =1
        return Optional.empty();
    }
}
