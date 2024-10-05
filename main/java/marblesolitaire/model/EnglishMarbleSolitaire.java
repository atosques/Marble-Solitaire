package marblesolitaire.model.hw02;

public class EnglishMarbleSolitaire extends AMarbleSolitaire {


  // constructor that takes no arguments and makes default board
  public EnglishMarbleSolitaire() {
    boardMaker(3);
    board[3][3] = '_';
  }


  /**
   * constructor that takes coords for empty slot and makes board armThickness 3
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   */
  public EnglishMarbleSolitaire(int sRow, int sCol) {
    boardMaker(3);
    if (board[sRow][sCol] == ' ') {
      throw new IllegalArgumentException("Invalid empty cell position " +
              "(" + sRow + ", " + sCol + ")");
    } else {
      board[sRow][sCol] = '_';
    }
  }

  /**
   * constructor that takes int for armThickness and sets empty slot in middle
   *
   * @param armThickness the armthickness for the board- must be positive and odd
   */
  public EnglishMarbleSolitaire(int armThickness) throws IllegalArgumentException {
    if (armThickness > 0 && (armThickness % 2) != 0) {
      boardMaker(armThickness);
      board[(int) Math.ceil((board.length)/2)][(int) Math.ceil((board.length)/2)] = '_';
    } else {
      throw new IllegalArgumentException("Arm thickness must be positive and odd number");
    }
  }


  /**
   * constructor that takes armThickness and emptySlot space
   *
   * @param armThickness arm thickness for board- must be positive and odd
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   */
  public EnglishMarbleSolitaire(int armThickness, int sRow, int sCol) {

    if (armThickness > 0 && (armThickness % 2) != 0) {
      boardMaker(armThickness);
      if (board[sRow][sCol] == ' ') {
        throw new IllegalArgumentException("Invalid empty cell position " +
                "(" + sRow + ", " + sCol + ")");
      } else {
        board[sRow][sCol] = '_';

      }
    } else {
      throw new IllegalArgumentException("Arm thickness must be positive and odd number");
    }
  }


  // constructs a board with the given thickness, English by default
  public void boardMaker(int armThickness){
    int boardThickness = (armThickness * 2) + 1;
    int minValue = armThickness - (armThickness / 2);
    int maxValue = armThickness + (armThickness / 2);
    this.board = new char[boardThickness][boardThickness];

    for (int r = 0; r < boardThickness; r++) {
      for (int c = 0; c < boardThickness; c++) {
        if ((r < minValue || r > maxValue) && (c < minValue || c > maxValue)) {
          this.board[r][c] = ' ';
        } else {
          this.board[r][c] = 'o';
        }
      }
    }
  }
}






