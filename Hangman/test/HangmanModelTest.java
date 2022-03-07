import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the HangmanModel class
 */
public class HangmanModelTest {
  private HangmanModelImpl model1;
  private HangmanModelImpl model2;
  private HangmanModelImpl model3;

  /**
   * Constructs 3 HangmanModels and initialize 3 HangmanModels
   */
  @Before
  public void setUp(){
    model1 = new HangmanModelImpl("word");
    model2 = new HangmanModelImpl("listener");
    model3 = new HangmanModelImpl();
  }

  /**
   * Test newGame method.
   */
  @Test
  public void testNewGame() {
    String str1 = model3.getGuessingWord();
    model3.newGame();
    assertNotEquals(str1, model3.getGuessingWord());
  }

  /**
   * Test win method.
   */
  @Test
  public void testWin() {
    assertFalse(model1.win());
    model1.guess("x");
    assertFalse(model1.win());
    model1.guess("w");
    model1.guess("O");
    model1.guess("R");
    model1.guess("D");
    assertTrue(model1.win());
    assertFalse(model2.win());
    model2.guess("Z");
    assertFalse(model2.win());
    model2.guess("LISTENER");
    assertTrue(model2.win());
  }

  /**
   * Test lose method.
   */
  @Test
  public void testLose() {
    assertFalse(model1.lose());
    model1.guess("x");
    assertFalse(model1.lose());
    model1.guess("a");
    model1.guess("b");
    model1.guess("c");
    model1.guess("z");
    model1.guess("Q");
    assertTrue(model1.lose());
    assertFalse(model2.lose());
    model2.guess("Z");
    assertFalse(model2.lose());
    model2.guess("a");
    model2.guess("b");
    model2.guess("c");
    model2.guess("z");
    model2.guess("Q");
    assertTrue(model2.lose());
  }

  /**
   * Test isGameOver method.
   */
  @Test
  public void testIsGameOver() {
    assertFalse(model1.isGameOver());
    model1.guess("x");
    assertFalse(model1.isGameOver());
    model1.guess("word");
    assertTrue(model1.isGameOver());

    assertFalse(model2.isGameOver());
    model2.guess("Z");
    assertFalse(model2.isGameOver());
    model2.guess("a");
    model2.guess("b");
    model2.guess("c");
    model2.guess("z");
    model2.guess("Q");
    assertTrue(model2.isGameOver());
  }

  /**
   * Test guess method.
   */
  @Test
  public void testGuess() {
    model1.guess("x");
    assertEquals("____",model1.getCurrentWord());
    model1.guess("r");
    assertEquals("__r_",model1.getCurrentWord());
    model1.guess("word");
    assertEquals("word",model1.getCurrentWord());
  }

  /**
   * Test getCurrentWord method.
   */
  @Test
  public void testGetCurrentWord() {
    assertEquals("____",model1.getCurrentWord());
    model1.guess("a");
    assertEquals("____",model1.getCurrentWord());
    model1.guess("r");
    assertEquals("__r_",model1.getCurrentWord());
  }

  /**
   * Test getGameState method.
   */
  @Test
  public void testGetGameState() {
    model1.guess("x");
    assertEquals("The game continues.\nThe incorrect letters/words you have guessed: x ",
        model1.getGameState());
    model1.guess("o");
    assertEquals("The game continues.\nThe incorrect letters/words you have guessed: x ",
        model1.getGameState());
    model1.guess("word");
    assertEquals("Congratulations! You win!", model1.getGameState());
    model2.guess("a");
    model2.guess("b");
    assertEquals("The game continues.\nThe incorrect letters/words you have guessed: a b ",
        model2.getGameState());
    model2.guess("c");
    model2.guess("d");
    model2.guess("m");
    assertEquals("The game continues.\nThe incorrect letters/words you have guessed: a b c d m ",
        model2.getGameState());
    model2.guess("x");
    assertEquals("You have lost!",model2.getGameState());
  }

  /**
   * Test letterInWrongLetters method.
   */
  @Test
  public void testLetterInWrongLetters() {
    model1.guess("a");
    model1.guess("b");
    assertTrue(model1.letterInWrongLetters("a"));
    assertFalse(model1.letterInWrongLetters("x"));
    model2.guess("ox");
    model2.guess("z");
    assertFalse(model2.letterInWrongLetters("k"));
    assertTrue(model2.letterInWrongLetters("ox"));
  }

  /**
   * Test letterInCorrectLetter method.
   */
  @Test
  public void testLetterInCorrectLetters() {
    model1.guess("o");
    model1.guess("r");
    model1.guess("d");
    assertTrue(model1.letterInCorrectLetters("o"));
    assertFalse(model1.letterInCorrectLetters("a"));
    model2.guess("l");
    model2.guess("i");
    model2.guess("s");
    assertTrue(model2.letterInCorrectLetters("l"));
    assertTrue(model2.letterInCorrectLetters("i"));
    assertFalse(model2.letterInCorrectLetters("o"));
  }

  /**
   * Test getWrongLetters method.
   */
  @Test
  public void testGetWrongLetters() {
    model1.guess("x");
    model1.guess("y");
    model1.guess("z");
    ArrayList<String> list1= new ArrayList<>();
    list1.add("x");
    list1.add("y");
    list1.add("z");
    assertTrue(model1.getWrongLetters().equals(list1));
    model2.guess("word");
    model2.guess("p");
    model2.guess("q");
    ArrayList<String> list2= new ArrayList<>();
    list2.add("word");
    list2.add("p");
    list2.add("q");
    assertTrue(model2.getWrongLetters().equals(list2));

  }

  /**
   * Test setGameOver method.
   */
  @Test
  public void testSetGameOver() {
    model1.setGameOver(true);
    assertTrue(model1.getGameOver());
    model1.setGameOver(false);
    assertFalse(model1.getGameOver());
    model3.setGameOver(true);
    assertTrue(model3.getGameOver());
    model3.setGameOver(false);
    assertFalse(model3.getGameOver());
  }

  /**
   * Test getGuessingWord method.
   */
  @Test
  public void testGetGuessingWord() {
    assertEquals("word",model1.getGuessingWord());
    assertEquals("listener",model2.getGuessingWord());
  }

  /**
   * Test getGameOver method.
   */
  @Test
  public void testGetGameOver(){
    assertFalse(model1.getGameOver());
    model1.guess("word");
    assertTrue(model1.getGameOver());
    assertFalse(model2.getGameOver());
    model2.guess("c");
    model2.guess("d");
    model2.guess("m");
    model2.guess("a");
    model2.guess("b");
    model2.guess("k");
    assertTrue(model2.getGameOver());
  }
}