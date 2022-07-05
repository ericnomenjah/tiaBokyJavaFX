package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Repository.LecteurRepository;
import com.example.bibliotheque.Repository.LivreRepository;
import com.example.bibliotheque.Repository.PretRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddPretController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker datePretField;
    @FXML
    private Button enregistrerPretBtn;
    @FXML
    private ChoiceBox<String> lecteurNomAndNumeroField;
    @FXML
    private ChoiceBox<String> livrePretTitreAndNumeroField;
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) enregistrerPretBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void savePret(ActionEvent event){
        String lecteurNumero = lecteurNomAndNumeroField.getValue().split(" ",2)[0];
        String livreNumero = livrePretTitreAndNumeroField.getValue().split(" ",2)[0];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();

        try{
            calendar.setTime(simpleDateFormat.parse(String.valueOf(datePretField.getValue())));
        }catch (ParseException e){
            e.printStackTrace();
            e.getCause();
        }

        calendar.add(Calendar.DAY_OF_MONTH,15);
        String dateRetour = simpleDateFormat.format(calendar.getTime());

        System.out.println("lecteur :" +lecteurNumero + " livre:"+livreNumero+ " datepret: " + datePretField.getValue() + " dateRetour : " + dateRetour);

        PretRepository pretRepository = new PretRepository();
        pretRepository.add(lecteurNumero,livreNumero,String.valueOf(datePretField.getValue()),dateRetour);
        cancel(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LecteurRepository lecteurRepository = new LecteurRepository();
        ObservableList<String> lecteurs = lecteurRepository.getLecteursElligible();
        lecteurNomAndNumeroField.setItems(lecteurs);

        LivreRepository livreRepository = new LivreRepository();
        ObservableList<String> livres = livreRepository.getLivresDisponible();
        livrePretTitreAndNumeroField.setItems(livres);
    }
}
