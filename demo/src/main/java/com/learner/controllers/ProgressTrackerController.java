package com.learner.controllers;

import java.io.IOException;
import java.util.UUID;

import com.learner.game.App;
import com.learner.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ProgressTrackerController {

    private Facade facade = Facade.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private ProgressBar gamesCompletedBar;

    @FXML
    public void initialize() {
        updateProgressBar();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    private void updateProgressBar() {
        int totalGames = facade.getCurrentTextObjectIndex();
        int completedGames = facade.getTotalNumberOfCompletedGames();
        double progress = 0.0;
        if (completedGames != 0) {
            progress = (double) totalGames / (double) completedGames;
        }
        System.out.println("Progress: " + progress);
        gamesCompletedBar.setProgress(progress);
    }
}
