module com.example.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.bibliotheque to javafx.fxml;
    exports com.example.bibliotheque;

    opens com.example.bibliotheque.Controller to javafx.fxml;
    exports com.example.bibliotheque.Controller to javafx.fxml;
}