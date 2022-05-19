
import static org.junit.Assert.*;

import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the MarbleSolitaireModelImpl class
 */
public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModelImpl model1;
  private MarbleSolitaireModelImpl model2;
  private MarbleSolitaireModelImpl model3;
  private MarbleSolitaireModelImpl model4;
  private MarbleSolitaireModelImpl model5;
  private MarbleSolitaireModelImpl model6;
  private MarbleSolitaireModelImpl model7;
  private MarbleSolitaireModelImpl model8;
  private MarbleSolitaireModelImpl model9;
  private MarbleSolitaireModelImpl model10;

  /**
   * Constructs 4 MarbleSolitaireModelImpl and initialize 4 MarbleSolitaireModelImpl
   */
  @Before
  public void setUp() {
    model1 = new MarbleSolitaireModelImpl();
    model2 = new MarbleSolitaireModelImpl(2,3);
    model3 = new MarbleSolitaireModelImpl(5);
    model4 = new MarbleSolitaireModelImpl(3,4,4);
  }

  /**
   * Test exception for constructor if the empty position is out of the board
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoardForArm3(){
    model5 = new MarbleSolitaireModelImpl(-1,0);
  }

  /**
   * Test exception for constructor if the empty position is out of the board
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoardForArm5(){
    model6 = new MarbleSolitaireModelImpl(5,8,13);
  }

  /**
   * Test exception for constructor if the empty position is invalid
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition(){
    model7 = new MarbleSolitaireModelImpl(1,1);
  }

  /**
   * Test exception for constructor if the arm is an even
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEvenArm(){
    model8 = new MarbleSolitaireModelImpl(4);
  }

  /**
   * Test exception for constructor if the arm is less than 3
   */
  @Test(expected = IllegalArgumentException.class)
  public void testArm1(){
    model9 = new MarbleSolitaireModelImpl(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeArm(){
    model10 = new MarbleSolitaireModelImpl(-5);
  }
  /**
   * Test move method
   */
  @Test
  public void testMove() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    model1.move(1,4,3,4);
    model1.move(4,4,2,4);
    model3.move(5,3,5,5);
    model3.move(5,6,5,4);
  }

  /**
   * Test exception for move method if the from position is out of board
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromOutOfBoard(){
    model1.move(7,1,5,1);
  }
  /**
   * Test exception for move method if the to position is out of board
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToOutOfBoard(){
    model1.move(5,1,7,1);
  }

  /**
   * Test exception for move method if the from position is invalid
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromInvalidPosition(){
    model1.move(1,1,3,1);
  }

  /**
   * Test exception for move method if the to position is invalid
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToInvalidPosition(){
    model1.move(3,1,1,1);
  }
  /**
   * Test exception for move method if the move is invalid
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveInvalid(){
    model1.move(3,6,3,3);
  }

  /**
   * Test isGameOver method
   */
  @Test
  public void testIsGameOver() {
    assertEquals(false,model1.isGameOver());
    model1.move(5,3,3,3);
    model1.move(2,3,4,3);
    assertEquals(false,model1.isGameOver());
    model1.move(0,3,2,3);
    assertEquals(false,model1.isGameOver());
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    assertEquals(false,model1.isGameOver());
    model1.move(3,6,3,4);
    assertEquals(true,model1.isGameOver());
    assertEquals(false,model3.isGameOver());
    model3.move(5,3,5,5);
    assertEquals(false,model3.isGameOver());
  }

  /**
   * Test getGameState method
   */
  @Test
  public void testGetGameState() {
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ",model1.getGameState());
    model1.move(1,3,3,3);
    assertEquals("    O O O    \n"
        + "    O _ O    \n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ",model1.getGameState());

    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ",model2.getGameState());
    model2.move(2,1,2,3);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ",model2.getGameState());
    model2.move(2,4,2,2);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O _ O _ _ O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ",model2.getGameState());
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "    O O O    \n"
        + "    O O O    ",model4.getGameState());

  }


  /**
   * Test getScore method
   */
  @Test
  public void testGetScore() {
    assertEquals(32,model1.getScore());
    model1.move(5,3,3,3);
    assertEquals(31,model1.getScore());
    model1.move(2,3,4,3);
    assertEquals(30,model1.getScore());
    model1.move(0,3,2,3);
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    model1.move(3,6,3,4);
    assertEquals(26,model1.getScore());
    assertEquals(84,model3.getScore());
    model3.move(5,3,5,5);
    model3.move(5,6,5,4);
    assertEquals(82,model3.getScore());
  }
}