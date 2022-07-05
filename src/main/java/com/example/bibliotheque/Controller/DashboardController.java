package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Application;
import com.example.bibliotheque.Model.Lecteur;
import com.example.bibliotheque.Model.Livre;
import com.example.bibliotheque.Model.Pret;
import com.example.bibliotheque.Repository.LecteurRepository;
import com.example.bibliotheque.Repository.LivreRepository;
import com.example.bibliotheque.Repository.PretRepository;
import com.example.bibliotheque.View.AddLecteurModal;
import com.example.bibliotheque.View.AddLivreModal;
import com.example.bibliotheque.View.AddPretModal;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    Stage stage;
    @FXML
    private Button lecteursBtn, livresBtn, pretsBtn, nouveauLecteur;
    @FXML
    private Tab lecteursOnglet,livresOnglet,pretsOnglet,lecteurDetailsOnglet,livreDetailsOnglet;
    @FXML
    private TabPane onglets;

    @FXML
    private Label numeroLecteurDetailLabel,nomLecteurDetailLabel,prenomLecteurDetailLabel,naissanceLecteurDetailLabel,
            adresseLecteurDetailLabel,emailLecteurDetailLabel,telephoneLecteurDetailLabel,pretLecteurDetailLabel,
            numeroLivreDetailLabel,titreLivreDetailLabel,autheurLivreDetailLabel,editionLivreDetailLabel, disponibleLivreDetailLabel,
            nbPretLivreDetailLabel;

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
    private TableView <Livre> livresTable;
    @FXML
    private TableColumn<Livre, Integer> numeroLivreCol;
    @FXML
    private TableColumn<Livre, String> titreLivreCol;
    @FXML
    private TableColumn<Livre, String> autheurLivreCol;
    @FXML
    private TableColumn<Livre, String> editionLivreCol;
    @FXML
    private TableColumn<Livre, String> disponibleLivreCol;
    @FXML
    private TableColumn<Livre, String> nbPretLivreCol;
    @FXML
    private TableColumn<Livre, String> manageLivreCol;
    ObservableList<Livre> listLivres;

    @FXML
    private TableView<Pret> pretsTable;
    @FXML
    private TableColumn numeroPretCol;
    @FXML
    private TableColumn designationPretCol;
    @FXML
    private TableColumn autheurPretCol;
    @FXML
    private TableColumn lecteurPretCol;
    @FXML
    private TableColumn datePretCol;
    @FXML
    private TableColumn dateRetourCol;
    @FXML
    private TableColumn statusPretCol;
    @FXML
    private TableColumn actionPretCol;
    ObservableList listPrets;


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
    void getAddLivreView(ActionEvent event) throws Exception {
        AddLivreModal addLivreModal = new AddLivreModal();
        addLivreModal.start(new Stage());
    }

    @FXML
    void getAddPretView(ActionEvent event) throws Exception {
        AddPretModal addPretModal = new AddPretModal();
        addPretModal.start(new Stage());
    }

    @FXML
    void getUpdateLecteurView(ActionEvent event) throws IOException, SQLException {
        //Afficher le modal && initialiser les champs
        AddLecteurModal addLecteurModal = new AddLecteurModal();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("addLecteur.fxml"));
        loader.load();
        AddLecteurController addLecteurController = loader.getController();

        LecteurRepository lecteurRepository = new LecteurRepository();
        Lecteur lecteur = lecteurRepository.getLecteurByNumero(numeroLecteurDetailLabel.getText());

        addLecteurController.initializeFormulaireForUpdate(
                lecteur.getNumero(),
                lecteur.getNom(),
                lecteur.getPrenom(),
                lecteur.getNaissance().toLocalDate(),
                lecteur.getAdresse(),
                lecteur.getEmail(),
                lecteur.getTelephone()
        );

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    @FXML
    void modifierLivre(ActionEvent event) throws IOException, SQLException {
        //Afficher le modal && initialiser les champs
        AddLivreModal addLivreModal = new AddLivreModal();

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("addLivre.fxml"));
        loader.load();
        AddLivreController addLivreController = loader.getController();

        LivreRepository livreRepository = new LivreRepository();

        Livre livre = livreRepository.getLivreByNumero(numeroLivreDetailLabel.getText());

        addLivreController.initializeFormulaireForUpdate(
                livre.getNumero(),
                livre.getTitre(),
                livre.getAutheur(),
                livre.getEdition()

        );

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void actualiserLecteurs(ActionEvent event) {
        actualiserLecteurTable();
    }
    @FXML
    void actualiserInfoLecteur(ActionEvent event) throws SQLException {
        LecteurRepository lecteurRepository = new LecteurRepository();
        Lecteur lecteur = lecteurRepository.getLecteurByNumero(numeroLecteurDetailLabel.getText());
        nomLecteurDetailLabel.setText(lecteur.getNom());
        prenomLecteurDetailLabel.setText(lecteur.getPrenom());
        naissanceLecteurDetailLabel.setText(lecteur.getNaissance().toString());
        adresseLecteurDetailLabel.setText(lecteur.getAdresse());
        emailLecteurDetailLabel.setText(lecteur.getEmail());
        telephoneLecteurDetailLabel.setText(lecteur.getTelephone());
        pretLecteurDetailLabel.setText(String.valueOf(lecteur.getNombrePret()));
        System.out.println(lecteur.getNombrePret());
    }
    @FXML
    void actualiserInfoLivre(ActionEvent event) throws SQLException {
        LivreRepository livreRepository = new LivreRepository();
        Livre livre = livreRepository.getLivreByNumero(numeroLivreDetailLabel.getText());
        titreLivreDetailLabel.setText(livre.getTitre());
        autheurLivreDetailLabel.setText(livre.getAutheur());
        editionLivreDetailLabel.setText(livre.getEdition());
        disponibleLivreDetailLabel.setText(livre.getFormattedDisponible());
        nbPretLivreDetailLabel.setText(String.valueOf(livre.getNbPret()));
    }

    @FXML
    void actualiserLivre(ActionEvent event) {
        actualiserLivreTable();
    }
    @FXML
    void actualiserPretTable(ActionEvent event) {
        actualiserPretTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiserLecteurTable();
        initialiserLivreTable();
        initialiserPretTable();
    }

    private void initialiserPretTable() {
        actualiserPretTable();

        numeroPretCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        designationPretCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        autheurPretCol.setCellValueFactory(new PropertyValueFactory<>("autheur"));
        lecteurPretCol.setCellValueFactory(new PropertyValueFactory<>("lecteur"));
        datePretCol.setCellValueFactory(new PropertyValueFactory<>("datePret"));
        dateRetourCol.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
        statusPretCol.setCellValueFactory(new PropertyValueFactory<>("rendu"));

        Callback<TableColumn<Pret, String>,TableCell<Pret, String>> cellFactory = (TableColumn<Pret,String> param)->{
            final TableCell<Pret, String> cell = new TableCell<Pret,String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item,empty);
                    
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        Button btnRendre = new Button("rendre");

                        btnRendre.setStyle("-fx-cursor: hand;-fx-text-fill: #ffffff ;-fx-background-color: #00BFA6 ");

                        btnRendre.setOnMouseClicked((MouseEvent event)->{
                            Pret pret = pretsTable.getSelectionModel().getSelectedItem();
                            LocalDate dateDuJour = LocalDate.now();
                            LocalDate retourPrevu = LocalDate.parse(pret.getDateRetour(), DateTimeFormatter.ISO_LOCAL_DATE);
                            Duration diff = Duration.between(retourPrevu.atStartOfDay(),dateDuJour.atStartOfDay());
                            long diffDays = diff.toDays();
                            if(diffDays>0){
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Livre rendu en retard, le lecteur doit payer une amende de 5000 Ariary !");
                                alert.show();
                            }else{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Livre rendu");
                                alert.show();
                            }
                            PretRepository pretRepository = new PretRepository();
                            pretRepository.rendre(pret.getNumero(),pret.getNumeroLecteur(),pret.getNumeroLivre());
                        });

                        HBox container = new HBox(btnRendre);
                        container.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnRendre, new Insets(1, 1, 1, 1));
                        setGraphic(container);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        actionPretCol.setCellFactory(cellFactory);
    }

    private void actualiserPretTable() {
        PretRepository pretRepository = new PretRepository();
        listPrets = pretRepository.getPrets();
        pretsTable.setItems(listPrets);
    }

    private void initialiserLecteurTable(){
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

                        btnSupprimerLecteur.setOnMouseClicked((MouseEvent event) ->{
                            Lecteur lecteur = lecteursTable.getSelectionModel().getSelectedItem();
                            LecteurRepository lecteurRepository = new LecteurRepository();
                            lecteurRepository.delete(lecteur);
                            actualiserLecteurTable();
                        });

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

    private void initialiserLivreTable(){
        actualiserLivreTable();
        numeroLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("numero"));
        titreLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        autheurLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("autheur"));
        editionLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("edition"));
        disponibleLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("disponible"));
        nbPretLivreCol.setCellValueFactory(new PropertyValueFactory<Livre, String>("nbPret"));

        Callback<TableColumn<Livre, String>, TableCell<Livre, String>> cellFactory = (TableColumn<Livre, String> param) -> {

            final TableCell<Livre, String> cell = new TableCell<Livre, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        Button btnVoirLivre = new Button("Details");
                        Button btnSupprimerLivre = new Button("Supprimer");

                        btnVoirLivre.setStyle("-fx-cursor: hand;-fx-text-fill: #ffffff ;-fx-background-color: #00BFA6 ");

                        btnSupprimerLivre.setOnMouseClicked((MouseEvent event) ->{
                            Livre livre = livresTable.getSelectionModel().getSelectedItem();
                            LivreRepository livreRepository = new LivreRepository();
                            livreRepository.delete(livre);
                            actualiserLivreTable();
                        });

                        btnVoirLivre.setOnMouseClicked((MouseEvent event) -> {
                            Livre livre = livresTable.getSelectionModel().getSelectedItem();
                            initColorButton();
                            livresBtn.setStyle("-fx-background-color: #00BFA6");
                            onglets.getSelectionModel().select(livreDetailsOnglet);
                            numeroLivreDetailLabel.setText(String.valueOf(livre.getNumero()));
                            titreLivreDetailLabel.setText(livre.getTitre());
                            autheurLivreDetailLabel.setText(livre.getAutheur());
                            disponibleLivreDetailLabel.setText(livre.getFormattedDisponible());
                            nbPretLivreDetailLabel.setText(String.valueOf(livre.getNbPret()));
                        });
                        HBox voirBtnContainer = new HBox(btnVoirLivre,btnSupprimerLivre);
                        voirBtnContainer.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnVoirLivre, new Insets(1, 1, 1, 1));
                        HBox.setMargin(btnSupprimerLivre, new Insets(1, 1, 1, 1));
                        setGraphic(voirBtnContainer);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        manageLivreCol.setCellFactory(cellFactory);
    }

    private void actualiserLivreTable() {
        LivreRepository livreRepository = new LivreRepository();
        listLivres = livreRepository.getLivres();
        livresTable.setItems(listLivres);
    }

    private void actualiserLecteurTable() {
        LecteurRepository lecteurRepository = new LecteurRepository();
        listLecteurs = lecteurRepository.getLecteurs();
        lecteursTable.setItems(listLecteurs);
    }
}
