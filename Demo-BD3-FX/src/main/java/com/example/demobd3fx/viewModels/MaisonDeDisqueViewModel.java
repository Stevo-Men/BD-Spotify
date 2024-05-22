package com.example.demobd3fx.viewModels;

import com.example.demobd3fx.dao.ArtisteDAO;
import com.example.demobd3fx.dao.MaisonDeDisqueDAO;
import com.example.demobd3fx.models.Artiste;
import com.example.demobd3fx.models.MaisonDeDisque;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MaisonDeDisqueViewModel {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nom = new SimpleStringProperty();
    private StringProperty annee_creation = new SimpleStringProperty();
    private StringProperty lieu = new SimpleStringProperty();
    private StringProperty fondateur = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getAnnee_creation() {
        return annee_creation.get();
    }

    public StringProperty annee_creationProperty() {
        return annee_creation;
    }

    public void setAnnee_creation(String annee_creation) {
        this.annee_creation.set(annee_creation);
    }

    public String getLieu() {
        return lieu.get();
    }

    public StringProperty lieuProperty() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu.set(lieu);
    }

    public String getFondateur() {
        return fondateur.get();
    }

    public StringProperty fondateurProperty() {
        return fondateur;
    }

    public void setFondateur(String fondateur) {
        this.fondateur.set(fondateur);
    }

    public MaisonDeDisqueViewModel() {}
    public MaisonDeDisqueViewModel(MaisonDeDisque maisonDeDisque) {
        setId(maisonDeDisque.getId());
        setNom(maisonDeDisque.getNom());
        setAnnee_creation(maisonDeDisque.getAnnee_creation());
        setLieu(maisonDeDisque.getLieu());
        setFondateur(maisonDeDisque.getFondateur());

    }
    public static ObservableList<MaisonDeDisqueViewModel> findAll() {
        List<MaisonDeDisque> maisonDeDisques = new MaisonDeDisqueDAO().findAll();
        ObservableList<MaisonDeDisqueViewModel> viewModels = FXCollections.observableArrayList();
        maisonDeDisques.forEach(person -> viewModels.add(new MaisonDeDisqueViewModel(person)));
        return viewModels;
    }

    private void updateModel(MaisonDeDisque model) {
        model.setId(getId());
        model.setNom(getNom());
        model.setAnnee_creation(getAnnee_creation());
        model.setLieu(getLieu());
        model.setFondateur(getFondateur());
    }

    public void saveChanges() {
        MaisonDeDisque model = new MaisonDeDisque();
        updateModel(model);
        new MaisonDeDisqueDAO().save(model);
    }

    public void delete(int id) {
        new MaisonDeDisqueDAO().delete(id);
    }
}
