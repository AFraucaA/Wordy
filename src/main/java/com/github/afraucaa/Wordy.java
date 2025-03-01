package com.github.afraucaa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.afraucaa.wordy.WordyEngine;
import com.github.afraucaa.wordy.WordyPlayer;
import com.github.afraucaa.wordysolvers.UserInputPlayer;

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
public class Wordy {
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

        boolean correctGuess = false;
        while (!correctGuess && engine.getGuessesLeft() > 0) {
            String guess = player.newGuess(feedback, engine.getGuessesLeft());
            // TO DO: add guess validation here? At this point, assume guess is legal
            feedback = engine.checkGuess(guess);
            // Print stuff here
            System.out.println("\t"+guess);
            System.out.print('\t');
            for (int i = 0; i < WORD_LENGTH; i+=1) {
                System.out.print(
                    switch (feedback.get(i)) {
                    case 2 -> 'X';
                    case 1 -> 'x';
                    default -> ' ';
                });
            }
            System.out.print('\n');
            //Check whether the guess is correct
            correctGuess = true;
            for (int i = 0; i < WORD_LENGTH; i+=1) {
                if (feedback.get(i) != 2) {
                    correctGuess = false;
                }
            }
        }
        //Game finishes
        String answer = engine.endGame();
        System.out.println("Game over.");
        if (correctGuess) {
            System.out.println("Congrats! You guessed the right word!");
        }
        else {
            System.out.println("You ran out of guesses.");
        }
        System.out.println("The answer was:\n\t" + answer + "\nThanks for playing!");
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