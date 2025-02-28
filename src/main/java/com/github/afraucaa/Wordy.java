package com.github.afraucaa;

import com.github.afraucaa.wordy.WordyEngine;
import com.github.afraucaa.wordy.WordyPlayer;
import com.github.afraucaa.wordysolvers.UserInputPlayer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Wordy {
    /**
     * Driver program for playing Wordy and testing Wordy solvers.
     * @since 0.1
     * 
     * Loads dictionary from project files. Instantiates game engine.
     * 
     * TODO:
     * <ul>
     *   <li> Add functionality to iterate through game solvers (Java reflection?)
     *   <li> Have the solver(s) play the game, printing sensible output throughout at a human-readable pace?
     * </ul>
     */
    public static void main(String[] args) {
        System.out.println("Initiated Wordy game.");
        Logger logger = Logger.getLogger("MyLogger");

        // Constants
        final int WORD_LENGTH = 5;
        final int maxGuesses = 6;
        final Path basePath = Paths.get("").toAbsolutePath().getParent().getParent().getParent();
        final Path candidateWordsPath = basePath.resolve("resources/dictionary/candidates.txt");
        final Path nonCandidateWordsPath = basePath.resolve("resources/dictionary/non_candidates.txt");

        
        // Populate lists from files
        ArrayList<String> candidateWords;
        ArrayList<String> nonCandidateWords;
        candidateWords = readWordsFromFile(candidateWordsPath);
        nonCandidateWords = readWordsFromFile(nonCandidateWordsPath);

        // Print loaded words for verification
        System.out.println("Candidate Words: " + candidateWords.size() + " words loaded.");
        System.out.println("Non-Candidate Words: " + nonCandidateWords.size() + " words loaded.");

        // Instantiate engine and player
        WordyEngine engine = new WordyEngine(candidateWords, nonCandidateWords, maxGuesses);
        WordyPlayer player = new UserInputPlayer(candidateWords, nonCandidateWords, maxGuesses);
        // To do: design a clever way to select which implementations of WordyPlayer to try
        // Idea: have an editable text file where the user lists all implementations they want to try. The driver would iterate through the file, instantiating each one, and running the tests on it.
        
        /* Play a single game */

        // Set up initial conditions for game
        engine.newGame();
        player.newGame();

        ArrayList<Integer> feedback = new ArrayList(WORD_LENGTH);
        for (int i = 0; i < feedback.size(); i += 1) {
            feedback.set(i, 0);
        }

        while (engine.getGuessesLeft() > 0) {
            String guess = player.newGuess(feedback, engine.getGuessesLeft());
            // TO DO: add guess validation here? At this point, assume guess is legal
            feedback = engine.checkGuess(guess);
            // Print stuff here

        }
    }

    /**
     * Utility function to read the dictionaries from project files
     * 
     * @param Path path to text file where each line in the text is a word.
     * @return ArrayList<String> a list of all the words contained in the file.
     */
    @SuppressWarnings("CallToPrintStackTrace")
    private static ArrayList<String> readWordsFromFile(Path filePath) {
        ArrayList<String> words = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                words.add(line.trim()); // Trim to remove extra spaces
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return words;
    }
}