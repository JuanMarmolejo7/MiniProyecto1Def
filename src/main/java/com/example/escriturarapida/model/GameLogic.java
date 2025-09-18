package com.example.escriturarapida.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * GameLogic handles the words and timing logic of the typing game.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Load words for the current difficulty (based on level).</li>
 *   <li>Provide a new random word while avoiding recent repeats.</li>
 *   <li>Keep the "current word" and manage a simple timer value.</li>
 * </ul>
 *
 * This class is intentionally small and stateless regarding the current level:
 * the caller passes the level to {@link #loadWordsForLevel(int)} or {@link #resetTime(int)}.
 *
 * @author  Your Name
 * @version 1.0
 * @since   1.0
 */
public class GameLogic {
    private final Random random = new Random();
    private List<String> words = new ArrayList<>();
    private final List<String> lastWords = new ArrayList<>();
    private String currentWord;

    private int timeLeft;
    /**
     * Loads the appropriate word file for the given level.
     * Files are expected at:
     * {@code /com/example/escriturarapida/Palabras/PalabrasFaciles.txt},
     * {@code PalabrasMedias.txt} and {@code PalabrasDificiles.txt}.
     *
     * @param level the current game level (1-based)
     */
    public void loadWordsForLevel(int level) {
        String filename;
        if (level <= 5) {
            filename = "PalabrasFaciles.txt";
        } else if (level <= 10) {
            filename = "PalabrasMedias.txt";
        } else {
            filename = "PalabrasDificiles.txt";
        }
        loadWords(filename);
    }
    /**
     * Read all non-empty lines from the resource file into {@link #words}.
     * If the resource cannot be found this method logs to stderr and leaves {@code words} empty.
     *
     * NOTE: the code tolerates missing files but the caller should check {@link #words} contents
     * (or rely on {@link #pickNewWord()} which will throw if called with no words loaded).
     *
     * @param filename the filename under the resources folder "Palabras"
     */
    private void loadWords(String filename) {
        words.clear();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/com/example/escriturarapida/Palabras/" + filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Picks and returns a new word at random from the loaded list.
     * This method avoids repeating any of the last 5 chosen words (if the pool is large enough).
     *
     * @return the new current word
     * @throws IllegalStateException if no words are loaded (call {@link #loadWordsForLevel(int)} first)
     */
    public String pickNewWord() {
        String newWord;
        do {
            newWord = words.get(random.nextInt(words.size()));
        } while (lastWords.contains(newWord) && words.size() > 5);

        currentWord = newWord;
        lastWords.add(newWord);
        if (lastWords.size() > 5) {
            lastWords.remove(0);
        }
        return currentWord;
    }
    /**
     * Returns the currently selected word.
     *
     * @return the current word, or {@code null} if none has been picked yet
     */
    public String getCurrentWord() {
        return currentWord;
    }
    /**
     * Reset the timer value according to the level rules.
     * Base time is 20 seconds and decreases by 2 every 5 levels down to a minimum of 2.
     *
     * @param level the current level (used to compute reduction)
     */

    public void resetTime(int level) {
        int reduction = (level / 5) * 2;
        timeLeft = Math.max(20 - reduction, 2);
    }
    /**
     * Advance the internal timer by one second (tick).
     *
     * @return the new timeLeft after decrement
     */

    public int tickTime() {
        timeLeft--;
        return timeLeft;
    }
    /**
     * Returns the current remaining time.
     *
     * @return time left in seconds for the current level
     */
    public int getTimeLeft() {
        return timeLeft;
    }
}
