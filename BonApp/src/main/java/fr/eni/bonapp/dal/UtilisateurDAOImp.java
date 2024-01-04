package fr.eni.bonapp.dal;

import fr.eni.bonapp.bo.Utilisateur;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurDAOImp implements UtilisateurDAO {
    Logger logger = LoggerFactory.getLogger(UtilisateurDAOImp.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = this.namedParameterJdbcTemplate.getJdbcTemplate();
    }

    /**
     * Permet de chercher un utilisateur via son id. Si aucun utilisateur n'est trouvé, renvoie un
     * logger le precisant et un optional vide.
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public Optional<Utilisateur> chercherUtilisateurParId(long idUtilisateur) {
        logger.info("Dans Utilisateur avec la methode pour trouver son id numéro {}", idUtilisateur);
        String sql =
                "Select id_utilisateur, pseudo, mdp, nom, prenom, email, admin from utilisateur where id_utilisateur =?";
        Optional<Utilisateur> optUtilisateur = Optional.empty();

        try {
            Utilisateur utilisateur =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) ->
                                    new Utilisateur(
                                            rs.getLong(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getString(6),
                                            rs.getBoolean(7)),
                            idUtilisateur);
            optUtilisateur = Optional.of(utilisateur);
        } catch (DataAccessException dae) {
            logger.error("Aucun utilisateur existe avec l'id suivant : {}", idUtilisateur);
            return Optional.empty();
        }
        return optUtilisateur;
    }

    @Override
    public Optional<Utilisateur> chercherUtilisateurParPseudo(String pseudo) {
        logger.info("Dans Utilisateur avec la methode pour trouver son pseudo {}", pseudo);
        String sql =
                "Select id_utilisateur,nom,prenom, pseudo,mdp, email, admin from utilisateur where pseudo =?";
        Optional<Utilisateur> optUtilisateur = Optional.empty();

        try {
            Utilisateur utilisateur =
                    jdbcTemplate.queryForObject(
                            sql,
                            (ResultSet rs, int rowNum) ->
                                    new Utilisateur(
                                            rs.getLong(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getString(6),
                                            rs.getBoolean(7)),
                            pseudo);

            optUtilisateur = Optional.of(utilisateur);
        } catch (DataAccessException dae) {
            logger.error("Aucun utilisateur existe avec le pseudo suivant : {}", pseudo);
            throw dae;
        }
        return optUtilisateur;
    }

    @Override
    public List<Utilisateur> listerUtilisateurs() {
        return null;
    }

    @Override
    public Optional<Utilisateur> chercherUtilisateurParRecette(long idRecette) {
        return Optional.empty();
    }

    /**
     * Permet d'ajouter un utilisateur.Utilisée pour l'inscription.
     */
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        logger.info("Dans l'inscription d'un nouvel utilisateur");
        String sql =
                "INSERT INTO utilisateur (nom,prenom,pseudo, mdp, email,admin) VALUES (:nom,:prenom,:pseudo,:mdp,:email,:admin)";
        KeyHolder generatedKey = new GeneratedKeyHolder();
        SqlParameterSource paramSrc =
                new MapSqlParameterSource()
                        .addValue("nom", utilisateur.getNom())
                        .addValue("prenom", utilisateur.getPrenom())
                        .addValue("pseudo", utilisateur.getPseudo())
                        .addValue("mdp", utilisateur.getMdp())
                        .addValue("email", utilisateur.getEmail())
                        .addValue("admin", utilisateur.getAdmin());

        utilisateur.setAdmin(false);

        namedParameterJdbcTemplate.update(sql, paramSrc, generatedKey);
    }
}
