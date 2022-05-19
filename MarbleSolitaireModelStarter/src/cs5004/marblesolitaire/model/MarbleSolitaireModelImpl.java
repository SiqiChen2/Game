package cs5004.marblesolitaire.model;
/**
 * This class represents a MarbleSolitaireModelImpl
 * which ahs a board, a arm and a score.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private Status[][] board;
  private int arm;
  private int score;

  /**
   * Check whether the position is valid or not
   * @param r the row of this position
   * @param c the column of this position
   * @return true if the position is valid, false otherwise
   */
  private boolean validCheck(int r, int c){
    if(r<0 || r>2*this.arm || c<0 ||c>2*this.arm){
      return false;
    }else
      return this.board[r][c] != Status.INVALID;
  }

  /**
   * Check whether the marble can move from one position to other position
   * @param fromRow the row of current position of the marble
   * @param fromCol the column of current position of the marble
   * @param toRow the row of position moving to of the marble
   * @param toCol the column of position moving to of the marble
   * @return true if the marble can move from current position to the given position
   */
  private boolean moveHelper(int fromRow, int fromCol, int toRow, int toCol){
    if(this.validCheck(fromRow,fromCol) && this.validCheck(toRow,toCol)){
      if(this.board[toRow][toCol]==Status.EMPTY
          &&this.board[fromRow][fromCol]==Status.OCCUPIED){
        if(fromRow==toRow && Math.abs(fromCol-toCol)==2
            && this.board[fromRow][(fromCol+toCol)/2]==Status.OCCUPIED){
          return true;
        }else if(fromCol==toCol && Math.abs(fromRow-toRow)==2
            &&this.board[(fromRow+toRow)/2][fromCol]==Status.OCCUPIED){
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Construct and initialize the model
   * @param arm the thickness of the board
   * @param r the row of initial empty position
   * @param c the column of initial empty position
   * @throws IllegalArgumentException if the row or column is out of the board or
   * the position if occupied
   */
  private void constructorHelper(int arm, int r, int c)throws IllegalArgumentException{
    if((arm % 2) != 1 || arm<3){
      throw new IllegalArgumentException("arm should be a positive odd number larger than 3.");
    }
    this.arm = arm;
    this.board = new Status[2*this.arm+1][2*this.arm+1];
    for(int x = 0; x <= 2*this.arm; x++){
      for(int y = 0; y <= 2*this.arm; y++){
        this.board[x][y]=Status.INVALID;
      }
    }

    for(int x1 = 0; x1<=2*this.arm; x1++){
      for(int y1= this.arm/2+1; y1<this.arm+this.arm/2+1 ; y1++){
        this.board[x1][y1] = Status.OCCUPIED;
      }
    }
    for(int y2 = 0; y2<=2*this.arm; y2++){
      for(int x2= this.arm/2+1; x2< this.arm+this.arm/2+1; x2++){
        this.board[x2][y2] = Status.OCCUPIED;
      }
    }
    if(!this.validCheck(r,c)){
      String errorMessage = String.format("Invalid empty cell position (%d,%d)",r,c);
      throw new IllegalArgumentException(errorMessage);
    }
    this.board[r][c] = Status.EMPTY;
    this.score = (int) (Math.pow((2*this.arm+1),2)-4*Math.pow(((this.arm/2)+1),2)-1);
  }

  /**
   * Constructs a board and initialize its arm as 3 and empty slot at center
   */
  public MarbleSolitaireModelImpl(){
    this.constructorHelper(3,3,3);
  }

  /**
   * Constructs a board and initialize its arm as 3 and empty slot at the given position
   * @param r the row of the empty position
   * @param c the column of the empty position
   * @throws IllegalArgumentException if the empty position is invalid
   */
  public MarbleSolitaireModelImpl(int r, int c)throws IllegalArgumentException{
    constructorHelper(3,r,c);
  }

  /**
   * Constructs a board and initialize its arm as given arm and empty slot at center
   * @param arm the thickness of the board
   * @throws IllegalArgumentException if the arm is an even number of less than 3
   */
  public MarbleSolitaireModelImpl(int arm) throws IllegalArgumentException{
    constructorHelper(arm,arm,arm);
  }

  /**
   * Constructs a board and initialize its arm as given arm and empty slot at
   * the given position
   * @param arm the thickness of the board
   * @param r the row of the empty position
   * @param c c the column of the empty position
   * @throws IllegalArgumentException if the arm is an even number of less than 3 or
   * the empty position is invalid
   */
  public MarbleSolitaireModelImpl(int arm, int r, int c) throws IllegalArgumentException{
    constructorHelper(arm,r,c);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {
    if(this.moveHelper(fromRow,fromCol,toRow,toCol)){
      this.board[fromRow][fromCol] = Status.EMPTY;
      this.board[toRow][toCol] = Status.OCCUPIED;
      this.board[(fromRow+toRow)/2][(fromCol+toCol)/2] = Status.EMPTY;
      this.score--;
    }else {
      throw new IllegalArgumentException("The move is invalid");
    }
  }

  @Override
  public boolean isGameOver() {
    for(int i=0; i<=2*arm;i++){
      for(int j=0;j<=2*arm;j++){
        if(this.moveHelper(i,j,i,j-2)
            || this.moveHelper(i,j,i,j+2)
            || this.moveHelper(i,j,i-2,j)
            || this.moveHelper(i,j,i+2,j)){
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i <= 2 * this.arm; i++) {
      for (int j = 0; j <= 2 * this.arm; j++) {
        if (this.board[i][j] == Status.INVALID) {
          str.append(" ");
        }else if(this.board[i][j]==Status.OCCUPIED){
          str.append("O");
        }else if(this.board[i][j]==Status.EMPTY){
          str.append("_");
        }
        str.append(" ");
      }
      str.deleteCharAt(str.length()-1);
      str.append("\n");
    }
    str.deleteCharAt(str.length()-1);
    return str.toString();
  }
  @Override
  public int getScore() {
    return this.score;
  }
}
