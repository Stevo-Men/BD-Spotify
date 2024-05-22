package com.example.demobd3fx.dao;

import com.example.demobd3fx.database.Database;
import com.example.demobd3fx.models.MaisonDeDisque;
import com.example.demobd3fx.models.Projet;
import com.example.demobd3fx.viewModels.ProjetViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAO extends DAO implements ResultSetHandler<Projet> {

    public ProjetDAO() {
        super();
    }

    public ProjetDAO(Database db) {
        super(db);
    }

    @Override
    public Projet handle(ResultSet rs) throws SQLException {
        Projet projet = new Projet();
        projet.setId(rs.getInt("projet.projet_id"));
        projet.setTitre(rs.getString("projet.projet_titre"));
        projet.setDateSortie(rs.getString("projet.projet_date_sortie"));
        projet.setArtiste(rs.getString("projet.artiste_alias"));
        projet.setMaisonDeDisque(rs.getString("projet.maison_de_disque_nom"));
        return projet;
    }

    @Override
    public List<Projet> findAll() {
        return db.selectInstances("SELECT projet_id \"projet.projet_id\", " +
                "projet_titre \"projet.projet_titre\", " +
                "projet_date_sortie \"projet.projet_date_sortie\", " +
                "artiste_alias \"projet.artiste_alias\", " +
                "maison_de_disque_nom \"projet.maison_de_disque_nom\" " +
                "FROM info_projet", this);

//        try (ResultSet rs = db.executeQuery(sql)) {
//            while (rs.next()) {
//                projets.add(handle(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("projet:" + projets);
//        return projets;
    }



    @Override
    public Projet findById(int id) {
        Projet projet = null;
        String sql = "SELECT * FROM info_projet WHERE projet_id = ?";

        try (ResultSet rs = db.executeQuery(sql, id)) {
            if (rs.next()) {
                projet = handle(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projet;
    }


//    public List<Projet> findByArtistAlias(String artistAlias) {
//        List<Projet> projets = new ArrayList<>();
//        String sql = "SELECT * FROM info_projet WHERE artiste_alias = ?";
//
//        try (ResultSet rs = db.executeQuery(sql, artistAlias)) {
//            while (rs.next()) {
//                projets.add(handle(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return projets;
//    }

    public List<Projet> findByArtistAlias(String artistAlias)  {
        return db.selectInstances("SELECT projet_id \"projet.projet_id\", " +
                        "projet_titre \"projet.projet_titre\", " +
                        "projet_date_sortie \"projet.projet_date_sortie\", " +
                        "artiste_alias \"projet.artiste_alias\", " +
                        "maison_de_disque_nom \"projet.maison_de_disque_nom\" " +
                        "FROM info_projet WHERE artiste_alias",
                this, artistAlias);
    }


}
