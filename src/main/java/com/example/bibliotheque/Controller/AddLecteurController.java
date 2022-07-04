package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Repository.LecteurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

public class AddLecteurController {

    private Boolean isUpdate = false;
    @FXML
    private TextField adresseField;

    @FXML
    private Button ajouterLecteurBtn;

    @FXML
    private Button cancelModalLecteur;

    @FXML
    private TextField emailField;

    @FXML
    private Label numeroLabel,titreModalLecteur;

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
        isUpdate = false;
    }

    @FXML
    void enregistrerLecteur(ActionEvent event) {
        if(isUpdate == true){
            if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() ||
                    emailField.getText().isBlank() || telephoneField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Merci de remplir le formulaire correctement !!");
                alert.show();
            }else{
                LecteurRepository lecteurRepository = new LecteurRepository();
                lecteurRepository.updateLecteur(
                        numeroLabel.getText(),
                        nomField.getText(),
                        prenomField.getText(),
                        String.valueOf(naissanceField.getValue()),
                        adresseField.getText(),
                        emailField.getText(),
                        telephoneField.getText()
                );
            titreModalLecteur.setText("Nouveau Membre");
            isUpdate=false;
            Stage stage = (Stage) ajouterLecteurBtn.getScene().getWindow();
            stage.close();
            }
        }else{
            if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() ||
                    emailField.getText().isBlank() || telephoneField.getText().isBlank()){
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

    public void initializeFormulaireForUpdate(int numero, String nom, String prenom, LocalDate naissance, String adresse, String email, String telephone) {
        numeroLabel.setText(String.valueOf(numero));
        titreModalLecteur.setText("Modification lecteur Numero "+ numero);
        isUpdate = true;

        nomField.setText(nom);
        prenomField.setText(prenom);
        naissanceField.setValue(naissance);
        adresseField.setText(adresse);
        emailField.setText(email);
        telephoneField.setText(telephone);
    }
}