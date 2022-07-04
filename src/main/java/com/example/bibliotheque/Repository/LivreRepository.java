package com.example.bibliotheque.Repository;

import com.example.bibliotheque.Database.DatabaseConnection;
import com.example.bibliotheque.Model.Lecteur;
import com.example.bibliotheque.Model.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreRepository {

    public ObservableList<Livre> getLivres() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        ObservableList<Livre> listLivres = FXCollections.observableArrayList();
        String query = "SELECT * FROM livres";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listLivres.add(new Livre(
                        Integer.parseInt(resultSet.getString("numero")),
                        resultSet.getString("titre"),
                        resultSet.getString("autheur"),
                        resultSet.getString("edition"),
                        resultSet.getBoolean("disponible"),
                        Integer.parseInt(resultSet.getString("nbPret"))
                ));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return listLivres;
    }

    public void delete(Livre livre) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "DELETE FROM `livres` WHERE numero = " + livre.getNumero();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Livre Supprimer du base de donnée");
            alert.show();
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public void addLivre(String titre, String autheur, String edition) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "INSERT INTO livres (titre, autheur, edition) " +
                "VALUES ('"+ titre + "','"+ autheur +"','"+ edition + "')";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public Livre getLivreByNumero(String numero) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * from livres WHERE numero="+ numero;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        Livre livre = null;

        while(resultSet.next()){
            livre = new Livre(
                    Integer.parseInt(resultSet.getString("numero")),
                    resultSet.getString("titre"),
                    resultSet.getString("autheur"),
                    resultSet.getString("edition"),
                    resultSet.getBoolean("disponible"),
                    Integer.parseInt(resultSet.getString("nbPret"))
            );
        }

        return livre;
    }

    public void updateLivre(String numero, String titre, String autheur, String edition) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "UPDATE livres SET titre=?,autheur=?,edition=? WHERE numero =" + numero;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,titre);
            preparedStatement.setString(2,autheur);
            preparedStatement.setString(3,edition);

            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Information du Livre Mis à jour!");
            alert.show();
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }
}
