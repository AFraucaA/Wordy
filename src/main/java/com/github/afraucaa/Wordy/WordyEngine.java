package com.github.afraucaa.Wordy;

import java.util.ArrayList;
import java.util.List;

class WordyEngine {
    private final  List<String> candidateWords;
    private final  List<String> nonCandidateWords;
    private final int maxGuesses;
    private final long seed;
    private static long MAX_SEED = 1000000;
    private static int WORD_LENGTH = 5;

    private int currentGuessesLeft;
    private String currentWord;
    /**
     * In-depth constructor.
     * @param List<String> a list of all the words that can be guessed and can be the answer word
     * @param List<String> a list of all the words that can be guessed and cannot be the answer word.
     * @param int the maximum allowed number of guesses
     * @param long a seed for generating the answer words for the duration of the object's existence
     */
    public WordyEngine(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses, long seed) {
        this.candidateWords = candidateWords;
        this.nonCandidateWords = nonCandidateWords;
        this.maxGuesses = maxGuesses;
        this.seed = seed % MAX_SEED;

        this.currentGuessesLeft = 0;
        this.currentWord = "";
        System.out.println("Initiated Wordy game engine. Seed is " + seed + ".");
    }
    /**
     * General case constructor. Will generate a pseudorandom seed for answer word generation.
     * @param List<String> a list of all the words that can be guessed and can be the answer word
     * @param List<String> a list of all the words that can be guessed and cannot be the answer word.
     * @param int the maximum allowed number of guesses
     */ 
    public WordyEngine(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses) {
        this(candidateWords, nonCandidateWords, maxGuesses, System.currentTimeMillis() % MAX_SEED);
    }
    
    public boolean newGame() {
        System.out.println("New game begun. Does nothing yet.");
        return false;
    }
    /**
     * Quietly calls the endGame() method if guess matches answer word exactly. Shouldn't make any difference though.
     */
    public List<Integer> checkGuess(String guess) throws IllegalArgumentException {
        if (!(Integer.valueOf(guess.length()).equals(WORD_LENGTH))) {
            throw new IllegalArgumentException("Guess must be " + WORD_LENGTH + "letters long. A guess with " + guess.size() + " characters was passed in.");
        }
        if (currentGuessesLeft <= 0) {
            throw new IllegalArgumentException("Player has no guesses left. Guess counter is " + currentGuessesLeft);
        }
        ArrayList<Integer> feedback = new ArrayList<>();
        //TODO: check letter by letter and stuff
        currentGuessesLeft -= 1;
        if (guess.equals(currentWord)) {
            //TODO: add logging here and some other places ig
            endGame();
        }
        System.out.println("Checking guess "+ guess+ ". Does nothing yet. Guess counter is now " +currentGuessesLeft);
        return feedback;
    }
    /**
     * Getter function for how many guesses left there are.
     * @return int how many guesses left to find out the current word. 
     */
    public int getGuessesLeft() {
        return currentGuessesLeft;
    }

    /**
     * Returns the answer word. Sets the engine to not accept any more guesses after this. Must be reset via the newGame() method to begin a new game.
     * Will be quietly called by the checkGuess function if a guess matches the answer word. It's okay for it to be called again by the driver class. 
     * @return String the answer word
     */
    public String endGame() {
        currentGuessesLeft = 0;
        return currentWord;
    }
}