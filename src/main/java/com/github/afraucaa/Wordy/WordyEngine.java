package com.github.afraucaa.Wordy;

import java.util.List;

class WordyEngine {
    private final  List<String> candidateWords;
    private final  List<String> nonCandidateWords;
    private final int maxGuesses;
    private final long seed;
    private static long MAX_SEED = 1000000;
    public WordyEngine(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses, long seed) {
        this.candidateWords = candidateWords;
        this.nonCandidateWords = nonCandidateWords;
        this.seed = seed % MAX_SEED;
        System.out.println("Initiated Wordy game engine. Seed is " + seed + ".");
    }
    public WordyEngine(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses) {
        this(candidateWords, nonCandidateWords, maxGuesses, System.currentTimeMillis() % MAX_SEED);
    }
    public boolean newGame() {
        System.out.println("New game begun. Does nothing yet.");
        return false;
    }

}