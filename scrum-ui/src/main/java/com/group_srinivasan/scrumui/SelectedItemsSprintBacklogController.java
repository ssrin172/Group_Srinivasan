package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



public class SelectedItemsSprintBacklogController {

    @FXML
    private VBox checkedItemsView;

    @FXML
    private ListView<String> checkedItemsListView;

    @FXML
    private TextField sprintLen;

    @FXML
    private TextField noOfSprint;


    @FXML
    public void setCheckedItems(List<UserStoriesData.DataItem> checkedItems) {
        System.out.println(checkedItems);
        for (UserStoriesData.DataItem item : checkedItems) {
            checkedItemsListView.getItems().add(item.getId() + " - " + item.getBvd());
        }
    }

    public void setCheckedItems(ActionEvent actionEvent) {
        System.out.println("Success");
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