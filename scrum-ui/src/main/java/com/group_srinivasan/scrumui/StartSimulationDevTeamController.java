package com.group_srinivasan.scrumui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class StartSimulationDevTeamController implements Initializable {

    @FXML
    private TextArea resultTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code, if needed
    }

    public void setResult(String result) {
        resultTextArea.setText(result);
    }

    // You can add other methods as needed
}
