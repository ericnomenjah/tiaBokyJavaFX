package com.example.bibliotheque.Repository;

import com.example.bibliotheque.Database.DatabaseConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
