package com.group_srinivasan.scrumui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginGui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGui-view.fxml"));

        Scene scene= new Scene(root, 600, 400);

        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.getScene().getRoot().setStyle("-fx-background-color: #336699;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

