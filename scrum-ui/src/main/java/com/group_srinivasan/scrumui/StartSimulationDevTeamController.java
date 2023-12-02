package com.group_srinivasan.scrumui;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartSimulationDevTeamController {
    private void startSimulation(ActionEvent event){
        try {
            String apiUrl = "http://localhost:8080/simulate/devTeam";
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
    }

}
