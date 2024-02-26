package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: Project3.java is the main class responsible for creating and 
 * managing the user interface of the Road Trip Cost Estimator application.
 */

import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Project3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the root BorderPane
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create the grid pane for UI elements
        GridPane gridPane = createGridPane();
        root.setCenter(gridPane);

        // Create the scene
        Scene scene = new Scene(root, 475, 375);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Road Trip Cost Estimator");
        primaryStage.show();
    }

    // Method to create the grid pane with UI elements
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create and add labels
        String[] labels = { "Distance:", "Gasoline Cost:", "Gas Mileage:", "Number Of Days:",
                "Hotel Cost:", "Food Cost:", "Attractions:" };
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            gridPane.add(label, 0, i);
        }

        // Create and add text fields
        TextField[] textFields = createTextFields(labels.length);
        for (int i = 0; i < textFields.length; i++) {
            gridPane.add(textFields[i], 1, i);
        }

        // Create and add combo boxes
        ComboBox<String>[] comboBoxes = createComboBoxes();
        for (int i = 0; i < comboBoxes.length; i++) {
            gridPane.add(comboBoxes[i], 2, i);
        }

        // Create and add total trip cost label and field
        Label totalTripCostLabel = new Label("Total Trip Cost:");
        TextField totalTripCostField = new TextField();
        totalTripCostField.setEditable(false);
        totalTripCostField.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(totalTripCostLabel, 0, 8);
        gridPane.add(totalTripCostField, 1, 8);

        // Create and add buttons
        Button calculateButton = createCalculateButton(textFields, comboBoxes, totalTripCostField, true);
        Button clearButton = createClearButton(textFields, comboBoxes, totalTripCostField);
        gridPane.add(calculateButton, 1, 7);

        // Add clear button
        gridPane.add(clearButton, 2, 4);

        return gridPane;
    }

    // Method to create text fields
    private TextField[] createTextFields(int count) {
        TextField[] textFields = new TextField[count];
        for (int i = 0; i < count; i++) {
            textFields[i] = new TextField();
            textFields[i].setAlignment(Pos.CENTER_RIGHT);
            addFocusListeners(textFields[i]);
        }
        return textFields;
    }

    // Method to create combo boxes
    private ComboBox<String>[] createComboBoxes() {
        String[][] comboBoxItems = {
                { "miles", "kilometers" },
                { "dollars/gallon", "dollars/liter" },
                { "miles/gallon", "kilometers/liter" }
        };

        @SuppressWarnings("unchecked")
        ComboBox<String>[] comboBoxes = new ComboBox[3];
        for (int i = 0; i < comboBoxes.length; i++) {
            comboBoxes[i] = new ComboBox<>();
            comboBoxes[i].getItems().addAll(comboBoxItems[i]);
            comboBoxes[i].setValue(comboBoxItems[i][0]);
            comboBoxes[i].setBorder(new Border(new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            addHoverEffects(comboBoxes[i]);
        }
        return comboBoxes;
    }

    // Method to create the calculate button
    private Button createCalculateButton(TextField[] textFields, ComboBox<String>[] comboBoxes,
            TextField totalTripCostField, boolean displayAlerts) {
        Button calculateButton = new Button("Calculate");
        calculateButton.setPadding(new Insets(5, 55, 5, 55));
        calculateButton.setBorder(new Border(
                new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        addHoverEffects(calculateButton);
        calculateButton.setOnAction(
                event -> handleCalculateButtonAction(textFields, comboBoxes, totalTripCostField, displayAlerts));
        return calculateButton;
    }

    // Method to create the clear button
    private Button createClearButton(TextField[] textFields, ComboBox<String>[] comboBoxes,
            TextField totalTripCostField) {
        Button clearButton = new Button("Clear Fields");
        // Set default border
        clearButton.setBorder(new Border(
                new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        addHoverEffects(clearButton);
        clearButton.setOnAction(e -> clearFields(textFields, comboBoxes, totalTripCostField));
        return clearButton;
    }

    // Method to clear all fields
    private void clearFields(TextField[] textFields, ComboBox<String>[] comboBoxes, TextField totalTripCostField) {
        // Clear all text fields
        for (TextField textField : textFields) {
            textField.clear();
        }
        // Reset combo boxes to their default values
        for (ComboBox<String> comboBox : comboBoxes) {
            comboBox.getSelectionModel().selectFirst(); // Select the first item in the combo box
        }
        // Clear total trip cost field
        totalTripCostField.clear();
    }

    // Method to add focus listeners to highlight text fields when focused
    private void addFocusListeners(TextField textField) {
        // Set default border
        textField.setBorder(
                new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Add focus listener to change border
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                textField.setBorder(new Border(
                        new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            } else {
                textField.setBorder(new Border(
                        new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            }
        });
    }

    // Method to add hover effects to buttons and combo boxes
    private void addHoverEffects(Control control) {
        // Add hover effect to change border color
        control.setOnMouseEntered(e -> {
            if (control instanceof ComboBox) {
                control.setBorder(new Border(new BorderStroke(Color.ROYALBLUE.brighter(), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(1))));
            } else if (control instanceof Button) {
                Button button = (Button) control; // Cast to Button
                if (button.getText().equals("Clear Fields")) {
                    control.setBorder(new Border(new BorderStroke(Color.FIREBRICK.brighter(), BorderStrokeStyle.SOLID,
                            null, new BorderWidths(1))));
                } else if (button.getText().equals("Calculate")) {
                    control.setBorder(new Border(new BorderStroke(Color.GREEN.brighter(), BorderStrokeStyle.SOLID, null,
                            new BorderWidths(1))));
                }
            }
        });
        // Reset border color on mouse exit
        control.setOnMouseExited(e -> {
            if (control instanceof ComboBox) {
                control.setBorder(new Border(
                        new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            } else if (control instanceof Button) {
                Button button = (Button) control; // Cast to Button
                if (button.getText().equals("Clear Fields")) {
                    control.setBorder(new Border(
                            new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                } else if (button.getText().equals("Calculate")) {
                    control.setBorder(new Border(
                            new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                }
            }
        });
    }

    // Method to handle the action when the calculate button is clicked
    public void handleCalculateButtonAction(TextField[] textFields, ComboBox<String>[] comboBoxes,
            TextField totalTripCostField, boolean showAlert) {
        try {
            // Validate text fields for empty values
            for (TextField textField : textFields) {
                if (textField.getText().isEmpty()) {
                    throw new IllegalArgumentException("One or more fields are empty.");
                }
            }

            // Validate combo box selection
            String[] validOption1 = { "miles", "dollars/gallon", "miles/gallon" };
            String[] validOption2 = { "kilometers", "dollars/liter", "kilometers/liter" };
            String[] currentSelection = {
                    comboBoxes[0].getValue(),
                    comboBoxes[1].getValue(),
                    comboBoxes[2].getValue()
            };

            if (!Arrays.equals(currentSelection, validOption1) && !Arrays.equals(currentSelection, validOption2)) {
                throw new IllegalArgumentException("Invalid selection for combo boxes.");
            }

            // Validate data types
            double distance = Double.parseDouble(textFields[0].getText());
            double gasolineCost = Double.parseDouble(textFields[1].getText());
            double gasMileage = Double.parseDouble(textFields[2].getText());
            int numberOfDays = Integer.parseInt(textFields[3].getText());
            double hotelCost = Double.parseDouble(textFields[4].getText());
            double foodCost = Double.parseDouble(textFields[5].getText());
            double attractions = Double.parseDouble(textFields[6].getText());

            // Check if any numeric field is <= 0
            if (distance <= 0 || gasolineCost <= 0 || gasMileage <= 0 || numberOfDays <= 0 || hotelCost < 0
                    || foodCost <= 0 || attractions < 0) {
                throw new IllegalArgumentException(
                        "Distance, Gasoline Cost, Gas Mileage, Number of Days, and Food Cost must be greater than zero. Attractions and Hotel Cost must be positive numbers.");
            }

            // Create a TripCost object
            TripCost tripCost = new TripCost(distance, gasolineCost, gasMileage,
                    numberOfDays, hotelCost, foodCost, attractions);

            // Calculate total trip cost
            double totalCost = tripCost.calculateTotalTripCost();

            // Display total trip cost in the text field
            totalTripCostField.setText(String.format("$%.2f", totalCost));

        } catch (NumberFormatException error) {
            if (showAlert) {
                // Handle non-numeric entries
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid number.");
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid input: Please enter a valid number.");
            }
        } catch (IllegalArgumentException error) {
            // Handle empty fields or invalid combo box selection
            if (showAlert) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText(error.getMessage());
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid input: " + error.getMessage());
            }
        }
    }

    // Launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
