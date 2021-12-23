package com.example.imgoinginsanehelp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertBoxKontroller {

    private String message;

    @FXML
    private Button closeButton;

    public AlertBoxKontroller(String message) {
        this.message=message;
    }

    @FXML
    private Label messageLabel;

    @FXML
    void initialize(){
        messageLabel.setText(message);
    }

    @FXML
    public void closeAlertWindow (){

        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
