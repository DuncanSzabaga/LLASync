package com.learner.game;

import java.io.IOException;

import com.learner.model.Facade;
import com.learner.narration.Narrator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(getClass().getResourceAsStream("/com/learner/game/fxml-images/logo.png"));
        stage.getIcons().add(icon); 
        stage.setTitle(" HelloLanguage");
        stage.setResizable(false); // Make windows non-resizable
        scene = new Scene(loadFXML("home"), 720, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Facade.getInstance().loadData();
        Narrator.playSound("");
        launch();
    }

}