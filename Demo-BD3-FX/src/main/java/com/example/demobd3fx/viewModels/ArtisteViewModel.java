package com.example.demobd3fx.viewModels;

import com.example.demobd3fx.dao.ArtisteDAO;
import com.example.demobd3fx.models.Artiste;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.util.List;

public class ArtisteViewModel {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty alias = new SimpleStringProperty();
    private StringProperty reelnom = new SimpleStringProperty();
    private StringProperty nationalite = new SimpleStringProperty();
    private StringProperty debut = new SimpleStringProperty();
    private StringProperty maisonDeDisque = new SimpleStringProperty();


    public ArtisteViewModel() {}
    public ArtisteViewModel(Artiste artiste) {
        setId(artiste.getId());
        setAlias(artiste.getAlias());
        setReelnom(artiste.getReelnom());
        setNationalite(artiste.getNationalite());
        setDebut(artiste.getDebut());
        setMaisonDeDisque(artiste.getMaisonDeDisque());
    }



    public String getAlias() {
        return alias.get();
    }

    public StringProperty aliasProperty() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getReelnom() {
        return reelnom.get();
    }

    public StringProperty reelnomProperty() {
        return reelnom;
    }

    public void setReelnom(String reelnom) {
        this.reelnom.set(reelnom);
    }

    public String getNationalite() {
        return nationalite.get();
    }

    public StringProperty nationaliteProperty() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite.set(nationalite);
    }

    public String getDebut() {
        return debut.get();
    }

    public StringProperty debutProperty() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut.set(debut);
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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public static ObservableList<ArtisteViewModel> findAll() {
        List<Artiste> artistes = new ArtisteDAO().findAll();
        ObservableList<ArtisteViewModel> viewModels = FXCollections.observableArrayList();
        artistes.forEach(person -> viewModels.add(new ArtisteViewModel(person)));
        return viewModels;
    }

    private void updateModel(Artiste model) {
        model.setId(getId());
        model.setAlias(getAlias());
        model.setReelnom(getReelnom());
        model.setNationalite(getNationalite());
        model.setDebut(getDebut());
        model.setMaisonDeDisque(getMaisonDeDisque());
    }

    public void saveChanges() {
        Artiste model = new Artiste();
        updateModel(model);
        new ArtisteDAO().save(model);
    }

    public void delete(int id) {
        new ArtisteDAO().delete(id);
    }
}
