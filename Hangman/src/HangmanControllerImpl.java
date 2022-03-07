import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a hangman controller implementation which has a
 * model and a view.
 */
public class HangmanControllerImpl implements HangmanController {
  private HangmanModel model;
  private HangmanView view;

  /**
   * Initialize the model of this game.
   * @param model the model of this game
   */
  public HangmanControllerImpl(HangmanModel model){
    this.model = model;
  }

  @Override
  public void setView(HangmanView view){
    this.view = view;
    this.view.addFeatures(this);
    this.view.setCurrentWord(this.model.getCurrentWord());
  }


  @Override
  public void makeAguess(String s) {
    s = s.toLowerCase();
    if(model.isGameOver()||model.letterInCorrectLetters(s)||
        model.letterInWrongLetters(s)){
      view.setPrompt(model.isGameOver(),
          model.letterInCorrectLetters(s),
          model.letterInWrongLetters(s));
      return;
    }
    Pattern nonLetter = Pattern.compile("([^a-z]+)");
    Matcher matcher = nonLetter.matcher(s);
    if(matcher.find()){
      view.setPrompt(false,false,false);
      return;
    }
    model.guess(s);
    String str1 = model.getCurrentWord();
    view.clearInputString();
    view.setCurrentWord(str1);
    String str2 = model.getGameState();
    view.setCurrentState(str2);
    int i = model.getWrongLetters().size();
    view.setPicture(i);
  }


  @Override
  public void endGame() {
    String word = this.model.getGuessingWord();
    view.endGame(word);
    model.setGameOver(true);
  }

  @Override
  public void restartGame() {
    model.newGame();
    String currentWord = model.getCurrentWord();
    view.setNewGame(currentWord);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

}
