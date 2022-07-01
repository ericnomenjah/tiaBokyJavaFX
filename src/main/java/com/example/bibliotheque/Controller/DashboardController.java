package com.example.bibliotheque.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class DashboardController {
    Stage stage;
    @FXML
    private Button lecteursBtn, livresBtn, pretsBtn;
    @FXML
    private Tab lecteursOnglet,livresOnglet,pretsOnglet;
    @FXML
    private TabPane onglets;

    @FXML
    void getLecteursView(ActionEvent event) {
        initColorButton();
        lecteursBtn.setStyle("-fx-background-color: #00BFA6");
    }

    @FXML
    void getLivresView(ActionEvent event) {
        initColorButton();
        livresBtn.setStyle("-fx-background-color: #00BFA6");
    }

    @FXML
    void getPretsView(ActionEvent event) {
        initColorButton();
        pretsBtn.setStyle("-fx-background-color: #00BFA6");
    }
    private void initColorButton(){
        pretsBtn.setStyle("-fx-background-color: transparent");
        livresBtn.setStyle("-fx-background-color: transparent");
        lecteursBtn.setStyle("-fx-background-color: transparent");
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
