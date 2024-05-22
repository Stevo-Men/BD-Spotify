package com.example.demobd3fx.dao;

import com.example.demobd3fx.models.Artiste;
import com.example.demobd3fx.models.Joueur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ArtisteDAO extends DAO implements ResultSetHandler<Artiste>{


    @Override
    public Artiste findById(int id) {

        return db.selectInstance(
                "SELECT id \"artiste.id\", " +
                        "alias \"artiste.alias\", " +
                        "reel_nom \"artiste.reel_nom\", " +
                        "nationalite \"artiste.nationalite\", " +
                        "debut \"artiste.debut\", " +
                        "maison_de_disque \"artiste.maison_de_disque\" " +
                        "FROM artiste WHERE id = ?",
                this, id);
    }

    public Artiste findNext(int previous) {
        return db.selectInstance(
                "SELECT id \"artiste.id\", " +
                        "alias \"artiste.alias\", " +
                        "reel_nom \"artiste.reel_nom\", " +
                        "nationalite \"artiste.nationalite\", " +
                        "debut \"artiste.debut\", " +
                        "maison_de_disque \"artiste.maison_de_disque\" " +
                        "FROM artiste WHERE id > ? ORDER BY id LIMIT 1",
                this, previous);
    }

    public List<Artiste> findAll() {
        return db.selectInstances("SELECT id \"artiste.id\", " +
                "alias \"artiste.alias\", " +
                "reel_nom \"artiste.reel_nom\", " +
                "nationalite \"artiste.nationalite\", " +
                "debut \"artiste.debut\", " +
                "maison_de_disque \"artiste.maison_de_disque\" " +
                "FROM artiste", this);
    }

    @Override
    public Artiste handle(ResultSet rs) throws SQLException {
        Artiste artiste = new Artiste();
        artiste.setId(rs.getInt("artiste.id"));
        artiste.setAlias(rs.getString("artiste.alias"));
        artiste.setReelnom(rs.getString("artiste.reel_nom"));
        artiste.setNationalite(rs.getString("artiste.nationalite"));
        artiste.setDebut(rs.getString("artiste.debut"));
        artiste.setMaisonDeDisque(rs.getString("artiste.maison_de_disque"));
        return artiste;
    }

    public void save(Artiste model) {
        try {
            // Ensure that alias and other required fields are not null
            if (model.getAlias() == null || model.getAlias().trim().isEmpty()) {
                throw new IllegalArgumentException("Alias cannot be null or empty");
            }

            if (model.getReelnom() == null) {
                model.setReelnom("");
            }
            if (model.getNationalite() == null) {
                model.setNationalite("");
            }
            if (model.getDebut() == null) {
                model.setDebut("");
            }

            // Convert maison_de_disque to integer, if it's not null
            Integer maisonDeDisqueId = null;
            if (model.getMaisonDeDisque() != null) {
                maisonDeDisqueId = Integer.parseInt(model.getMaisonDeDisque());
            }

            int newId = getNextAvailableId();
            model.setId(newId);

            db.executeUpdate(
                    "INSERT INTO artiste (id, alias, reel_nom, nationalite, debut, maison_de_disque) VALUES (?, ?, ?, ?, ?, ?)",
                    model.getId(), model.getAlias(), model.getReelnom(), model.getNationalite(), model.getDebut(), maisonDeDisqueId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the error (e.g., logging, rethrowing, user feedback, etc.)
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // Handle validation errors appropriately
        }
    }

    private int getNextAvailableId() {
        try {
            // Query the maximum ID currently in the table
            ResultSet rs = db.executeQuery("SELECT MAX(id) FROM artiste");
            if (rs.next()) {
                int maxId = rs.getInt(1);
                // Return the next ID (maximum ID + 1)
                return maxId + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., logging, rethrowing, user feedback, etc.)
        }
        // Return a default value if there's an error
        return 0;
    }

    public void delete(int artistId) {
        String query = "DELETE FROM artiste WHERE id = ?";
        db.executeUpdate(query, artistId);
    }




}
