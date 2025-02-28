package com.github.afraucaa.wordy.solvers;

import com.github.afraucaa.wordy.WordyPlayer;
import java.util.List;

class UserInputPlayer extends WordyPlayer {
    UserInputPlayer(List candidateWords, List nonCandidateWords, int maxGuesses) {
        super(candidateWords, nonCandidateWords, maxGuesses);
        System.out.println("UserInputPlayer constructor called. Does nothing so far.");
    }
    @Override
    public boolean newGame() {
        System.out.println("UserInputPlayer.newGame called. Does nothing so far.");
        return false;
    }
    @Override
    public String newGuess(List<Integer> previousFeedback, int GuessesLeft) {
        System.out.println("UserInputPlayer.newGuess called. Does nothing so far.");
        return "crane";
    }
}