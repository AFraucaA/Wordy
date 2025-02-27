# Wordy
**author** Adrian Frauca

**email** adfrauca@gmail.com

**date of creation** February 27, 2025

**version** 0.1

Personal challenge to create an efficient Wordle solver using some cool math ideas. 

This project is inspired by the Wordle word game. From [Wikipedia](https://en.wikipedia.org/wiki/Wordle):
> Wordle is a web-based word game created and developed by Welsh software engineer Josh Wardle. In the game, players have six attempts to guess a five-letter word, receiving feedback through colored tiles that indicate correct letters and their placement. [...] In January 2022, Wordle was acquired by The New York Times Company for a seven-figure sum and integrated into its games platform the following month.

This project is meant to be open for others to create their own Wordle solvers.

# Challenge/project scope
Eventually I want to make this open so that others can create their own extensions of the `WordyPlayer` abstract class and test them / compare them against each other. We'll see how that goes.

## Assumptions
* All words are 5 letters long.
* There's 6 allowed guesses.

## Guess feedback
Feedback for each guess is packaged in an `ArrayList<int>` object. The Array contains five integers, one for each letter in the guess and in the answer. Each integer can be 0, 1, or 2. If the i-th integer is 0, it means that the i-th letter of the guess is not in the answer word (grey); if it's 1, it means that the i-th letter is in the final answer, but not in that position (yellow); if it's 2, it means that the i-th letter is in the i-th position in the final answer (green).

# Dictionary
The dictionary used for this project is taken from the following [GitHub Gist page](https://gist.github.com/scholtes/94f3c0303ba6a7768b47583aff36654d) by user "Scholtes". According to them, this dictionary of words was used by Wordle by January 27th, 2022, and is now obsolete.

The dictionary is split into two subsets: a set of words that can be guessed and can be the answer word (here contained in `resources\dictionary\candidates.txt`) and a set of words that can be guessed but cannot be the answer word (here contained in `resources\dictionary\non-candidates.txt`). At the time of writing, these files are not meant to be changed. Please don't touch them for now. 

# Classes
Public interface described here.

## Wordy
Driver program for running the game, playing, and or testing game solver algorithms.

Ensures solver inputs are valid.

Ensures number of guesses is respected and that solver and game engine communicate correctly.

### main()
Reads dictionary from project files. Instantiates WordyEngine object.
TODO:
* Add functionality to iterate through game solvers (Java reflection?)
* Have the solver(s) play the game, printing sensible output throughout at a human-readable pace?
* Ensure player and engine input/output is valid
* Ensure integrity of dictionary

## WordyEngine

### WordyEngine(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses, int seed = null)
* **List<String>** a list containing all candidate words
* **List<String>** a list of all guessable but non-candidate words
* **int** the maximum number of guesses allowed
* **int** a random seed to generate new games. If none provided, will generate its own.

### boolean newGame()
TODO: implement
Initializes a new game. Resetting the number of guesses and the answer word.
* **returns** true if the game was reset successfully, false otherwise.

### Array<int> checkGuess(String guess) throws IllegalArgumentException
TODO: implement
Checks the guess against the true answer and returns feedback as per Wordle specifications, if there's guesses left. Updates the guess ounter. Throws if the guess is not the right length, or if no more guesses are permitted.
* **returns** an array containing information about the guess. See `Wordy Guess Feedback` section.
* **throws** IllegalArgumentException if the guess does not have five letters, as per Wordle specifications, or if there's no guesses left.

### int getLifeCounter()
TODO: implement
* **returns** how many guesses left there are.

### String endGame()
TODO: implement
Reveals the final word and sets the guess counter to 0. Game cannot continue from here.
* **returns** the answer word.

## WordyPlayer
Abstract class that can be extended to create game solvers

### WordyPlayer(List<String> candidateWords, List<String> nonCandidateWords, int maxGuesses)
TODO: implement this (or not?)
Constructor.
* **List<String>** a list containing all candidate words
* **List<String>** a list of all guessable but non-candidate words
* **int** the maximum number of guesses allowed

### boolean newGame()
TODO: implement (or not?)
Resets the state of the solver, informing it that the previous game ended and a new one is about to begin.

### String newGuess(List<int> previousFeedback, int guessesLeft)
TODO: implement (or not?)
TODO: polish this a bit
Abstract class. This is where the magic happens.
* **List<int>** Feedback from last guess. See `Wordy Guess Feedback` section.
* **int** Number of guesses left.
* **returns** a new word to be guessed.

# Current features
* Project description
* Interface for Wordle solvers

# Logging practice
TODO: enforce
* Want to print to terminal all game-related information (i.e the kinds of things you'd see when playing Wordle on the official website)
* Want to send to log any additional technical / warning / diagnostic info. 

# Documentation practice
* Add full specifications to readme whenever a new feature is conceived. Add a "TODO: implement" line to its documentation in `README.md`.
* Add full javadoc comments after implementing each feature in source code. Remove "TODO: implement" line from `README.md`.

# Future features / TODO
* Decide on logging/println philosophy
* Implementation for Wordle engine and game
* Implementation for user-based solver (i.e. let the user play the game and input guesses into the program through the keyboard)
* Implementation for personal Wordle solver(s)
* Some integrity checks to ensure word length consistency throughout
* Features to test the performance of a solver (success rate, guess count distribution, time performance), which generates a random set of words based on a seed.

# Questions
* How to load and test the different solver implementations? Something something Java reflection.
* Do we care to give the solver classes for a means to give up and finish the game early? Prolly not but who knows. 

# Miscellaneous notes
**2025-02-27** Repo created. I should be studying for midterms right now. *~Adrian*
