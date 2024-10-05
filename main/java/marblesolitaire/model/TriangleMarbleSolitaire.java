package marblesolitaire.model.hw02;

public class TriangleMarbleSolitaire extends AMarbleSolitaire{

  // constructor that takes no arguments and makes default board
  public TriangleMarbleSolitaire() {
    // constructor that takes no arguments and makes default board
      boardMaker(5);
      board[0][0] = '_';

  }

  /**
   * constructor that takes coords for empty slot and makes board armThickness 3
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   */
  public TriangleMarbleSolitaire(int sRow, int sCol) throws IllegalStateException{
    boardMaker(5);
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
  public TriangleMarbleSolitaire(int armThickness) throws IllegalArgumentException {
    if (armThickness > 0) {
      boardMaker(armThickness);
      board[0][0] = '_';
    } else {
      throw new IllegalArgumentException("Arm thickness must be positive number");
    }
  }

  /**
   * constructor that takes armThickness and emptySlot space
   *
   * @param armThickness arm thickness for board- must be positive and odd
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   */
  public TriangleMarbleSolitaire(int armThickness, int sRow, int sCol) {

    if (armThickness > 0) {
      boardMaker(armThickness);
      if (board[sRow][sCol] == ' ') {
        throw new IllegalArgumentException("Invalid empty cell position " +
                "(" + sRow + ", " + sCol + ")");
      } else {
        board[sRow][sCol] = '_';
      }
    } else {
      throw new IllegalArgumentException("Arm thickness must be positive number");
    }
  }

  @Override
  public boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    // Check if the destination is within the valid triangle area
    if (!isWithinTriangle(toRow, toCol) || !isWithinTriangle(fromRow, fromCol)) {
      return false;
    }

    // Check that the move is exactly two positions away in a valid direction
    int rowDiff = Math.abs(fromRow - toRow);
    int colDiff = Math.abs(fromCol - toCol);

    if (rowDiff == 2 && colDiff == 2 || rowDiff == 2 && colDiff == 0 || rowDiff == 0 && colDiff == 2) {
      // Ensure the move is jumping over a marble
      int midRow = (fromRow + toRow) / 2;
      int midCol = (fromCol + toCol) / 2;

      if (board[fromRow][fromCol] == 'o' && board[toRow][toCol] == '_' && board[midRow][midCol] == 'o') {
        return true;
      }
    }

    return false;
  }

  private boolean isWithinTriangle(int row, int col) {
    return row >= 0 && row < board.length && col >= 0 && col <= row;
  }






  /**
   * constructs a european board with the given arm thickness
   * @param armThickness
   */


  @Override
  public void boardMaker(int armThickness) {
    this.board = new char[armThickness][armThickness];
    for (int r = 0; r < armThickness; r++) {
      for (int c = 0; c < armThickness; c++) {
        if (c > r) {
          this.board[r][c] = ' ';
        } else {
          this.board[r][c] = 'o';
        }
      }
    }
  }

}
