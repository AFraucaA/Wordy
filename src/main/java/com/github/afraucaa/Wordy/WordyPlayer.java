package com.github.afraucaa.wordy;

import java.util.List;

public abstract class WordyPlayer {
    protected final List<String> candidateWords;
    protected final List<String> nonCandidateWords;
    protected final int maxGuesses;

    public WordyPlayer(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses) {
        this.candidateWords = candidateWords;
        this.nonCandidateWords = nonCandidateWords;
        this.maxGuesses = maxGuesses;
    }

    public abstract String newGuess(List<Integer> lastGuessResult, int guessesLeft);
    public abstract boolean newGame();
}
