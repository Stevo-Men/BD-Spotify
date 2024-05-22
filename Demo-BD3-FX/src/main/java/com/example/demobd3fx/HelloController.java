package com.example.demobd3fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Tab tabArtist;

    @FXML
    private Tab tabRecordLabel;

    private MaisonDeDisqueController maisonDeDisqueController;
    private ArtistController artistController;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeMaisonDeDisqueController();
            initializeArtistController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void initializeArtistController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demobd3fx/artists-view.fxml"));
        AnchorPane artistLabelPane = loader.load();
        artistController = loader.getController();

        tabArtist.setContent(artistLabelPane);
        artistController.initialize();

        artistController.btnLoadArtists.setOnAction(event -> artistController.loadArtists());
        artistController.btnInsertArtist.setOnAction(event -> artistController.insertNewArtist());
        artistController.btnDeleteArtist.setOnAction(event -> artistController.deleteArtist());
        artistController.btnSaveArtist.setOnAction(event -> artistController.saveChanges());
    }

    private void initializeMaisonDeDisqueController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demobd3fx/record-label-view.fxml"));
        AnchorPane recordLabelPane = loader.load();
        maisonDeDisqueController = loader.getController();

        tabRecordLabel.setContent(recordLabelPane);
        maisonDeDisqueController.initialize();

        maisonDeDisqueController.btnRecordLabels.setOnAction(event -> maisonDeDisqueController.loadRecordLabels());
        maisonDeDisqueController.btnInsertLabel.setOnAction(event -> maisonDeDisqueController.insertNewLabel());
        maisonDeDisqueController.btnDeleteLabel.setOnAction(event -> maisonDeDisqueController.deleteLabel());
    }





}