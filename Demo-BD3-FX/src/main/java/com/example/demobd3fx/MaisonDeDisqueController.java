package com.example.demobd3fx;

import com.example.demobd3fx.viewModels.MaisonDeDisqueViewModel;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

public class MaisonDeDisqueController {

    public MaisonDeDisqueViewModel currentMaisonDeDisque = new MaisonDeDisqueViewModel();
    public TableView<MaisonDeDisqueViewModel> tvRecordLabel;
    public TextField txtNomLabel, txtCreation, txtLieu, txtFondateur;
    public Button btnRecordLabels, btnSaveLabels, btnInsertLabel, btnDeleteLabel;

    public void initialize() {
        assignRecordLabelItems();
    }

    public void loadRecordLabels() {
        ObservableList<MaisonDeDisqueViewModel> items = MaisonDeDisqueViewModel.findAll();
        tvRecordLabel.setItems(items);
    }

    public void deleteLabel() {
        MaisonDeDisqueViewModel selectedLabel = tvRecordLabel.getSelectionModel().getSelectedItem();
        if (selectedLabel != null) {
            tvRecordLabel.getItems().remove(selectedLabel);
            currentMaisonDeDisque.delete(selectedLabel.getId());
        }
    }

    public void insertNewLabel() {
        String nomLabel = txtNomLabel.getText();
        String creation = txtCreation.getText();
        String lieu = txtLieu.getText();
        String fondateur = txtFondateur.getText();

        MaisonDeDisqueViewModel newMaisonDeDisque = new MaisonDeDisqueViewModel();
        newMaisonDeDisque.setNom(nomLabel);
        newMaisonDeDisque.setAnnee_creation(creation);
        newMaisonDeDisque.setLieu(lieu);
        newMaisonDeDisque.setFondateur(fondateur);

        newMaisonDeDisque.saveChanges();

        tvRecordLabel.getItems().add(newMaisonDeDisque);

        txtNomLabel.clear();
        txtCreation.clear();
        txtLieu.clear();
        txtFondateur.clear();
    }



    private void assignRecordLabelItems() {
        //Establish the Columns for the TableView... or do it in the FXML
        TableColumn<MaisonDeDisqueViewModel, String> nomCol = new TableColumn<>("Nom");
        TableColumn<MaisonDeDisqueViewModel, String> annee_de_creationCol = new TableColumn<>("Année de création");
        TableColumn<MaisonDeDisqueViewModel, String> lieuCol = new TableColumn<>("Lieu");
        TableColumn<MaisonDeDisqueViewModel, String> fondateurCol = new TableColumn<>("Fondateur");
        //TableColumn<JoueurViewModel, String> lastNameCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        annee_de_creationCol.setCellValueFactory(cellData -> cellData.getValue().annee_creationProperty());
        lieuCol.setCellValueFactory(cellData -> cellData.getValue().lieuProperty());
        fondateurCol.setCellValueFactory(cellData -> cellData.getValue().fondateurProperty());


        //Si on veut dodge le warning, mais on s'en fou un peu...
        tvRecordLabel.getColumns().add(nomCol);
        tvRecordLabel.getColumns().add(annee_de_creationCol);
        tvRecordLabel.getColumns().add(lieuCol);
        tvRecordLabel.getColumns().add(fondateurCol);
        //tvMain.getColumns().setAll(firstNameCol, lastNameCol);

        //EventHandler<MouseEvent> eventHandler = mouseEvent -> System.out.println("Clicked");
        //btnLoadArtists.setOnAction(actionEvent -> System.out.println("Clicked"));
        //btnLoadArtists.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> System.out.println("Clicked"));

        tvRecordLabel.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                currentMaisonDeDisque = tvRecordLabel.getSelectionModel().getSelectedItem();
                txtNomLabel.textProperty().bindBidirectional(currentMaisonDeDisque.nomProperty());
                currentMaisonDeDisque.nomProperty().addListener((observableValue, o, n) -> {
                    System.out.printf("Changed first name from %s to %s\n", o, n);
                });
            }
        });
    }
}
