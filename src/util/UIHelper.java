package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UIHelper {

    // Creates a standardized button
    public static Button createButton(String text) {
        Button button = new Button(text);
        // Add style classes or any common configurations
        button.getStyleClass().add("standard-button");
        return button;
    }

    // Creates a standardized text field
    public static TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        // Add style classes or any common configurations
        textField.getStyleClass().add("standard-text-field");
        return textField;
    }

    // Shows an information alert dialog
    public static void showInfoAlert(String title, String header, String content) {
        showAlert(AlertType.INFORMATION, title, header, content);
    }

    // Shows an error alert dialog
    public static void showErrorAlert(String title, String header, String content) {
        showAlert(AlertType.ERROR, title, header, content);
    }

    // General method for showing an alert of any type
    private static void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


//To use this class in your application, simply call the static methods when you need to create a UI element. For example:
//Button myButton = UIHelper.createButton("Click Me");
//TextField myTextField = UIHelper.createTextField("Enter your name");
//Darsh is amazing!