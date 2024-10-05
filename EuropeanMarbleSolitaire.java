package marblesolitaire.model.hw02;

public class EuropeanMarbleSolitaire extends AMarbleSolitaire {

  // constructor that takes no arguments and makes default board
  public EuropeanMarbleSolitaire() {
    boardMaker(3);
    board[3][3] = '_';
  }


  /**
   * constructor that takes coords for empty slot and makes board armThickness 3
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   */
  public EuropeanMarbleSolitaire(int sRow, int sCol) {
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
  public EuropeanMarbleSolitaire(int armThickness) throws IllegalArgumentException {
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
  public EuropeanMarbleSolitaire(int armThickness, int sRow, int sCol) {

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


  /**
   * constructs a european board with the given arm thickness
   * @param armThickness
   */
  @Override
  public void boardMaker(int armThickness) {
    int boardThickness = (3 * armThickness) - 2;
    this.board = new char[boardThickness][boardThickness];
    for(int r = 0; r < boardThickness; r++) {
      for(int c = 0; c < boardThickness; c++) {
        boolean topLeft = (r > (boardThickness + (armThickness + 2 * c)) / 2 - 1) && c < armThickness - 1;
        boolean topRight = r > armThickness * 2 - 2
                && c > (boardThickness / 2) + (armThickness / 2 + (boardThickness - r - 1));
        boolean botLeft = r < armThickness - 1 && c < (boardThickness - (armThickness + 2 * r)) / 2;
        boolean botRight = r < armThickness - 1 && c > (boardThickness / 2) + ((armThickness + 2 * r) / 2);

        if(topLeft || topRight || botLeft || botRight) {
          this.board[r][c] = ' ';
        }
        else {
          this.board[r][c] = 'o';
        }
      }
    }
  }

}
