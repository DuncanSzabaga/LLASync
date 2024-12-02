package com.learner.controllers;

import java.io.IOException;

import com.learner.game.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button playButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button progressButton;

    @FXML
    private Button settingsButton;

    @FXML
    public void goToPlay(ActionEvent event) throws IOException {
        App.setRoot("setLangAndDiff");
    }

    @FXML
    public void goToProgress(ActionEvent event) {

    }

    @FXML
    public void goToSettings(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    public void logoutToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void makeOptionsVisible(ActionEvent event) throws IOException{
        logoutButton.setVisible(!logoutButton.isVisible());
        settingsButton.setVisible(!settingsButton.isVisible());
    }

}
