package com.group_srinivasan.scrumui;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
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

    public void startSimulation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartSimulationProductOwner-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 400, 200);
            stage.setScene(scene);
            stage.setTitle("Start Simulation");

            try {
                // Replace this with your actual API endpoint URL
                String apiUrl = "http://localhost:8080/simulate/productOwner";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                // Handle HTTP response codes, timeouts, etc.
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Start Simulation Successful");
                } else {
                    // Handle API error response
                    System.out.println("API request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle API call error
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}