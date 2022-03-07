/**
 * The interface for Hangman view class
 */
public interface HangmanView
{

  /**
   * Change the current word label content to the given string.
   * @param s the current word
   */
  void setCurrentWord(String s);

  /**
   * Change the current state label content to the given string
   * @param s the current state
   */
  void setCurrentState(String s);

  /**
   * Set the label to the given picture
   * @param i the given picture's number
   */
  void setPicture(int i);

  /**
   * End the current game and show the given string as answer.
   * @param s the answer of the game
   */
  void endGame(String s);

  /**
   * Restart a new game with given current word.
   * @param s the given current word.
   */
  void setNewGame(String s);

  /**
   * Clear all the input in the text field
   */
  void clearInputString();

  /**
   * Adding the events of the components
   * @param features the events that to be added
   */
  void addFeatures(HangmanController features);

  /**
   * Show the prompt to give a warning
   * @param isGameOver whether the game is over
   * @param duplicateCorrectLetter whether there is a duplicate correct letter
   * @param duplicateWrongLetter whether there is a duplicate wrong letter
   */
  void setPrompt(boolean isGameOver, boolean duplicateCorrectLetter, boolean duplicateWrongLetter);

}
