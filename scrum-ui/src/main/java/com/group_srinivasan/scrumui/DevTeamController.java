package com.group_srinivasan.scrumui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    
}
