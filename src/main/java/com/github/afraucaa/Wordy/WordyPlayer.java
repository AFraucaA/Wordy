package com.github.afraucaa.Wordy;

import java.util.List;

public abstract class WordyPlayer {
    protected final List<String> candidateDictionary;
    protected final List<String> nonCandidateDictionary;
    protected final int maxGuesses;

    public WordyPlayer(List<String> candidateDictionary, List<String> nonCandidateDictionary, int maxGuesses) {
        this.candidateDictionary = candidateDictionary;
        this.nonCandidateDictionary = nonCandidateDictionary;
        this.maxGuesses = maxGuesses;
    }

    public abstract String newGuess(List<Integer> lastGuessResult, int guessesLeft);
}
