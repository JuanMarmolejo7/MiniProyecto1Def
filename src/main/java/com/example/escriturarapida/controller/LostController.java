package com.example.escriturarapida.controller;

import com.example.escriturarapida.SceneManager;
import com.example.escriturarapida.model.GameState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Controller for the Game over screen
 * Handles navigation after losing a game
 */

public class LostController {
    /**
     * Make possible to retry the level you lost at
     * @throws IOException
     */
    @FXML
    private void onRetryClick()throws IOException{
        SceneManager.changeScene("Game.fxml","Juego");
    }

    /**
     * Returns to the main menu when the "menu" button is clicked
     * @param event
     * @throws IOException
     */

    @FXML
    private void onMenuClick(ActionEvent event) throws IOException {
        GameState.reset();
        SceneManager.changeScene("Menu.fxml", "Menu Principal");

    }

    /**
     * Leads to a scene that shows your stats
     * @param event
     * @throws IOException
     */
    @FXML
    private void onStatsClick(ActionEvent event) throws IOException {
        SceneManager.changeScene("Stats.fxml", "Stats Principal");
    }

}
