package com.example.demobd3fx.dao;

import com.example.demobd3fx.database.Database;
import com.example.demobd3fx.models.Editor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EditorDAO extends DAO implements ResultSetHandler<Editor> {

    public EditorDAO() {
        super();
    }

    public EditorDAO(Database db) {
        super(db);
    }

    public Editor findById(int id) {
        return db.selectInstance(
                "SELECT id \"editeur.id\", " +
                        "nom \"editeur.nom\", " +
                        "FROM editeur WHERE id = ?",
                this, id);
    }

    public List<Editor> findAll() {
        return db.selectInstances("SELECT id \"editeur.id\", " +
                "nom \"editeur.nom\", " +
                "FROM editeur", this);
    }

    @Override
    public Editor handle(ResultSet rs) throws SQLException {
        Editor editor = new Editor();
        editor.setId(rs.getInt("editeur.id"));
        editor.setName(rs.getString("editeur.nom"));
        return editor;
    }
}
