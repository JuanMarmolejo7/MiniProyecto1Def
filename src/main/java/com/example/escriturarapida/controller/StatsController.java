package com.example.escriturarapida.controller;

import com.example.escriturarapida.SceneManager;
import com.example.escriturarapida.model.GameState;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Controller for statistics screen
 * Displays the player's performance after the game
 */

public class StatsController {
    @FXML private Label levelLabel;
    @FXML private Label succesLabel;
    @FXML private Label failLabel;
    @FXML private Label statsLabel;

    private static int successes= 0;
    private static int failures = 0;

    /**
     * Makes possible to set the stats
     * @param s
     * @param f
     */

    public static void setStats(int s, int f){
        successes = s;
        failures = f;
    }

    /**
     *Initialize the stats screen with the latest game results
     */

    public void initialize(){
        levelLabel.setText("Vas en el nivel: " + GameState.currentLevel);
        succesLabel.setText("Has acertado: " + GameState.successfulLevels + "veces");
        failLabel.setText("Fallidas: " + GameState.failedAttempts);

    }


    /**
     * Leads back to the menu when button "menu" is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    private void onMenuClick(javafx.event.ActionEvent event) throws IOException {
        SceneManager.changeScene("Menu.fxml", "Menu Principal");
    }

    /**
     * leads back to the immediate back scene
     * @param event
     * @throws IOException
     */
    @FXML
    private void onReturnClick(javafx.scene.input.MouseEvent event) throws IOException {
        SceneManager.changeScene("Incorrecto.fxml", "Incorrecto");
    }
}
