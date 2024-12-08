package com.learner.controllers.questions;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.learner.controllers.GameOutroController;
import com.learner.game.App;
import com.learner.model.Facade;
import com.learner.model.questions.MultipleChoiceQuestion;
import com.learner.narration.Narrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MultipleChoiceQuestionController implements Initializable {

    private final Facade facade = Facade.getInstance();
    private MultipleChoiceQuestion currentQuestion;
    private final boolean spokenFeedback = facade.getCurrentUser().getReadQuestionFeedbackAloud();
    private String selectedAnswer; 

    @FXML
    private ImageView audioButton;

    @FXML
    private HBox hboxForChoiceButtons;

    @FXML
    private Button nextButton;

    @FXML
    private Label questionText;

    @FXML
    private Text questionTypeText;

    @FXML
    private Button submitButton;

    @FXML
    private Label title;

    @FXML
    private ImageView exitButton;

    private Button currentlyHighlightedButton;

    @FXML
    private void goToMain(MouseEvent event) throws IOException {
        App.setRoot("main");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(facade.getCurrentGame().getGameTitle());
        loadQuestion();
    }

    private void loadQuestion() {
        currentQuestion = (MultipleChoiceQuestion) facade.getQuizQuestion();
        
        if (currentQuestion != null) {
            questionText.setText(currentQuestion.getQuestionText());
            ArrayList<String> options = currentQuestion.getOptions();
    
            questionTypeText.setText(options.size() == 2 ? "True/False" : "Multiple Choice");
            
            // hboxForChoiceButtons.getChildren().clear(); // Clear previous buttons
    
            for (String option : options) {
                Button optionButton = new Button(option);
    
                // Assign event handler for selection
                optionButton.setOnAction(event -> handleOptionSelection(option));
    
                // Add button to the UI
                hboxForChoiceButtons.getChildren().add(optionButton);
            }
        }
    }
    
    @FXML
    private void submitQuestion(ActionEvent event) throws IOException {

        if(submitButton.getText().equals("Continue")) { // Go to next
            continueButton();
        } else if (submitButton.getText().equals("Reveal Answer")) { // If user gets question wrong
            revealAnswer();
        } else { // Default first use of the button
            submitButton();
        }
    }

    private void submitButton() {
        if (currentQuestion != null && selectedAnswer != null) {
            // boolean isCorrect = currentQuestion.validateAnswer(selectedAnswer); // incorrect use
            boolean isCorrect = facade.validateQuizAnswer(selectedAnswer);
    
            // Change button colors based on correctness
            hboxForChoiceButtons.getChildren().forEach(node -> {
                if (node instanceof Button) {
                    Button btn = (Button) node;
                    String answerText = btn.getText();
    
                    // Check / compare button to answers 
                    if (answerText.equals(currentQuestion.getCorrectAnswer()) && answerText.equals(selectedAnswer)) {
                        btn.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Correct answer
                        if(spokenFeedback) Narrator.playSound("Correct! Well done.");
                    } else if (answerText.equals(selectedAnswer)) {
                        btn.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Incorrect selected answer
                        if(spokenFeedback) Narrator.playSound("Incorrect! Better luck next time.");
                    } else {
                        btn.setStyle(""); // Reset style for other buttons
                    }

                    // Disable the button after submission
                    btn.setDisable(true); // Disable the button so it can't be clicked again
                }
            });

            if(isCorrect) {
                submitButton.setText("Continue");
            } else {
                submitButton.setText("Reveal Answer");
            }
        }
    }

    private void revealAnswer() {

        hboxForChoiceButtons.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button btn = (Button) node;
                String answerText = btn.getText();

                // Check / compare button to answers 
                if (answerText.equals(currentQuestion.getCorrectAnswer())) {
                    btn.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Correct answer
                    
                } else {
                    btn.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Incorrect answer
                    
                } 
            }
        });

        submitButton.setText("Continue");
    }

    private void continueButton() throws IOException {
        GameOutroController.directQuestion(facade.getNextQuizQuestion());
    }
    
    private void handleOptionSelection(String option) {
        selectedAnswer = option; // Store the selected answer
    }

    @FXML
    private void playAudio(MouseEvent event) {
        Narrator.playSound(questionText.getText());
    }
    
    @FXML
    private void goToNext(ActionEvent event) throws IOException {
        // Navigate to the next part of the application (e.g., results or another question type)
        System.out.println("Next button clicked.");
    }

    @FXML
    private void handleOptionSelection(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        // Reset the style of the previously highlighted button
        if (currentlyHighlightedButton != null) {
            currentlyHighlightedButton.setStyle(""); // Reset to default style
        }

        // Highlight the clicked button
        clickedButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-border-color: #FFD700; -fx-background-radius: 10px; -fx-padding: 10px 20px; -fx-font-size: 14pt; -fx-font-weight: bold; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0); -fx-transition: all 0.3s ease;");
        currentlyHighlightedButton = clickedButton;
    }

}
