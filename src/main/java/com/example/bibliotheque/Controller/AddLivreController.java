package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Repository.LecteurRepository;
import com.example.bibliotheque.Repository.LivreRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddLivreController {

    @FXML
    private Button enregistrerLivreBtn;

    @FXML
    private TextField autheurField;

    @FXML
    private Button cancelModalLecteur;

    @FXML
    private TextField editionField;

    @FXML
    private Label numeroLabel;

    @FXML
    private TextField titreField;

    @FXML
    private Label titreModalLivre;

    private Boolean isUpdate = false;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) enregistrerLivreBtn.getScene().getWindow();
        stage.close();
        isUpdate = false;
    }

    @FXML
    void enregistrerLivre(ActionEvent event) {
        if(isUpdate){
            if(titreField.getText().isBlank() || autheurField.getText().isBlank() || editionField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Merci de remplir tous les champs");
                alert.show();
            }else{
                LivreRepository livreRepository = new LivreRepository();
                livreRepository.updateLivre(
                        numeroLabel.getText(),
                        titreField.getText(),
                        autheurField.getText(),
                        editionField.getText()
                );
                titreModalLivre.setText("Nouveau Livre");
                isUpdate=false;
                cancel(event);
            }
        }else{
            if(titreField.getText().isBlank() || autheurField.getText().isBlank() || editionField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Merci de remplir tous les champs");
                alert.show();
            }else{
                LivreRepository livreRepository = new LivreRepository();
                livreRepository.addLivre(titreField.getText(), autheurField.getText(),editionField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nouveau livre enregistrer !");
                alert.show();
                cancel(event);
            }
        }
    }

    public void initializeFormulaireForUpdate(int numero, String titre, String autheur, String edition){
        numeroLabel.setText(String.valueOf(numero));
        titreModalLivre.setText("Modification livre Numero "+ numero);
        isUpdate = true;
        titreField.setText(titre);
        autheurField.setText(autheur);
        editionField.setText(edition);
    }

}
