import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a hangman model implementation which has a wordlist, a guessing word,
 * an error times, a currentWord, a isGameOver, a wrongLettersWords and correct letters.
 */
public class HangmanModelImpl implements HangmanModel {
  private ArrayList<String> wordList;
  private String guessingWord;
  private static final int maxError=6;
  private int errorTimes;
  private String currentWord;
  private boolean isGameOver;
  private ArrayList<String> wrongLettersWords;
  private String correctLetters;

  /**
   * Generate a word list.
   * @throws IllegalStateException if the path does not exist.
   */
  private void createWordList() throws  IllegalStateException {
    try {
      this.wordList = new ArrayList<String>();
      File file = new File("res/1000words.txt");
      Scanner scanner = new Scanner(file);
      while(scanner.hasNext()){
        this.wordList.add(scanner.next());
      }
    }catch (FileNotFoundException e){
      throw new IllegalStateException("The file does not exist.");
    }
  }
  @Override
  public void newGame(){
    Random random = new Random();
    this.guessingWord = wordList.get(random.nextInt(wordList.size()));
    this.errorTimes = 0;
    this.currentWord = guessingWord.replaceAll("([a-z])", "_");
    this.correctLetters = "";
    this.wrongLettersWords = new ArrayList<String>();
    this.isGameOver = false;
  }

  /**
   * Constructs and initialize a hangman model with a random selected word.
   */
  public HangmanModelImpl(){
    this.createWordList();
    this.newGame();
  }

  /**
   * Constructs and initialize a hangman model with a given word.
   * @param s the given word
   */
  public HangmanModelImpl(String s){
    this.guessingWord = s;
    this.errorTimes = 0;
    this.currentWord = guessingWord.replaceAll("([a-z])", "_");
    this.correctLetters = "";
    this.wrongLettersWords = new ArrayList<String>();
    this.isGameOver = false;
  }
  @Override
  public boolean win(){
    return this.currentWord.equals(this.guessingWord);
  }
  @Override
  public boolean lose(){
    return this.errorTimes == maxError;
  }

  @Override
  public boolean isGameOver(){
    if(this.win()||this.lose()){
      this.isGameOver=true;
    }
    return this.isGameOver;
  }

  /**
   * Check whether the given word is the guessing word
   * @param str the given word
   */
  private void guessAWord(String str) {
    if(str.equals(this.guessingWord)){
      this.currentWord = str;
    }else {
      this.errorTimes++;
      this.wrongLettersWords.add(str);
    }
  }

  /**
   * Check whether the given letter is the guessing letter
   * @param str the given letter
   */
  private void guessALetter(String str) {
    if(this.guessingWord.contains(str)){
      correctLetters+=str;
      String pattern = String.format("[^%s]",correctLetters);
      this.currentWord = this.guessingWord.replaceAll(pattern,"_");
    }else {
      this.errorTimes++;
      this.wrongLettersWords.add(str);
    }
  }

  @Override
  public void guess(String str){
      if(str.length()==1){
        this.guessALetter(str);
      }else if(str.length()>1){
        this.guessAWord(str);
      }
      this.isGameOver();
  }


  @Override
  public String getCurrentWord(){
    return this.currentWord;
  }

  /**
   * Get a representation of wrong letters list.
   * @return
   */
  private String wrongLettersToString(){
    String str = "";
    for(String s: this.wrongLettersWords){
      str += s+" ";
    }
    return str;
  }
  @Override
  public String getGameState(){
    String str="";
    if(this.win()){
      str += "Congratulations! You win!";
    }else if(this.lose()){
      str += "You have lost!";
    }else {
      str += "The game continues.\nThe incorrect letters/words you have guessed: "
          +this.wrongLettersToString();
    }
    return str;
 }

  @Override
  public boolean letterInWrongLetters(String str) {
    if(this.wrongLettersWords.contains(str)){
      return true;
    }
    return false;
  }

  @Override
  public boolean letterInCorrectLetters(String str) {
    if(this.correctLetters.contains(str)){
      return true;
    }
    return false;
  }

  @Override
  public ArrayList<String> getWrongLetters(){
    return this.wrongLettersWords;
  }

  @Override
  public void setGameOver(boolean isGameOver) {
    this.isGameOver=isGameOver;
  }


  @Override
  public boolean getGameOver() {
    return this.isGameOver;
  }


  @Override
  public String getGuessingWord() {
    return this.guessingWord;
  }
}
