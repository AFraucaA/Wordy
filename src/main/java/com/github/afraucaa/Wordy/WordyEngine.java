package com.github.afraucaa.wordy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class WordyEngine {
    private final  ArrayList<String> candidateWords; // For generating answer words
    private final  TreeSet<String> guessableWords; // For checking that guesses are valid. May not actually need?
    private final int maxGuesses;
    private final Random random;
    private final int WORD_LENGTH = 5;

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
        this.candidateWords = new ArrayList<>(candidateWords);
        this.guessableWords = new TreeSet();
        guessableWords.addAll(candidateWords);
        guessableWords.addAll(nonCandidateWords);
        this.maxGuesses = maxGuesses;
        this.random = new Random(seed); 


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
        this(candidateWords, nonCandidateWords, maxGuesses, System.currentTimeMillis());
    }
    
    /**
     * Begins a new game. Resets the answer word and the number of guesses left.
     */
    public void newGame() {
        currentGuessesLeft = maxGuesses;
        int randomIndex = this.random.nextInt(candidateWords.size());
        currentWord = candidateWords.get(randomIndex);
        System.out.println("New game begun. You have " + currentGuessesLeft + "guesses.");
    }
    
    /**
     * Compares the given word against the true answer and returns feedback, as per Wordle specifications.
     * Updates the guess counter if a legal guess was made.
     * Quietly calls the endGame() method if guess matches answer word exactly. Shouldn't make any difference though.
     * @param String a word guess
     * @return List<Integer> specifying the information about letter matches. See `Guess feedback` section on readme.
     * @throws IllegalArgumentException if no guesses can be made, or if guess word is illegal.
     */
    public ArrayList<Integer> checkGuess(String guess) throws IllegalArgumentException {
        // Check that guess is legal
        if (guess.length() != WORD_LENGTH) {
            throw new IllegalArgumentException("Guess must be " + WORD_LENGTH + "letters long. A guess with " + guess.length() + " characters was passed in.");
        }
        if (!guessableWords.contains(guess)) {
            throw new IllegalArgumentException("Guess is not in the dictionary of legal words.");
        }
        if (currentGuessesLeft <= 0) {
            throw new IllegalArgumentException("Player has no guesses left. Guess counter is " + currentGuessesLeft);
        }
        
        //Feedback
        /* guessFeedback key
         *  value  meaning
         *   -1    not yet evaluated
         *    0    letter not in word
         *    1    letter in word, wrong placement
         *    2    letter in word, right placement
         */
        /* answerMatches key
         *  value  meaning
         *    0    letter not yet matched to any letters in guess
         *    1    letter matched to a letter in guess
         */
        ArrayList guessFeedback = new ArrayList<Integer>(WORD_LENGTH); // Status of each letter in the guess (no match, partial match, perfect match with answer)
        ArrayList answerMatches = new ArrayList<Integer>(WORD_LENGTH); // Status of each letter in the correct answer (no match or match with some letter in the guess)
        //Initialize both arrays
        for (int i = 0; i < WORD_LENGTH; i+=1) {
            guessFeedback.set(i, -1);
            answerMatches.set(i, 0);
        }
        // First, check for exact matches.
        for (int i = 0; i < WORD_LENGTH; i+=1) {
            if (guess.charAt(i) == (currentWord.charAt(i))) {
                guessFeedback.set(i, 2);
                answerMatches.set(i, 1);
            }
        }
        // Now every exact match is accounted for
        // Now, check for partial matches, making sure to only count matches with letters in answer that haven't previously been matched
        for (int i = 0; i <WORD_LENGTH; i+=1) {
            if (!guessFeedback.get(i).equals(2)) {
                // Hasn't been matched to anything yet
                // check whether it matches to any letter in the true word that hasn't been matched yet
                for (int j = 0; j < WORD_LENGTH; j+=1) {
                    if ((guess.charAt(i) == currentWord.charAt(j)) 
                     && (answerMatches.get(j).equals(0))) {
                        answerMatches.set(j, 1);
                        guessFeedback.set(i, 1);
                    }
                }
            }
        }


        currentGuessesLeft -= 1;
        if (guess.equals(currentWord)) {
            //TODO: add logging here and some other places ig
            endGame();
        }
        System.out.println("Checking guess "+ guess+ ". Does nothing yet. Guess counter is now " +currentGuessesLeft);
        return guessFeedback;
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