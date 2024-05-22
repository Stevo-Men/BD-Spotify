package com.example.demobd3fx.viewModels;

import com.example.demobd3fx.dao.MaisonDeDisqueDAO;
import com.example.demobd3fx.dao.ProjetDAO;
import com.example.demobd3fx.models.MaisonDeDisque;
import com.example.demobd3fx.models.Projet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProjetViewModel {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty titre = new SimpleStringProperty();
    private StringProperty dateSortie = new SimpleStringProperty();
    private StringProperty artiste = new SimpleStringProperty();
    private StringProperty maisonDeDisque = new SimpleStringProperty();




    public ProjetViewModel() {}

    public ProjetViewModel(Projet projet) {
        setId(projet.getId());
        setTitre(projet.getTitre());
        setDateSortie(getDateSortie());
        setArtiste(projet.getArtiste());
        setMaisonDeDisque(projet.getMaisonDeDisque());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitre() {
        return titre.get();
    }

    public StringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getDateSortie() {
        return dateSortie.get();
    }

    public StringProperty dateSortieProperty() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie.set(dateSortie);
    }

    public String getArtiste() {
        return artiste.get();
    }

    public StringProperty artisteProperty() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste.set(artiste);
    }

    public String getMaisonDeDisque() {
        return maisonDeDisque.get();
    }

    public StringProperty maisonDeDisqueProperty() {
        return maisonDeDisque;
    }

    public void setMaisonDeDisque(String maisonDeDisque) {
        this.maisonDeDisque.set(maisonDeDisque);
    }

    public static ObservableList<ProjetViewModel> findAll() {
        List<Projet> projets = new ProjetDAO().findAll();
        ObservableList<ProjetViewModel> viewModels = FXCollections.observableArrayList();
        projets.forEach(person -> viewModels.add(new ProjetViewModel(person)));
        return viewModels;
    }

    public static ObservableList<ProjetViewModel> findByArtistAlias(String artistAlias) {
        List<Projet> projets = new ProjetDAO().findByArtistAlias(artistAlias);
        ObservableList<ProjetViewModel> viewModels = FXCollections.observableArrayList();
        projets.forEach(projet -> viewModels.add(new ProjetViewModel(projet)));
        return viewModels;
    }




}
