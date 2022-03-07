
/**
 * This class is in charge of running the hangman game.
 */
public class Driver {
  public static void main(String[] args) {
    HangmanModel model = new HangmanModelImpl();
    HangmanView view  = new HangmanJFrameView("HANGMAN");
    HangmanControllerImpl controller= new HangmanControllerImpl(model);
    controller.setView(view);
  }
}
