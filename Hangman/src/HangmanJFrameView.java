import javax.swing.*;

import java.awt.*;

/**
 * This class represents a hangman view implementation which has a wordlist,
 */
public class HangmanJFrameView extends JFrame implements HangmanView {
  private JPanel panelTotal,panelUpper,panelMiddleLow,panelMiddleUp,panelBottom;
  private JLabel guessLabel,pictureLabel,currentWordLabel,gameStateLabel,answerLabel;
  private JButton enterButton,endGameButton,newGameButton,exitButton;
  private JTextField letterOrWord;

  /**
   * Constructs and initialize a JFrameView
   * @param title the title of this frame
   */
  public HangmanJFrameView( String title ){
    super( title );                      // invoke the JFrame constructor
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    panelTotal = new JPanel();
    this.add(panelTotal);
    panelTotal.setLayout(new GridBagLayout());

    //setting the upper panel
    GridBagConstraints c = new GridBagConstraints();
    panelUpper = new JPanel();
    panelUpper.setLayout(new BoxLayout(panelUpper,1));
    pictureLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon("res/Hangman0.png");
    pictureLabel.setIcon(imageIcon);
    pictureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelUpper.add(pictureLabel);
    gameStateLabel = new JLabel("");
    panelUpper.add(gameStateLabel);
    c.fill = GridBagConstraints.VERTICAL;
    c.gridy = 0;
    panelTotal.add(panelUpper,c);

    //Setting the middle up panel
    panelMiddleUp = new JPanel();
    currentWordLabel = new JLabel("");
    currentWordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelMiddleUp.add(currentWordLabel);
    c.fill = GridBagConstraints.VERTICAL;
    c.gridy = 1;
    panelTotal.add(panelMiddleUp,c);
    gameStateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    //Setting the middle low panel
    panelMiddleLow = new JPanel();
    c.fill = GridBagConstraints.VERTICAL;
    c.gridy = 2;
    panelTotal.add(panelMiddleLow,c);
    panelMiddleLow.setLayout( new FlowLayout());
    guessLabel = new JLabel("Make a guess");
    panelMiddleLow.add(guessLabel);
    enterButton = new JButton("Enter");
    letterOrWord = new JTextField(20);
    panelMiddleLow.add(letterOrWord);
    panelMiddleLow.add(enterButton);

    //Setting the bottom panel
    panelBottom=new JPanel();
    endGameButton = new JButton("End the game and see the word");
    panelBottom.add(endGameButton);
    answerLabel = new JLabel("");
    panelBottom.add(answerLabel);
    newGameButton = new JButton("New Game");
    panelBottom.add(newGameButton);
    exitButton = new JButton("Exit");
    panelBottom.add(exitButton);
    c.fill = GridBagConstraints.VERTICAL;
    c.gridy = 3;
    panelTotal.add(panelBottom,c);

    this.setPreferredSize(new Dimension(500,600));
    this.setVisible(true);
    pack();
  }


  @Override
  public void setCurrentWord(String s) {
    currentWordLabel.setText(s);
  }

  @Override
  public void setCurrentState(String s) {
    gameStateLabel.setText(s);
  }

  @Override
  public void addFeatures(HangmanController controller) {
    enterButton.addActionListener(evt->controller.makeAguess(letterOrWord.getText()));
    endGameButton.addActionListener(evt->controller.endGame());
    newGameButton.addActionListener(evt->controller.restartGame());
    exitButton.addActionListener(eve->controller.exitProgram());
  }

  @Override
  public void setPrompt(boolean isGameOver, boolean duplicateCorrectLetter,
      boolean duplicateWrongLetter) {
    if(isGameOver){
      JOptionPane.showMessageDialog(this,
          "The game is over. You cannot continue to guess.",
          "Warning",JOptionPane.WARNING_MESSAGE);
    }else if(duplicateCorrectLetter){
        JOptionPane.showMessageDialog(this,
            "Your guess is correct but you have already guessed it.",
            "Warning",JOptionPane.WARNING_MESSAGE);
    }else if(duplicateWrongLetter){
        JOptionPane.showMessageDialog(this,
            "Your guess is wrong and you have already guessed it.",
            "Warning",JOptionPane.WARNING_MESSAGE);
    }else {
      JOptionPane.showMessageDialog(this,
          "Your guess should be a letter or a word.",
          "Warning",JOptionPane.WARNING_MESSAGE);
    }
    this.clearInputString();
  }


  @Override
  public void clearInputString() {
    letterOrWord.setText("");
  }
  @Override
  public void setPicture(int i){
    String path = "res/Hangman"+i+".png";
    pictureLabel.setIcon(new ImageIcon(path));
  }

  @Override
  public void endGame(String s){
    String str = "The word is: "+s;
    answerLabel.setText(str);
    panelMiddleLow.setVisible(false);
  }

  @Override
  public void setNewGame(String s){
    currentWordLabel.setText(s);
    panelMiddleLow.setVisible(true);
    this.clearInputString();
    String path = "res/Hangman0.png";
    gameStateLabel.setText("");
    pictureLabel.setIcon(new ImageIcon(path));
    answerLabel.setText("");
  }


}