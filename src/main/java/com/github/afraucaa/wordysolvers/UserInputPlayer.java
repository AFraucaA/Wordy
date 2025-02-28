package com.github.afraucaa.wordysolvers;

import com.github.afraucaa.wordy.WordyPlayer;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class UserInputPlayer extends WordyPlayer {
    
    private final int WORD_LENGTH = 5;
    private int currentGuessesLeft;
    private final Scanner userInputScanner;

    private final  TreeSet<String> guessableWords; // For checking that guesses are valid. May not actually need?

    public UserInputPlayer(List candidateWords, List nonCandidateWords, int maxGuesses) {
        super(candidateWords, nonCandidateWords, maxGuesses);
        this.userInputScanner = new Scanner(System.in);

        this.guessableWords = new TreeSet();
        guessableWords.addAll(candidateWords);
        guessableWords.addAll(nonCandidateWords);

        System.out.println("UserInputPlayer constructor called.");
        System.out.print("Human player: input your guesses when prompted.");
    }
    @Override
    public void newGame() {
        this.currentGuessesLeft = this.maxGuesses;
        System.out.println("Human player: a new game has begun. you have " + this.currentGuessesLeft + " guesses.");
    }
    @Override
    public String newGuess(List<Integer> previousFeedback, int GuessesLeft) {
        boolean legalGuess = false;
        String guess = "";
        while(!legalGuess) {
            // Take in guess

            System.out.println("Human player: you have "+ currentGuessesLeft + " guesses left. Enter your next guess.");
            guess = this.userInputScanner.nextLine();
            
            // Check guess is legal
            if (guess.length() != this.WORD_LENGTH) {
                System.out.println("Human player: guess must be " + WORD_LENGTH + " letters long.");
            }
            else if (!guessableWords.contains(guess)) {
                System.out.println("Human player: the word " + guess + " is not a legal guess.");
            }
            else {
                this.currentGuessesLeft -= 1;
                legalGuess = true;
            }
        }
        
        return guess;
    }
}