package com.example.bibliotheque.Repository;

import com.example.bibliotheque.Database.DatabaseConnection;
import com.example.bibliotheque.Model.Pret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PretRepository {
    public void add(String lecteurNumero, String livreNumero, String datePret, String dateRetour) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "INSERT INTO prets (numeroLecteur,numeroLivre,datePret,dateRetour)" +
                "VALUES ('"+lecteurNumero+"','"+livreNumero+"','"+ datePret+"','"+dateRetour+"')";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            LecteurRepository lecteurRepository = new LecteurRepository();
            lecteurRepository.incrementerNbPret(lecteurNumero);

            LivreRepository livreRepository = new LivreRepository();
            livreRepository.incrementPret(livreNumero);

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Nouveau pret enregistrer !");
            alert.show();

        }catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public ObservableList getPrets() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = " SELECT * FROM prets ";
        ObservableList list = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Pret(
                        Integer.parseInt(resultSet.getString("numero")),
                        Integer.parseInt(resultSet.getString("numeroLecteur")),
                        Integer.parseInt(resultSet.getString("numeroLivre")),
                        resultSet.getString("datePret"),
                        resultSet.getString("dateRetour"),
                        resultSet.getBoolean("rendu")
                ));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return list;
    }
}
