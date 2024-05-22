package com.example.demobd3fx.models;

public class MaisonDeDisque {

    private int id = 0;
    private String nom;
    private String annee_creation;
    private String lieu;
    private String fondateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnnee_creation() {
        return annee_creation;
    }

    public void setAnnee_creation(String annee_creation) {
        this.annee_creation = annee_creation;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getFondateur() {
        return fondateur;
    }

    public void setFondateur(String fondateur) {
        this.fondateur = fondateur;
    }

}
