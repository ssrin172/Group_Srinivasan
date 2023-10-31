package com.example.myfirstapp;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import java.net.URI;
import java.net.http.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    public void loginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/student/add"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                    .build();

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response;
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    // Successful login
                    // Show a success message
                    Platform.runLater(() -> showSuccessAlert());
                } else {
                    // Failed login
                    // Show an error message
                    Platform.runLater(() -> showErrorAlert("Login Failed", "Invalid username or password. Please try again."));
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                Platform.runLater(() -> showErrorAlert("Error", "An error occurred while processing your request."));
            }


        // Replace this with your actual login logic.
//            messageLabel.setText("Login successful");
//            messageLabel.setText("Login failed. Please try again.");

    }
    private void showSuccessAlert() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Login Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Welcome! New User added");
        successAlert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}

