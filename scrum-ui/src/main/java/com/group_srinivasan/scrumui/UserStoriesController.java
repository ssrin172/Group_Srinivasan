package com.group_srinivasan.scrumui;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class UserStoriesController {

    @FXML
    private ListView<UserStoriesData.DataItem> dataListView;

    public void initialize() {
        // Call the API asynchronously to retrieve data
        fetchDataFromApiAsync();

        // Set the custom cell factory for the ListView
        dataListView.setCellFactory(getCellFactory());
    }

    private void fetchDataFromApiAsync() {
        // Asynchronous data fetching logic (GET request)
        new Thread(() -> {
            try {
                // Replace this with your actual API endpoint URL
                String apiUrl = "localhost:8080/UserStoryBacklog/getAll";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Handle HTTP response codes, timeouts, etc.
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // If the request was successful, parse the JSON response
                    ObjectMapper objectMapper = new ObjectMapper();
                    UserStoriesData.DataItem[] dataItems = objectMapper.readValue(connection.getInputStream(), UserStoriesData.DataItem[].class);
                    List<UserStoriesData.DataItem> dataList = Arrays.asList(dataItems);

                    // Update UI with retrieved data on the JavaFX Application Thread
                    dataListView.getItems().addAll(dataList);
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

    private Callback<ListView<UserStoriesData.DataItem>, ListCell<UserStoriesData.DataItem>> getCellFactory() {
        return param -> new ListCell<>() {
            @Override
            protected void updateItem(UserStoriesData.DataItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getId() + " - " + item.getBvd());
                }
            }
        };
    }
}
