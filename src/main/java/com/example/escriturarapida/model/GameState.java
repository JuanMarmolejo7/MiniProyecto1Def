package com.example.escriturarapida.model;

/**
 * Holds the global game state across different scenes
 * Stores current level, number of successful levels and failed attempts
 */

public class GameState {
    public static int currentLevel = 1;
    public static int successfulLevels = 0;
    public static int failedAttempts = 0;

    /**
     * Reset the game state to its initial value
     */
    public static void reset(){
        currentLevel = 1;
        successfulLevels = 0;
        failedAttempts = 0;

    }

}
