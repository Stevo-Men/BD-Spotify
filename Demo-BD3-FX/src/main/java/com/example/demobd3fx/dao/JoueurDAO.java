package com.example.demobd3fx.dao;

import com.example.demobd3fx.database.Database;
import com.example.demobd3fx.models.Joueur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAO extends DAO implements ResultSetHandler<Joueur> {

    public JoueurDAO() {
        super();
    }

    public JoueurDAO(Database db) {
        super(db);
    }

    public Joueur findById(int id) {

        return db.selectInstance(
                "SELECT id \"joueur.id\", " +
                        "nom \"joueur.nom\", " +
                        "prenom \"joueur.prenom\" " +
                        "FROM joueur WHERE id = ?",
                this, id);
    }

    public Joueur findNext(int previous) {
        return db.selectInstance(
                "SELECT id \"joueur.id\", " +
                        "nom \"joueur.nom\", " +
                        "prenom \"joueur.prenom\" " +
                        "FROM joueur WHERE id > ? ORDER BY id LIMIT 1",
                this, previous);
    }

    public List<Joueur> findAll() {
        return db.selectInstances("SELECT id \"joueur.id\", " +
                "nom \"joueur.nom\", " +
                "prenom \"joueur.prenom\" " +
                "FROM joueur", this);
    }

    @Override
    public Joueur handle(ResultSet rs) throws SQLException {
        Joueur joueur = new Joueur();
        joueur.setId(rs.getInt("joueur.id"));
        joueur.setFirstname(rs.getString("joueur.prenom"));
        joueur.setLastname(rs.getString("joueur.nom"));
        return joueur;
    }

    public void save(Joueur model) {
        if (model.getId() != 0){
            //UPDATE
        } else {
            //INSERT
        }
    }
}
