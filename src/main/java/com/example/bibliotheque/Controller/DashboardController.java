package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Model.Lecteur;
import com.example.bibliotheque.Repository.LecteurRepository;
import com.example.bibliotheque.View.AddLecteurModal;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    Stage stage;
    @FXML
    private Button lecteursBtn, livresBtn, pretsBtn, nouveauLecteur;
    @FXML
    private Tab lecteursOnglet,livresOnglet,pretsOnglet,lecteurDetailsOnglet;
    @FXML
    private TabPane onglets;

    @FXML
    private Label numeroLecteurDetailLabel,nomLecteurDetailLabel,prenomLecteurDetailLabel,naissanceLecteurDetailLabel,adresseLecteurDetailLabel,emailLecteurDetailLabel,telephoneLecteurDetailLabel,pretLecteurDetailLabel;

    @FXML
    private TableView <Lecteur> lecteursTable;

    @FXML
    private TableColumn<Lecteur, Integer> numeroLecteurCol;
    @FXML
    private TableColumn<Lecteur, String> nomLecteurCol;
    @FXML
    private TableColumn<Lecteur, String> prenomLecteurCol;
    @FXML
    private TableColumn<Lecteur, String> adresseLecteurCol;
    @FXML
    private TableColumn<Lecteur, String> emailLecteurCol;
    @FXML
    private TableColumn<Lecteur, String> manageLecteurCol;

    ObservableList<Lecteur> listLecteurs;

    @FXML
    void getLecteursView(ActionEvent event) {
        initColorButton();
        lecteursBtn.setStyle("-fx-background-color: #00BFA6");
        onglets.getSelectionModel().select(lecteursOnglet);
    }

    @FXML
    void getLivresView(ActionEvent event) {
        initColorButton();
        livresBtn.setStyle("-fx-background-color: #00BFA6");
        onglets.getSelectionModel().select(livresOnglet);
    }

    @FXML
    void getPretsView(ActionEvent event) {
        initColorButton();
        pretsBtn.setStyle("-fx-background-color: #00BFA6");
        onglets.getSelectionModel().select(pretsOnglet);
    }
    private void initColorButton(){
        pretsBtn.setStyle("-fx-background-color: transparent");
        livresBtn.setStyle("-fx-background-color: transparent");
        lecteursBtn.setStyle("-fx-background-color: transparent");
    }
    public void initDashboard(){
        lecteursBtn.setStyle("-fx-background-color: #00BFA6");
        onglets.getSelectionModel().select(lecteursOnglet);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void getAddLecteurView(ActionEvent event) throws Exception {
        AddLecteurModal addLecteurModal = new AddLecteurModal();
        addLecteurModal.start(new Stage());
    }

    @FXML
    void actualiserLecteurs(ActionEvent event) {
        actualiserLecteurTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        actualiserLecteurTable();

        numeroLecteurCol.setCellValueFactory(new PropertyValueFactory<Lecteur, Integer>("numero"));
        nomLecteurCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("nom"));
        prenomLecteurCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("prenom"));
        adresseLecteurCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("adresse"));
        emailLecteurCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("email"));

        Callback<TableColumn<Lecteur, String>, TableCell<Lecteur, String>> cellFactory = (TableColumn<Lecteur, String> param) -> {
            //Make cell containing buttons
            final TableCell<Lecteur, String> cell = new TableCell<Lecteur, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        Button btnVoirLecteur = new Button("Details");
                        Button btnSupprimerLecteur = new Button("Supprimer");

                        btnVoirLecteur.setStyle("-fx-cursor: hand;-fx-text-fill: #ffffff ;-fx-background-color: #00BFA6 ");
                        btnSupprimerLecteur.setStyle("-fx-cursor: hand; -fx-text-fill: #fff ; -fx-background-color:  #F50057 ");

                        btnVoirLecteur.setOnMouseClicked((MouseEvent event) -> {
                            Lecteur lecteur = lecteursTable.getSelectionModel().getSelectedItem();
                            initColorButton();
                            lecteursBtn.setStyle("-fx-background-color: #00BFA6");
                            onglets.getSelectionModel().select(lecteurDetailsOnglet);
                            numeroLecteurDetailLabel.setText(String.valueOf(lecteur.getNumero()));
                            nomLecteurDetailLabel.setText(lecteur.getNom());
                            prenomLecteurDetailLabel.setText(lecteur.getPrenom());
                            naissanceLecteurDetailLabel.setText(lecteur.getNaissance().toString());
                            adresseLecteurDetailLabel.setText(lecteur.getAdresse());
                            pretLecteurDetailLabel.setText(String.valueOf(lecteur.getNombrePret()));
                            emailLecteurDetailLabel.setText(lecteur.getEmail());
                            telephoneLecteurDetailLabel.setText(lecteur.getTelephone());
                        });

                        HBox voirBtnContainer = new HBox(btnVoirLecteur,btnSupprimerLecteur);
                        voirBtnContainer.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnVoirLecteur, new Insets(1, 1, 1, 1));
                        HBox.setMargin(btnSupprimerLecteur, new Insets(1, 1, 1, 1));
                        setGraphic(voirBtnContainer);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        manageLecteurCol.setCellFactory(cellFactory);
    }

    private void actualiserLecteurTable() {
        LecteurRepository lecteurRepository = new LecteurRepository();
        listLecteurs = lecteurRepository.getLecteurs();
        lecteursTable.setItems(listLecteurs);
    }
}
