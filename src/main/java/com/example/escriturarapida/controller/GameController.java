package com.example.escriturarapida.controller;

import com.example.escriturarapida.SceneManager;
import com.example.escriturarapida.model.GameLogic;
import com.example.escriturarapida.model.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for the game screen
 * Handles word generation, level progression,timing and user input
 */

public class GameController {
    @FXML private ImageView pauseButton;
    @FXML private Label levelLabel;
    @FXML private Label timerLabel;
    @FXML private Label wordLabel;
    @FXML private TextField inputField;
    @FXML private Label feedbackLabel;

    private boolean isPaused = false;
    private Timeline timeline;
    private final GameLogic logic = new GameLogic();

    /**
     * Initializes the game screen when loaded
     * Loads words for the current level and starts the first round
     */
    @FXML
    public void initialize() {

        startLevel();
    }

    /**
     * Starts the level, resetting all the things changed on the latest game
     */

    private void startLevel() {
        logic.loadWordsForLevel(GameState.currentLevel);
        String newWord = logic.pickNewWord();
        wordLabel.setText(newWord);

        inputField.clear();
        feedbackLabel.setText("");
        feedbackLabel.setStyle("");
        levelLabel.setText(String.valueOf(GameState.currentLevel));

        logic.resetTime(GameState.currentLevel);
        timerLabel.setText(String.valueOf(logic.getTimeLeft()));

        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            int time = logic.tickTime();
            timerLabel.setText(String.valueOf(time));
            if (time <= 0 && !isPaused) {
                timeline.stop();
                endGame();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Pauses or resumes the game when the pause button is clicked
     * @param event
     */
    @FXML
    private void onPauseClick(javafx.scene.input.MouseEvent event) {
        if (!isPaused) {
            isPaused = true;
            if (timeline != null) timeline.pause();
            inputField.setDisable(true);
            feedbackLabel.setText("Juego en pausa");
            feedbackLabel.setStyle("-fx-text-fill:yellow");
        } else {
            isPaused = false;
            if (timeline != null) timeline.play();
            inputField.setDisable(false);
            feedbackLabel.setText("");
        }
    }

    /**
     * Handles user input validation
     * Compares the typed word by the user with the expected word
     */
    @FXML
    private void handleInput() {
        if (isPaused) return;
        if (inputField.getText().equals(logic.getCurrentWord())) {
            GameState.successfulLevels++;
            GameState.currentLevel++;
            startLevel();
        } else {
            GameState.failedAttempts++;
            feedbackLabel.setText("Incorrecto");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            inputField.clear();
        }
    }

    /**
     * Ends the game and leads to other scene
     */
    private void endGame() {
        if (timeline != null) timeline.stop();
        try {
            SceneManager.changeScene("Incorrecto.fxml", "Game Over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns to the immediate back screen, in this case main menu
     * @param event
     * @throws IOException
     */
    @FXML
    private void onReturnClick(javafx.scene.input.MouseEvent event) throws IOException {
        SceneManager.changeScene("Menu.fxml", "Menú Principal");
        GameState.reset();
    }
}
