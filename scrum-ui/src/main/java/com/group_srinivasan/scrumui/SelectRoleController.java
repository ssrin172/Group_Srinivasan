package com.group_srinivasan.scrumui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

public class SelectRoleController {
    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private void selectRole(ActionEvent event) {
        String selectedRole = roleComboBox.getValue();
        if( selectedRole.equals("Product Owner")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserStories-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Product Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if( selectedRole.equals("Scrum Master")){
            // Delete call for Product Backlog
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
            try {
                // API Call from ScrumMaster
                try {
                    String apiUrl = "http://localhost:8080/simulate/scrumMasterSelection";
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        // If the request was successful, parse the JSON response
                        System.out.println("API call successful");
                    } else {
                        System.out.println("API request failed with response code: " + responseCode);
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //change the view.fxml file to the scrum master's GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserStoryScrumMaster-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Sprint Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if( selectedRole.equals("Development Team")){
            // Delete call for Product Backlog
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
                    System.out.println("Delete Request is successful. Product Backlog is Empty");
                    // Now, send an HTTP POST request to add new values
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            // Delete call for Sprint Backlog
            HttpRequest deleteRequest2 = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/SprintBacklog/deleteAll"))
                    .DELETE()
                    .build();

            // Send the DELETE request
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> deleteResponse = client.send(deleteRequest2, HttpResponse.BodyHandlers.ofString());
                if (deleteResponse.statusCode() == 200) {
                    // Successful delete
                    System.out.println("Delete Request is successful. Sprint Backlog is Empty");
                    // Now, send an HTTP POST request to add new values
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            try {
                // API Call from DevTeam to simulate the automatic simulation function
                try {
                    String apiUrl = "http://localhost:8080/simulate/devTeamSelection";
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        // If the request was successful, parse the JSON response
                        System.out.println("API call successful");
                    } else {
                        System.out.println("API request failed with response code: " + responseCode);
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // change the view.fxml file to the scrum master's GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DevelopmentTeam-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 400, 200);

                stage.setScene(scene);
                stage.setTitle("Select User Stories for Sprint Backlog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (selectedRole != null) {
            System.out.println("Selected Role: " + selectedRole);
        } else {
            System.out.println("Please select a role.");
        }
    }
}