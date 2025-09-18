module com.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;


    opens com.example.escriturarapida to javafx.fxml;
    exports com.example.escriturarapida;
    exports com.example.escriturarapida.model;
    opens com.example.escriturarapida.model to javafx.fxml;
    exports com.example.escriturarapida.controller;
    opens com.example.escriturarapida.controller to javafx.fxml;
}