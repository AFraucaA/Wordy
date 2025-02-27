# Wordy
**author** Adrian Frauca

**email** adfrauca@gmail.com

**date of creation** February 27, 2025

**version** 0.1

Personal challenge to create an efficient Wordle solver using some cool math ideas. 

This project is inspired by the Wordle word game. From [Wikipedia](https://en.wikipedia.org/wiki/Wordle):
> Wordle is a web-based word game created and developed by Welsh software engineer Josh Wardle. In the game, players have six attempts to guess a five-letter word, receiving feedback through colored tiles that indicate correct letters and their placement. [...] In January 2022, Wordle was acquired by The New York Times Company for a seven-figure sum and integrated into its games platform the following month.

This project is meant to be open for others to create their own Wordle solvers.

# Interfaces

# Classes

# Dictionary
The dictionary used for this project is taken from the following [GitHub Gist page](https://gist.github.com/scholtes/94f3c0303ba6a7768b47583aff36654d) by user "Scholtes". According to them, this dictionary of words was used by Wordle by January 27th, 2022, and is now obsolete.

The dictionary is split into two subsets: a set of words that can be guessed and can be the answer word (here contained in `resources\dictionary\candidates.txt`) and a set of words that can be guessed but cannot be the answer word (here contained in `resources\dictionary\non-candidates.txt`)

# Current features
* Project description

# Future features
* Interface for Wordle solvers
* Implementation for Wordle engine and game
* Implementation for personal Wordle solver(s)
* A test suite to test the performance of a solver (success rate, guess count distribution, time performance), which generates a random set of words based on a seed.

# Miscellaneous notes
**2025-02-27** I should be studying for midterms right now. *~Adrian*
