package com.example.demobd3fx.viewModels;

import com.example.demobd3fx.models.Joueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.demobd3fx.dao.JoueurDAO;

import java.util.ArrayList;
import java.util.List;

public class JoueurViewModel {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();

    private StringProperty fullName = new SimpleStringProperty();


    //Empty constructor to allow initialization
    public JoueurViewModel() {}
    public JoueurViewModel(Joueur joueur) {
        setId(joueur.getId());
        setFirstName(joueur.getFirstname());
        setLastName(joueur.getLastname());
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

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }


    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public static ObservableList<JoueurViewModel> findAll() {
        List<Joueur> joueurs = new JoueurDAO().findAll();
        ObservableList<JoueurViewModel> viewModels = FXCollections.observableArrayList();
        joueurs.forEach(person -> viewModels.add(new JoueurViewModel(person)));
        return viewModels;
    }

    private void updateModel(Joueur model) {
        model.setId(getId());
        model.setFirstname(getFirstName());
        model.setLastname(getLastName());
    }

    public void saveChanges() {
        Joueur model = new Joueur();
        updateModel(model);
        new JoueurDAO().save(model);
    }
}
