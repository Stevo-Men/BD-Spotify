package com.example.demobd3fx.dao;

import com.example.demobd3fx.database.Database;
import com.example.demobd3fx.models.Editor;
import com.example.demobd3fx.models.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GameDAO extends DAO implements ResultSetHandler<Game> {

    public GameDAO() {
        super();
    }

    public GameDAO(Database db) {
        super(db);
    }

    public Game findById(int id) {
        throw new UnsupportedOperationException();
    }

    public List<Game> findByEditor(Editor editor) {
        return db.selectInstances("SELECT j.id \"jeu.id\", j.nom \"jeu.nom\", j.minimum \"jeu.minimum\", j.maximum \"jeu.maximum\", " +
                " j.complexite \"jeu.complexite\", j.coop \"jeu.coop\", j.sortie \"jeu.sortie\", " +
                "e.id \"editeur.id\", e.nom \"editeur.nom\"  " +
                "FROM jeux.jeu j JOIN jeux.editeur e ON e.id = j.editeur_id WHERE j.editeur_id = ? ORDER BY j.id",
                this, editor.getId());
    }
    public List<Game> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game handle(ResultSet rs) throws SQLException {
        Game game = new Game();
        game.setId(rs.getInt("jeu.id"));
        game.setName(rs.getString("jeu.nom"));
        game.setMinimum(rs.getInt("jeu.minimum"));
        game.setMaximum(rs.getInt("jeu.maximum"));
        game.setComplexity(rs.getInt("jeu.complexite"));
        game.setCoop(rs.getBoolean("jeu.coop"));
        game.setRelease(rs.getDate("jeu.sortie"));

        game.setEditor(new EditorDAO(db).handle(rs));
        return game;
    }
}
