package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;



public class SelectedItemsController {

    @FXML
    private VBox selectedItemsView;

    @FXML
    private ListView<String> selectedItemsListView;

    @FXML
    public void setSelectedItems(List<UserStoriesData.DataItem> selectedItems) {
        System.out.println(selectedItems);
        for (UserStoriesData.DataItem item : selectedItems) {
            selectedItemsListView.getItems().add(item.getId() + " - " + item.getBvd());
        }
    }

    public void moveSelectRoles(ActionEvent event) {
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
    }
}