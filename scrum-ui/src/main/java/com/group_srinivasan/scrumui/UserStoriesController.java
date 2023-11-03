package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;
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

        fetchDataFromApiAsync();


        dataListView.setCellFactory(getCellFactory());
        dataListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        for (UserStoriesData.DataItem item : dataListView.getItems()) {
//            CheckBox checkBox = new CheckBox();
//            itemCheckBoxMap.put(item, checkBox);
//        }


    }

    private void fetchDataFromApiAsync() {

        new Thread(() -> {
            try {

                String apiUrl = "http://localhost:8080/UserStoryBacklog/getAll";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {


                    ObjectMapper objectMapper = new ObjectMapper();
                    UserStoriesData.DataItem[] dataItems = objectMapper.readValue(connection.getInputStream(), UserStoriesData.DataItem[].class);
                    List<UserStoriesData.DataItem> dataList = Arrays.asList(dataItems);


                    dataListView.getItems().addAll(dataList);

                } else {

                    System.out.println("API request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();

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

                    CheckBox checkBox = new CheckBox(item.getId() + " - " + item.getBvd());


                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            // Handle item selection here (e.g., send it to the next stage)
                            System.out.println(item.getId() + " is selected");
                            itemCheckBoxMap.put(item, checkBox);

//                            HttpRequest request = HttpRequest.newBuilder()
//                                    .uri(URI.create("http://localhost:8080/ProductBacklog/add"))
//                                    .header("Content-Type", "application/json")
//                                    .POST(HttpRequest.BodyPublishers.ofString("{\"ID\":\"" + item.getId() + "\",\"BV\":\"" + item.getBvd() + "\"}"))
//                                    .build();
                        }
                    });

                    setGraphic(checkBox);
                }
            }
        };
    }

    @FXML
    private void openSelectedItems(ActionEvent event) {
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ProductBacklog/deleteAll"))
                .build();

        for (UserStoriesData.DataItem item : itemCheckBoxMap.keySet()) {
            CheckBox checkBox = itemCheckBoxMap.get(item);
            if (checkBox.isSelected()) {

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/ProductBacklog/add"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"" + item.getId() + "\",\"bv\":\"" + item.getBvd() + "\"}"))
                        .build();


                HttpClient client = HttpClient.newHttpClient();
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenAccept(responseBody -> {

                            System.out.println("Response: " + responseBody);
                        })
                        .join();
            }
        }


        List<UserStoriesData.DataItem> selectedItems = new ArrayList<>();

        for (UserStoriesData.DataItem item : itemCheckBoxMap.keySet()) {
            CheckBox checkBox = itemCheckBoxMap.get(item);
            if (checkBox.isSelected()) {
                selectedItems.add(item);
            }
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedItems-View.fxml"));
            Parent root = loader.load();
            SelectedItemsController selectedItemsController = loader.getController();


            selectedItemsController.setSelectedItems(selectedItems);

            Stage newStage = new Stage();
            newStage.setTitle("Selected Items");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
