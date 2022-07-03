package com.example.bibliotheque.Repository;

import com.example.bibliotheque.Database.DatabaseConnection;
import com.example.bibliotheque.Model.Lecteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LecteurRepository {
    //All CRUD DATABASE OPERATIONS
    public ObservableList<Lecteur> getLecteurs(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        ObservableList<Lecteur> listLecteur = FXCollections.observableArrayList();
        String query = "SELECT * FROM lecteurs";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                listLecteur.add(new Lecteur(
                        Integer.parseInt(resultSet.getString("numero")),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("naissance"),
                        resultSet.getString("adresse"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                ));
            }
        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return listLecteur;
    }

    public static void addLecteur(String nom, String prenom, String naissance, String adresse, String email, String telephone) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "INSERT INTO lecteurs (nom, prenom, naissance, adresse, email, telephone) " +
                "VALUES ('"+ nom + "','"+ prenom +"','"+ naissance +"','"+ adresse +"','"+ email + "','"+ telephone +"') ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
