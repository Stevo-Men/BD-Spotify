package com.example.demobd3fx;

import com.example.demobd3fx.dao.ArtisteDAO;
import com.example.demobd3fx.dao.JoueurDAO;
import com.example.demobd3fx.dao.MaisonDeDisqueDAO;
import com.example.demobd3fx.models.Artiste;
import com.example.demobd3fx.models.Joueur;
import com.example.demobd3fx.models.MaisonDeDisque;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1140, 760);
        stage.setTitle("Spotiphy");
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(HelloApplication.class.
                getResourceAsStream("images/spotify-logo.png")));
        stage.getIcons().add(icon);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        ArtisteDAO artisteBroker = new ArtisteDAO();
        Artiste artiste = artisteBroker.findNext(0);
        List<Artiste> artistes = artisteBroker.findAll();

        MaisonDeDisqueDAO maisonBroker = new MaisonDeDisqueDAO();
        MaisonDeDisque maisonDeDisque = maisonBroker.findNext(0);
        List<MaisonDeDisque> maisonDeDisques = maisonBroker.findAll();


        //launch JavaFX
        launch();
    }

}