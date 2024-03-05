package com.group_srinivasan.scrumui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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

public class UserStoriesController {


    @FXML
    private ListView<UserStoriesData.DataItem> dataListView;

    private Map<UserStoriesData.DataItem, CheckBox> itemCheckBoxMap = new HashMap<>(); // Define itemCheckBoxMap

    public void initialize() {
        // Call the API asynchronously to retrieve data
        fetchDataFromApiAsync();

        // Set the custom cell factory for the ListView
        dataListView.setCellFactory(getCellFactory());
        dataListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        for (UserStoriesData.DataItem item : dataListView.getItems()) {
//            CheckBox checkBox = new CheckBox();
//            itemCheckBoxMap.put(item, checkBox);
//        }


    }

    private void fetchDataFromApiAsync() {
        // Asynchronous data fetching logic (GET request)
        new Thread(() -> {
            try {
                // Replace this with your actual API endpoint URL
                String apiUrl = "http://localhost:8080/UserStoryBacklog/getAll";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                // Handle HTTP response codes, timeouts, etc.
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {

                    // If the request was successful, parse the JSON response
                    ObjectMapper objectMapper = new ObjectMapper();
                    UserStoriesData.DataItem[] dataItems = objectMapper.readValue(connection.getInputStream(), UserStoriesData.DataItem[].class);
                    List<UserStoriesData.DataItem> dataList = Arrays.asList(dataItems);

                    // Update UI with retrieved data on the JavaFX Application Thread
                    dataListView.getItems().addAll(dataList);

                    //Empty Product Backlog
                    HttpRequest deleteRequest = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/ProductBacklog/deleteAll"))
                            .DELETE()
                            .build();

                    // Send the DELETE request
                    try {
                        HttpClient client = HttpClient.newHttpClient();
                        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
                        if (deleteResponse.statusCode() == 200) {
                            // Successful delete
                            System.out.println("Delete Request is successful");
                            // Now, send an HTTP POST request to add new values
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Handle API error response
                    System.out.println("API request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle API call error
            }
        }).start();
    }


    private List<String> selectedItemsList = new ArrayList<>();

    private Callback<ListView<UserStoriesData.DataItem>, ListCell<UserStoriesData.DataItem>> getCellFactory() {
        return param -> new ListCell<>() {
            @Override
            protected void updateItem(UserStoriesData.DataItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    // Create a CheckBox for each data item
                    CheckBox checkBox = new CheckBox(item.getId() + " - " + item.getBvd());

                    // Handle checkbox selection here
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            // Handle item selection here (e.g., send it to the next stage)
                            System.out.println(item.getId() + " is selected");
                            itemCheckBoxMap.put(item, checkBox);

                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/ProductBacklog/add"))
                                    .header("Content-Type", "application/json")
                                    .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"" + item.getId() + "\",\"bv\":\"" + item.getBvd() + "\"}"))
                                    .build();
                            try {
                                HttpClient client = HttpClient.newHttpClient();
                                HttpResponse<String> response;
                                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                                if (response.statusCode() == 200) {
                                    // Successful login
                                    // Show a success message
                                    System.out.println("User Story added successfully");
                                }
                            }catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }



                            System.out.println(item.getId());
                            System.out.println(item.getBvd());
                        }
                    });

                    setGraphic(checkBox);
                }
            }
        };
    }

    @FXML
    private void openSelectedItems() {
        List<UserStoriesData.DataItem> selectedItems = new ArrayList<>();

        for (UserStoriesData.DataItem item : itemCheckBoxMap.keySet()) {

            CheckBox checkBox = itemCheckBoxMap.get(item);
            if (checkBox.isSelected()) {
                selectedItems.add(item);
            }
        }

        try {
            // Load the new stage from the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedItems-View.fxml"));
            Parent root = loader.load();
            SelectedItemsController selectedItemsController = loader.getController();

            // Pass the selected items to the controller of the new stage
            selectedItemsController.setSelectedItems(selectedItems);

            Stage newStage = new Stage();
            newStage.setTitle("Selected Items for Product Backlog");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error loading the new stage
        }
    }

}
