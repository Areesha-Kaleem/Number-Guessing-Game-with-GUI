# Number-Guessing-Game-with-GUI
Number Guessing Game with GUI
This project is a graphical implementation of a number guessing game using Java Swing. The game generates a random number between 0 and 1000, and the player has 10 attempts to guess the number. The GUI provides feedback on whether the guess is too high or too low and keeps track of the number of guesses left. If the player guesses the number correctly or runs out of guesses, the game resets for another round.

Project Structure
The project consists of two main classes:

GuessNumber: Handles the game logic.
GuessNumberGUI: Provides the graphical user interface for the game.
GuessNumber Class
This class manages the game's state, including:

The number of guesses left.
The list of guessed numbers.
The randomly generated target number.
A warning system for invalid guesses.
GuessNumberGUI Class
This class extends JFrame and creates the GUI for the game. It includes:

A background panel with a custom image.
Labels to display messages and the number of guesses left.
A text field for the user to input guesses.
Buttons for submitting guesses and closing the game.
How to Run
To run the game, compile and execute the GuessNumberGUI class. The GUI window will appear, and you can start playing by entering guesses in the text field and clicking the "Guess" button.
