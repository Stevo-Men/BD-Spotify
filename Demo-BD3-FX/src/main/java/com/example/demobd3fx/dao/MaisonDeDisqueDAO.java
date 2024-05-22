package com.example.demobd3fx.dao;

import com.example.demobd3fx.models.Artiste;
import com.example.demobd3fx.models.MaisonDeDisque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MaisonDeDisqueDAO extends DAO implements ResultSetHandler<MaisonDeDisque>{
    @Override
    public MaisonDeDisque findById(int id) {

        return db.selectInstance(
                "SELECT id \"maison_de_disque.id\", " +
                        "nom \"maison_de_disque.nom\", " +
                        "annee_creation \"maison_de_disque.annee_creation\", " +
                        "lieu \"maison_de_disque.lieu\", " +
                        "fondateur \"maison_de_disque.fondateur\" " +
                        "FROM maison_de_disque WHERE id = ?",
                this, id);
    }

    public MaisonDeDisque findNext(int previous) {
        return db.selectInstance(
                "SELECT id \"maison_de_disque.id\", " +
                        "nom \"maison_de_disque.nom\", " +
                        "annee_creation \"maison_de_disque.annee_creation\", " +
                        "lieu \"maison_de_disque.lieu\", " +
                        "fondateur \"maison_de_disque.fondateur\" " +
                        "FROM maison_de_disque WHERE id > ? ORDER BY id LIMIT 1",
                this, previous);
    }

    public List<MaisonDeDisque> findAll() {
        return db.selectInstances("SELECT id \"maison_de_disque.id\", " +
                "nom \"maison_de_disque.nom\", " +
                "annee_creation \"maison_de_disque.annee_creation\", " +
                "lieu \"maison_de_disque.lieu\", " +
                "fondateur \"maison_de_disque.fondateur\" " +
                "FROM maison_de_disque", this);
    }

    @Override
    public MaisonDeDisque handle(ResultSet rs) throws SQLException {
        MaisonDeDisque maisonDeDisque = new MaisonDeDisque();
        maisonDeDisque.setId(rs.getInt("maison_de_disque.id"));
        maisonDeDisque.setNom(rs.getString("maison_de_disque.nom"));
        maisonDeDisque.setAnnee_creation(rs.getString("maison_de_disque.annee_creation"));
        maisonDeDisque.setLieu(rs.getString("maison_de_disque.lieu"));
        maisonDeDisque.setFondateur(rs.getString("maison_de_disque.fondateur"));
        return maisonDeDisque;
    }

    public void save(MaisonDeDisque model) {
        try {
            if (model.getNom() == null || model.getNom().trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }

            if (model.getAnnee_creation() == null) {
                model.setFondateur("");
            }

            if (model.getLieu() == null) {
                model.setLieu("");
            }

            if (model.getFondateur() == null) {
                model.setFondateur("");
            }


            db.executeUpdate(
                    "INSERT INTO maison_de_disque (id, nom, annee_creation, lieu, fondateur) VALUES (?, ?, ?, ?, ?)",
                    model.getId(), model.getNom(), model.getAnnee_creation(), model.getLieu(), model.getFondateur());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the error (e.g., logging, rethrowing, user feedback, etc.)
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // Handle validation errors appropriately
        }

    }


    public void delete(int maisonDeDiqueId) {
        String query = "DELETE FROM maison_de_disque WHERE id = ?";
        db.executeUpdate(query, maisonDeDiqueId);
    }
}
