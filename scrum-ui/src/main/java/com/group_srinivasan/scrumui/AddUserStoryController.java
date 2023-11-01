package com.group_srinivasan.scrumui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddUserStoryController {

    @FXML
    private TextField userStoryField;

    public void addUserStory() {
        String userStory = userStoryField.getText();

        // Here, you would add the user story to the database.
        // You can add your database logic here.

        // For now, let's just print the user story to the console.
        System.out.println("Added User Story: " + userStory);

        // Clear the text field after adding the user story.
        userStoryField.clear();
    }
}
