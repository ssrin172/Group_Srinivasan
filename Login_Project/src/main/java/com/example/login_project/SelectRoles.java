//package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SelectRoles extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Role Selection");

        // Create a label
        Label label = new Label("Select a role:");

        // Create a ComboBox (dropdown) with role options
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Scrum Master", "Product Owner", "Development Team");

        // Set a default selection (optional)
        roleComboBox.setValue("Scrum Master");

        // Create a layout for the GUI
        StackPane root = new StackPane();
        root.getChildren().addAll(label, roleComboBox);
        StackPane.setMargin(label, new Insets(-50, 0, 50, 0)); // Adjust label position

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
