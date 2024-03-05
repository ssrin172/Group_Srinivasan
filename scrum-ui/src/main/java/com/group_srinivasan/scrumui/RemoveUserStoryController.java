package com.group_srinivasan.scrumui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RemoveUserStoryController {

    @FXML
    private ComboBox<String> userStoryComboBox;

    public void initialize() {
        // Initialize the ComboBox with user stories from the database.
        // Replace this with your database retrieval logic.
        ObservableList<String> userStories = FXCollections.observableArrayList(
                "User Story 1",
                "User Story 2",
                "User Story 3"
                // Add more user stories here
        );

        userStoryComboBox.setItems(userStories);
    }

    public void removeUserStory() {
        String selectedUserStory = userStoryComboBox.getValue();

        // Here, you would remove the selected user story from the database.
        // You can add your database removal logic here.

        // For now, let's just print the removed user story to the console.
        System.out.println("Removed User Story: " + selectedUserStory);

        // Clear the selection in the ComboBox after removal.
        userStoryComboBox.getSelectionModel().clearSelection();
    }
}