package com.example.imgoinginsanehelp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertBoxKontroller {

    private String message;

    @FXML
    private Button closeButton;

    /**
     * sets the message in the alertbox
     * @param message the message you want to set
     */

    public AlertBoxKontroller(String message) {
        this.message=message;
    }

    @FXML
    private Label messageLabel;

    @FXML
    void initialize(){
        messageLabel.setText(message);
    }

    /**
     * closes the alertbox window
     */

    @FXML
    public void closeAlertWindow (){

        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
