package com.example.escriturarapida.controller;

import com.example.escriturarapida.SceneManager;
import com.example.escriturarapida.model.GameState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Controller for the main menu scene
 * Handles navigation to the game scene
 * Handles navigation to the help scene
 * Help on return button functionalities
 */

public class MenuController {
    /**
     * Start the game when the button that says "JUGAR" is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    private void onPlayClick(ActionEvent event) throws IOException {
        GameState.reset();
        SceneManager.changeScene("Game.fxml", "Nivel 1");
    }

    /**
     * Leads to a scene with instructions
     * @param event
     * @throws IOException
     */
    @FXML
    private void onHelpClick(ActionEvent event) throws IOException {
        SceneManager.changeScene("Ayuda.fxml", "Ayuda");
    }

    /**
     *Helps on button return functionalities, make possible to go to the immediate back scene
     * @param event
     * @throws IOException
     */
    @FXML
    private void onReturnClick(javafx.scene.input.MouseEvent event) throws IOException {
        SceneManager.changeScene("Menu.fxml", "Menú Principal");
    }

}
