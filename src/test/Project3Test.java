package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: Project3Test.java is a test class for the Project3 application.
 * It includes tests for positive scenarios and exception handling.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Project3;

public class Project3Test extends Application {

    // Launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    // Method to initialize the JavaFX components and execute tests.
    @Override
    public void start(Stage primaryStage) {
        // Initialize your JavaFX components here
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);

        // Execute tests and report results
        boolean test1 = testPositiveScenario();
        boolean test2 = testTextFieldExceptions();
        boolean test3 = testComboBoxExceptions();

        // Output the results of the test
        System.out.println("Test 1: Positive Scenario - " + (test1 ? "Passed" : "Failed"));
        System.out.println("Test 2: testTextFieldExceptions - " + (test2 ? "Passed" : "Failed"));
        System.out.println("Test 3: testComboBoxExceptions - " + (test3 ? "Passed" : "Failed"));

        // Close the primaryStage to exit the JavaFX application
        primaryStage.close();
    }

    // Positive Test: Ensure correct behavior with valid input
    public static boolean testPositiveScenario() {
        try {
            // Simulate valid input
            TextField[] textFields = {
                    new TextField("500"),
                    new TextField("3.5"),
                    new TextField("25.0"),
                    new TextField("7"),
                    new TextField("100.0"),
                    new TextField("200.0"),
                    new TextField("50.0")
            };

            @SuppressWarnings("unchecked")
            ComboBox<String>[] comboBoxes = new ComboBox[] {
                    new ComboBox<>(),
                    new ComboBox<>(),
                    new ComboBox<>()
            };

            // Set values for each ComboBox
            comboBoxes[0].getItems().addAll("miles", "kilometers");
            comboBoxes[1].getItems().addAll("dollars/gallon", "dollars/liter");
            comboBoxes[2].getItems().addAll("miles/gallon", "kilometers/liter");

            // Set ComboBox values
            comboBoxes[0].setValue("miles");
            comboBoxes[1].setValue("dollars/gallon");
            comboBoxes[2].setValue("miles/gallon");

            // Create an instance of Project3
            Project3 project3 = new Project3();

            // Verify if no exception is thrown for valid input
            project3.handleCalculateButtonAction(textFields, comboBoxes, new TextField(), false);

            // If no exception is thrown, the test passes
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Negative Test 1: Test for handling exceptions related to text fields.
    public static boolean testTextFieldExceptions() {
        // Initially, assume the test will fail
        boolean passed = false;
        try {
            // Create ComboBoxes
            @SuppressWarnings("unchecked")
            ComboBox<String>[] comboBoxes = new ComboBox[] {
                    new ComboBox<>(),
                    new ComboBox<>(),
                    new ComboBox<>()
            };

            // Set values for each ComboBox
            comboBoxes[0].getItems().addAll("miles", "kilometers");
            comboBoxes[1].getItems().addAll("dollars/gallon", "dollars/liter");
            comboBoxes[2].getItems().addAll("miles/gallon", "kilometers/liter");

            // Scenario 1: Missing Gasoline Cost
            TextField[] textFields1 = {
                    new TextField("638.83"),
                    new TextField(),
                    new TextField("35.51"),
                    new TextField("5"),
                    new TextField("222.95"),
                    new TextField("271.37"),
                    new TextField("88.31")
            };

            // Scenario 2: Negative Distance
            TextField[] textFields2 = {
                    new TextField("-638.83"),
                    new TextField("3.89"),
                    new TextField("35.51"),
                    new TextField("5"),
                    new TextField("222.95"),
                    new TextField("271.37"),
                    new TextField("88.31")
            };

            // Scenario 3: Char value for gas mileage
            TextField[] textFields3 = {
                    new TextField("638.83"),
                    new TextField("3.89"),
                    new TextField("A"),
                    new TextField("5"),
                    new TextField("222.95"),
                    new TextField("271.37"),
                    new TextField("88.31")
            };

            // Initialize the total trip cost TextField to simulate UI behavior
            TextField totalTripCostField = new TextField("Expected not to change");

            // Create an instance of Project3
            Project3 project3 = new Project3();

            // Set ComboBox values
            comboBoxes[0].setValue("miles");
            comboBoxes[1].setValue("dollars/gallon");
            comboBoxes[2].setValue("miles/gallon");

            // Call handleCalculateButtonAction
            project3.handleCalculateButtonAction(textFields1, comboBoxes, totalTripCostField, false);
            project3.handleCalculateButtonAction(textFields2, comboBoxes, totalTripCostField, false);
            project3.handleCalculateButtonAction(textFields3, comboBoxes, totalTripCostField, false);

            // Check if totalTripCostField text remains unchanged
            if (totalTripCostField.getText().equals("Expected not to change")) {
                // The test passes if the total trip cost field is unchanged
                passed = true;
            }
        } catch (Exception e) {
            // Catch any unexpected exceptions that may occur
            e.printStackTrace();
        }

        // Return the status of the test
        return passed;
    }

    // Negative Test 2: Test for handling exceptions related to combo boxes.
    public static boolean testComboBoxExceptions() {
        // Initially, assume the test will fail
        boolean passed = false;
        try {
            TextField[] textFields = {
                    new TextField("638.83"),
                    new TextField("3.89"),
                    new TextField("35.51"),
                    new TextField("5"),
                    new TextField("222.95"),
                    new TextField("271.37"),
                    new TextField("88.31")
            };

            // Scenario 1: Invalid Distance Measurement
            @SuppressWarnings("unchecked")
            ComboBox<String>[] comboBoxes1 = new ComboBox[] {
                    new ComboBox<>(),
                    new ComboBox<>(),
                    new ComboBox<>()
            };

            // Set values for each ComboBox
            comboBoxes1[0].getItems().addAll("miles", "kilometers");
            comboBoxes1[0].setValue("kilometers");

            comboBoxes1[1].getItems().addAll("dollars/gallon", "dollars/liter");
            comboBoxes1[1].setValue("dollars/gallon");

            comboBoxes1[2].getItems().addAll("miles/gallon", "kilometers/liter");
            comboBoxes1[2].setValue("miles/gallon");

            // Scenario 2: Invalid Gasoline Cose Measurement
            @SuppressWarnings("unchecked")
            ComboBox<String>[] comboBoxes2 = new ComboBox[] {
                    new ComboBox<>(),
                    new ComboBox<>(),
                    new ComboBox<>()
            };

            // Set values for each ComboBox
            comboBoxes2[0].getItems().addAll("miles", "kilometers");
            comboBoxes2[0].setValue("kilometers");

            comboBoxes2[1].getItems().addAll("dollars/gallon", "dollars/liter");
            comboBoxes2[1].setValue("dollars/gallon");

            comboBoxes2[2].getItems().addAll("miles/gallon", "kilometers/liter");
            comboBoxes2[2].setValue("kilometers/liter");

            // Scenario 3: Invalid Gas Mileage Measurement
            @SuppressWarnings("unchecked")
            ComboBox<String>[] comboBoxes3 = new ComboBox[] {
                    new ComboBox<>(),
                    new ComboBox<>(),
                    new ComboBox<>()
            };

            // Set values for each ComboBox
            comboBoxes3[0].getItems().addAll("miles", "kilometers");
            comboBoxes3[0].setValue("miles");

            comboBoxes3[1].getItems().addAll("dollars/gallon", "dollars/liter");
            comboBoxes3[1].setValue("dollars/gallon");

            comboBoxes3[2].getItems().addAll("miles/gallon", "kilometers/liter");
            comboBoxes3[2].setValue("kilometers/liter");

            // Initialize the total trip cost TextField to simulate UI behavior
            TextField totalTripCostField = new TextField("Expected not to change");

            // Create an instance of Project3
            Project3 project3 = new Project3();

            // Call handleCalculateButtonAction with the altered setup
            project3.handleCalculateButtonAction(textFields, comboBoxes1, totalTripCostField, false);
            project3.handleCalculateButtonAction(textFields, comboBoxes2, totalTripCostField, false);
            project3.handleCalculateButtonAction(textFields, comboBoxes3, totalTripCostField, false);

            // Check if totalTripCostField text remains unchanged
            if (totalTripCostField.getText().equals("Expected not to change")) {
                // The test passes if the total trip cost field is unchanged
                passed = true;
            }
        } catch (Exception e) {
            // Catch any unexpected exceptions that may occur
            e.printStackTrace();
        }

        // Return the status of the test
        return passed;
    }

}
