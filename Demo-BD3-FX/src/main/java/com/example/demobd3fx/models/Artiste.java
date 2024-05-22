package com.example.demobd3fx.models;

import javafx.beans.property.StringProperty;

public class Artiste {
    private int id = 0;
    private String Alias;
    private String reelNom;
    private String nationalite;
    private String debut;
    private String maisonDeDisque;

    public String getMaisonDeDisque() {
        return maisonDeDisque;
    }

    public void setMaisonDeDisque(String maisonDeDisque) {
        this.maisonDeDisque = maisonDeDisque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getReelnom() {
        return reelNom;
    }

    public void setReelnom(String reelnom) {
        this.reelNom = reelnom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }





}
