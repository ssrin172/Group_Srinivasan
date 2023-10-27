//package com.example.login_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Login");

        // Create UI components
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Set up a message label for displaying login status
        Label messageLabel = new Label("");

        // Create a VBox layout for the login form
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel);

        // Define the login action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Replace this logic with your authentication code.
            // For this example, we are using a simple hardcoded check.
            if (username.equals("user") && password.equals("password")) {
                messageLabel.setText("Login successful!");
            } else {
                messageLabel.setText("Login failed. Please try again.");
            }
        });

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
