package com.example.bibliotheque.Controller;

import com.example.bibliotheque.Database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label message;

    @FXML
    void authenticate(ActionEvent event) {
        if(username.getText().isBlank() || password.getText().isBlank()){
            message.setText("Please enter your login !!");
            message.setVisible(true);
        }else{
            validateLogin(username.getText(),password.getText());
        }

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


    private void validateLogin(String username, String password){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String query = "SELECT COUNT(1) FROM utilisateur WHERE name='" + username + "' AND password='" + password + "'";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                if(resultSet.getInt(1)==1){

                }else{
                    message.setText("Invalid login, please try again !!!");
                    message.setVisible(true);
                }
            }

        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }


    }
}
