package com.group_srinivasan.scrumui;

import com.group_srinivasan.scrumui.SelectedItemsSprintBacklogController;
import com.group_srinivasan.scrumui.UserStoriesData;
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

public class UserStoryScrumMasterController {

    @FXML
    private ListView<UserStoriesData.DataItem> productBacklogListView;

    private Map<UserStoriesData.DataItem, CheckBox> itemCheckBoxMap2 = new HashMap<>(); // Define itemCheckBoxMap
//Change the itemCheckBoxMap variable name to itemCheckboxMap2 for new checkboxes to select.
    public void initialize() {
        // Call the API asynchronously to retrieve data
        fetchDataFromApiAsync2();

        // Set the custom cell factory for the ListView
        productBacklogListView.setCellFactory(getCellFactory());
        productBacklogListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        for (UserStoriesData.DataItem item : dataListView.getItems()) {
//            CheckBox checkBox = new CheckBox();
//            itemCheckBoxMap.put(item, checkBox);
//        }


    }

//    No need to fetch the user stories from the API, here we will be fetching the past checked user stories
    private void fetchDataFromApiAsync2() {
        // Asynchronous data fetching logic (GET request)
        new Thread(() -> {
            try {
                String apiUrl = "http://localhost:8080/ProductBacklog/getAll";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {

                    // If the request was successful, parse the JSON response
                    ObjectMapper objectMapper2 = new ObjectMapper();
                    UserStoriesData.DataItem[] dataItems2 = objectMapper2.readValue(connection.getInputStream(), UserStoriesData.DataItem[].class);
                    List<UserStoriesData.DataItem> dataList2 = Arrays.asList(dataItems2);

                    // Update UI with retrieved data on the JavaFX Application Thread
                    productBacklogListView.getItems().addAll(dataList2);

                    //Empty Sprint Backlog
                    HttpRequest deleteRequest = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/SprintBacklog/deleteAll"))
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
                    System.out.println("API request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private List<String> selectedItemsList2 = new ArrayList<>();

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
                            itemCheckBoxMap2.put(item, checkBox);

                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("http://localhost:8080/SprintBacklog/add"))
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
                                    System.out.println("User Story added to Sprint backlog successfully");
                                }
                            }catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    setGraphic(checkBox);
                }
            }
        };
    }

    @FXML
    private void openCheckedItems() {
        List<UserStoriesData.DataItem> checkedItems = new ArrayList<>();

        for (UserStoriesData.DataItem item : itemCheckBoxMap2.keySet()) {

            CheckBox checkBox = itemCheckBoxMap2.get(item);
            if (checkBox.isSelected()) {
                checkedItems.add(item);
//                System.out.println("Item is checked");
            }
        }

        try {
            // Load the new stage from the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedItemsSprintBacklog-view.fxml"));
            Parent root = loader.load();

            SelectedItemsSprintBacklogController checkedItemsController = loader.getController();

            // Pass the selected items to the controller of the new stage
            checkedItemsController.setCheckedItems(checkedItems);

            Stage newStage = new Stage();
            newStage.setTitle("Selected User Stories for Sprint Backlog");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error loading the new stage
        }
    }

}

