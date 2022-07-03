module com.example.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.bibliotheque to javafx.fxml;
    exports com.example.bibliotheque;

    opens com.example.bibliotheque.Controller to javafx.fxml;
    exports com.example.bibliotheque.Controller to javafx.fxml;

    opens com.example.bibliotheque.Model to javafx.base;
    opens com.example.bibliotheque.Repository to javafx.base;
}