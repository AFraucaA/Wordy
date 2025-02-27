package com.github.afraucaa.Wordy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


class Wordy {
    public static void main(String[] args) {
        // Constants
        final int maxGuesses = 6;
        final Path candidateWordsPath = Paths.get("resources/dictionary/candidates.txt");
        final Path nonCandidateWordsPath = Paths.get("resources/dictionary/non_candidates.txt");


        System.out.println("Initiated Wordy game.");
        Logger logger = Logger.getLogger("MyLogger");
        WordyEngine engine = new WordyEngine();

        // To do: design a clever way to select which implementations of WordyPlayer to try
        // Idea: have an editable text file where the user lists all implementations they want to try. The driver would iterate through the file, instantiating each one, and running the tests on it.
         
    }
}