module ec.edu.espol.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.gui to javafx.fxml;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.gui;
    exports ec.edu.espol.controller;
}