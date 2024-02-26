package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: Controller.java is a controller class responsible for initializing 
 * the JavaFX application and setting up the initial view with version information.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    // Initializes the controller after its root element has been completely
    // processed
    public void initialize() {
        // Retrieves the Java version and JavaFX version system properties
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        // Sets the text of the label to display JavaFX and Java versions
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }
}