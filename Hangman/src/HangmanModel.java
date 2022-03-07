import java.util.ArrayList;
import java.util.LinkedList;
/**
 * The interface for Hangman model class
 */
public interface HangmanModel {

  /**
   * Initialize a game.
   */
  void newGame();

  /**
   * Check whether the player win.
   * @return true if the player win false otherwise.
   */
  boolean win();

  /**
   * Check whether the player lose.
   * @return true if the player lose false otherwise.
   */
  boolean lose();

  /**
   * Check whether the game is over.
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Guess a word or a letter
   * @param str the word or the letter guessed
   */
  void guess(String str);

  /**
   * Get the current word of the hangman game
   * @return the current word of the hangman game
   */
  String getCurrentWord();

  /**
   * Get the guessing word of hangman game.
   * @return the guessing word of hangman game.
   */
  String getGuessingWord();

  /**
   * Get a formatted string representing current state of the game.
   * @return a formatted string representing current state of the game.
   */
  String getGameState();

  /**
   * Check whether the guessed letter or word has been guessed and is correct.
   * @param str the guessed letter or word.
   * @return true if the guessed letter or word has been guessed and is correct false otherwise.
   */
  boolean letterInWrongLetters(String str);

  /**
   * Check whether the guessed letter or word has been guessed and is wrong.
   * @param str the guessed letter or word
   * @return true if the guessed letter or word has been guessed and is wrong false otherwise.
   */
  boolean letterInCorrectLetters(String str);

  /**
   * Get an arraylist of all wrong letters or words that have been guessed.
   * @return an arraylist of all wrong letters or words that have been guessed.
   */
  ArrayList<String> getWrongLetters();

  /**
   * Setting the game over state of this game.
   * @param isGameOver the state to be set.
   */
  void setGameOver(boolean isGameOver);

  /**
   * Get the game over state of this game.
   * @return the game over state of this game.
   */
  boolean getGameOver();
}
