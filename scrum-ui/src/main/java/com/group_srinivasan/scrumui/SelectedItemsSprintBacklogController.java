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
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
    public void setSprintVariables(ActionEvent event){
        String sprintLength = sprintLen.getText();
        String numberOfSprint = noOfSprint.getText();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/SprintVariablesBacklog/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"sprintLength\":\"" + sprintLength + "\",\"numberOfSprint\":\"" + numberOfSprint + "\"}"))
                .build();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Successful login
                // Show a success message
                System.out.println("Sprint Variables added to the database.");
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startSimulation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartSimulationScrumMaster-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 400, 200);

            stage.setScene(scene);
            stage.setTitle("Start Simulation");

            try {
                // Replace this with your actual API endpoint URL
                String apiUrl = "http://localhost:8080/simulate/scrumMaster";
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