package com.example.demobd3fx.dao;

import com.example.demobd3fx.database.Database;

/**
 * Cette classe abstraite est utilisée pour tout les Controllers
 * De cette façon, on centralise l'initialisation de la BD
 */
public abstract class DAO {

    protected Database db;

    /**
     * Si on utilise le constructeur vide,
     * on surcharge simplement l'autre avec NULL
     */
    public DAO() {
        this(null);
    }

    /**
     * Instancie l'objet "Database" pour le Controller
     * @param db Objet Database prédéfini si existant
     */
    public DAO(Database db) {
        if (db == null) {
            db = Database.buildFromConfig();
        }
        this.db = db;
    }

}
