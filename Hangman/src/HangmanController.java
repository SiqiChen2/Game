/**
 * The interface for Hangman controller class
 */
public interface HangmanController {
  /**
   * Initialize the view
   * @param view the view of this game
   */
  void setView(HangmanView view);
  /**
   * Guess a word or a letter
   * @param s the word or the letter to be guessed
   */
  void makeAguess(String s);

  /**
   * End the current game.
   */
  void endGame();

  /**
   * Restart a new game.
   */
  void restartGame();

  /**
   * Exit the hangman game.
   */
  void exitProgram();
}
