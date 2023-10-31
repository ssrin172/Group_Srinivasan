package com.example.myfirstapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label button_clickMe;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onClickMeButtonClick() {
        button_clickMe.setText("Hey, You Clicked Me!, Have a nice day..");
    }

}