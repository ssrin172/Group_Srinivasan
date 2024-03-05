package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectTaskController {
    @FXML
    private void openScrumSimulator(ActionEvent event) {
        // Implement the action to open Scrum Simulator here
        System.out.println("Opening Scrum Simulator");
    }

    @FXML
    private void openScrumGameplay(ActionEvent event) {
        // Implement the action to open Scrum Gameplay here
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectRole-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 400, 200);

            stage.setScene(scene);
            stage.setTitle("Choose a Role");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Opening Scrum Gameplay");
    }
}

