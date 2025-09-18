package com.example.escriturarapida;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Entry point of the JavaFX application
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager.changeScene("Menu.fxml", "Escritura Rápida");
        stage.setResizable(false);
    }

    /**
     * Main entry point of the program.
     * @param args
     */

    public static void main(String[] args) {
        launch();
    }
}
