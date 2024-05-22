package com.example.demobd3fx;

import com.example.demobd3fx.dao.ArtisteDAO;
import com.example.demobd3fx.dao.ProjetDAO;
import com.example.demobd3fx.models.Projet;
import com.example.demobd3fx.viewModels.ArtisteViewModel;
import com.example.demobd3fx.viewModels.ProjetViewModel;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

public class ArtistController {

    public ArtisteViewModel currentArtiste = new ArtisteViewModel();
    public TableView<ArtisteViewModel> tvArtist;
    public TableView<ProjetViewModel> tvProjet;
    public TextField txtAlias,txtReelNom,txtNationalite,txtDebut;
    public TextField txtMaisonDeDisque;
    public Button btnLoadArtists,btnSaveArtist,btnInsertArtist,btnDeleteArtist;

    public void initialize() {
        assignArtistItems();
        assignProjetInfo();
    }

    public void loadArtists() {
        ObservableList<ArtisteViewModel> items = ArtisteViewModel.findAll();
        ObservableList<ProjetViewModel> projetItems = ProjetViewModel.findAll();
        tvArtist.setItems(items);
        tvProjet.setItems(projetItems);

    }

    public void deleteArtist() {
        ArtisteViewModel selectedArtist = tvArtist.getSelectionModel().getSelectedItem();
        if (selectedArtist != null) {
            tvArtist.getItems().remove(selectedArtist);
            currentArtiste.delete(selectedArtist.getId());
        }
    }

    public void insertNewArtist() {
            String alias = txtAlias.getText();
            String reelNom = txtReelNom.getText();
            String nationalite = txtNationalite.getText();
            String debut = txtDebut.getText();
            String maisonDeDisque = txtMaisonDeDisque.getText();

            ArtisteViewModel newArtist = new ArtisteViewModel();
            newArtist.setAlias(alias);
            newArtist.setReelnom(reelNom);
            newArtist.setNationalite(nationalite);
            newArtist.setDebut(debut);
            newArtist.setMaisonDeDisque(maisonDeDisque);

            newArtist.saveChanges();


            // Print out the values of newArtist
            System.out.println("New Artist:");
            System.out.println("Alias: " + newArtist.getAlias());
            System.out.println("Reel Nom: " + newArtist.getReelnom());
            System.out.println("Nationalite: " + newArtist.getNationalite());
            System.out.println("Debut: " + newArtist.getDebut());
            System.out.println("Maison de Disque: " + newArtist.getMaisonDeDisque());

            tvArtist.getItems().add(newArtist);

            txtAlias.clear();
            txtReelNom.clear();
            txtNationalite.clear();
            txtDebut.clear();
            txtMaisonDeDisque.clear();


        }

        public void saveChanges() {
           // currentArtiste.saveChanges();
        }



    private void assignArtistItems() {
        TableColumn<ArtisteViewModel, String> aliasCol = new TableColumn<>("Alias");
        TableColumn<ArtisteViewModel, String> reel_nomCol = new TableColumn<>("Réel Nom");
        TableColumn<ArtisteViewModel, String> nationaliteCol = new TableColumn<>("Nationalité");
        TableColumn<ArtisteViewModel, String> debutCol = new TableColumn<>("Début");
        TableColumn<ArtisteViewModel, String> maison_de_disqueCol = new TableColumn<>("Maison de Disque");

        aliasCol.setCellValueFactory(cellData -> cellData.getValue().aliasProperty());
        reel_nomCol.setCellValueFactory(cellData -> cellData.getValue().reelnomProperty());
        nationaliteCol.setCellValueFactory(cellData -> cellData.getValue().nationaliteProperty());
        debutCol.setCellValueFactory(cellData -> cellData.getValue().debutProperty());
        maison_de_disqueCol.setCellValueFactory(cellData -> cellData.getValue().maisonDeDisqueProperty());


        tvArtist.getColumns().add(aliasCol);
        tvArtist.getColumns().add(reel_nomCol);
        tvArtist.getColumns().add(nationaliteCol);
        tvArtist.getColumns().add(debutCol);
        tvArtist.getColumns().add(maison_de_disqueCol);


        tvArtist.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                ObservableList<ProjetViewModel> projects = ProjetViewModel.findByArtistAlias(currentArtiste.getAlias());
                tvProjet.setItems(projects);
                }
            });
    }

    private void assignProjetInfo() {
        TableColumn<ProjetViewModel, String> titreCol = new TableColumn<>("Titre");
        TableColumn<ProjetViewModel, String> date_SortieCol = new TableColumn<>("Date de Sortie");
        TableColumn<ProjetViewModel, String> artisteCol = new TableColumn<>("Artiste");
        TableColumn<ProjetViewModel, String> maison_de_disqueCol = new TableColumn<>("Maison de Disque");

        titreCol.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        date_SortieCol.setCellValueFactory(cellData -> cellData.getValue().dateSortieProperty());
        artisteCol.setCellValueFactory(cellData -> cellData.getValue().artisteProperty());
        maison_de_disqueCol.setCellValueFactory(cellData -> cellData.getValue().maisonDeDisqueProperty());


        tvProjet.getColumns().add(titreCol);
        tvProjet.getColumns().add(date_SortieCol);
        tvProjet.getColumns().add(artisteCol);
        tvProjet.getColumns().add(maison_de_disqueCol);

        System.out.printf("Projet: " + tvProjet.getColumns());
    }


}
