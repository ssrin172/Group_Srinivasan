package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectRoleController {
    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private void selectRole(ActionEvent event) {
        String selectedRole = roleComboBox.getValue();
        if( selectedRole.equals("Product Owner")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserStories-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Product Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if( selectedRole.equals("Scrum Master")){
            try {
                //change the view.fxml file to the scrum master's GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserStoryScrumMaster-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Sprint Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if( selectedRole.equals("Development Team")){
            try {
                //change the view.fxml file to the scrum master's GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DevelopmentTeam-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Sprint Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (selectedRole != null) {
            System.out.println("Selected Role: " + selectedRole);
        } else {
            System.out.println("Please select a role.");
        }
    }
}

