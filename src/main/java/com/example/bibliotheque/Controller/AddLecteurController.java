package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Repository.LecteurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddLecteurController {

    @FXML
    private TextField adresseField;

    @FXML
    private Button ajouterLecteurBtn;

    @FXML
    private Button cancelModalLecteur;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker naissanceField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField telephoneField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ajouterLecteurBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enregistrerLecteur(ActionEvent event) {
        if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() || emailField.getText().isBlank() || telephoneField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Merci de remplir tous les champs du formulaire !!");
            alert.show();
        }else{
            LecteurRepository lecteurRepository = new LecteurRepository();
            lecteurRepository.addLecteur(
                    nomField.getText(),
                    prenomField.getText(),
                    String.valueOf(naissanceField.getValue()),
                    adresseField.getText(),
                    emailField.getText(),
                    telephoneField.getText()
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Nouveau membre enregistrer");
            alert.show();
            Stage stage = (Stage) ajouterLecteurBtn.getScene().getWindow();
            stage.close();
        }
    }

}