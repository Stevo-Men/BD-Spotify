package com.example.demobd3fx.models;

import java.util.Date;

public class Projet {

    private int id = 0;
    private String titre;

    private String dateSortie;
    private String artiste;
    private String maisonDeDisque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getMaisonDeDisque() {
        return maisonDeDisque;
    }

    public void setMaisonDeDisque(String maisonDeDisque) {
        this.maisonDeDisque = maisonDeDisque;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }
}
