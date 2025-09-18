package com.example.escriturarapida;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class to manage scene changes in the project
 */
public class SceneManager {
    private static Stage stage;

    /**
     * Sets the primary stage for the application
     * @param primaryStage
     */
    public static void setStage(Stage primaryStage) {

        stage = primaryStage;
    }

    /**
     * Changes the current scene to the specified FXML file
     * @param fxmlFile
     * @param title
     * @throws IOException
     */
    // Versión extendida: carga el archivo FXML y asigna título a la ventana
    public static void changeScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/escriturarapida/" + fxmlFile));
        Scene scene = new Scene(loader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
