package com.group_srinivasan.scrumui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class DevTeamController {

    @FXML
    private ListView<UserStoriesData.DataItem> sprintBacklogListView;

    private Map<UserStoriesData.DataItem, TextField> itemTextFieldMap = new HashMap<>();
    private Map<UserStoriesData.DataItem, Button> itemButtonMap = new HashMap<>();

    public void initialize() {
        // Call the API asynchronously to retrieve data
        fetchDataFromApiAsync3();

        // Set the custom cell factory for the ListView
        sprintBacklogListView.setCellFactory(getCellFactory());
    }

    private void fetchDataFromApiAsync3() {
        // Asynchronous data fetching logic (GET request)
        new Thread(() -> {
            try {
                String apiUrl = "http://localhost:8080/SprintBacklog/getAll";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    // If the request was successful, parse the JSON response
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        UserStoriesData.DataItem[] dataItems = objectMapper.readValue(connection.getInputStream(), UserStoriesData.DataItem[].class);
                        List<UserStoriesData.DataItem> dataList = Arrays.asList(dataItems);

                        // Update UI with retrieved data on the JavaFX Application Thread
                        sprintBacklogListView.getItems().addAll(dataList);
                    }catch(Exception e)
                    {
                        System.out.println("Error occurred object mapper " + e);
                    }
                } else {
                    System.out.println("API request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Callback<ListView<UserStoriesData.DataItem>, ListCell<UserStoriesData.DataItem>> getCellFactory() {
        return param -> new ListCell<>() {
            @Override
            protected void updateItem(UserStoriesData.DataItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                    setGraphic(null);
                } else {
                    setText(item.getId() + " - " + item.getBvd());

                    // Create a TextField for story points
                    TextField textField = new TextField();
                    itemTextFieldMap.put(item, textField);

                    // Create a Button for each data item
                    Button updateButton = new Button("Update");
                    itemButtonMap.put(item, updateButton);

                    // Set an initial value if needed
                    textField.setText(""); // Set your initial value here

                    // Handle button click event
                    updateButton.setOnAction(event -> handleUpdateButtonClick(item));

                    // Set the graphic with TextField and Button
                    HBox hbox = new HBox(textField, updateButton);
                    setGraphic(hbox);
                }
            }
        };
    }

    private void handleUpdateButtonClick(UserStoriesData.DataItem item) {
        TextField textField = itemTextFieldMap.get(item);
        String textInput = textField.getText();

        if (textField != null) {
            // Get the entered story points from the TextField
//            String storyPoints = textField.getText();

            // Send an HTTP POST request to update the values in the database
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/SprintBacklog/"+ item.getId()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString("{\"storyPoints\":\"" + Integer.parseInt(textInput) + "\",\"id\":\"" + item.getId() + "\",\"bv\":\"" + item.getBvd() + "\"}"))
                    .build();
            // System.out.println(item.getStp());

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    // Successful update
                    // Show a success message if needed
                    System.out.println("Story Points updated for User Story with ID: " + item.getId());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startSimulation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartSimulationDevTeam-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 400, 200);

            stage.setScene(scene);
            stage.setTitle("Start Simulation");

            try {
                // Replace this with your actual API endpoint URL
                String apiUrl = "http://localhost:8080/simulate/devTeam";
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