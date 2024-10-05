package marblesolitaire.model.hw02;

import java.lang.Math;


public abstract class AMarbleSolitaire implements MarbleSolitaireModel {

  public char[][] board;


  /**
   * @param armThickness constructs a 2d array that represents the board of marbles
   */
  abstract protected void boardMaker(int armThickness);

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */

  public int getBoardSize() {
    return board.length;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */

  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (board[row][col] == '_') {
      return SlotState.Empty;
    } else if (board[row][col] == 'o') {
      return SlotState.Marble;
    } else {
      return SlotState.Invalid;
    }
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */

  public int getScore() {
    int score = 0;
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (board[r][c] == 'o') {
          score += 1;
        }
      }
    }
    return score;
  }


  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */


 public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
  if (validMove(fromRow, fromCol, toRow, toCol)) {
      board[fromRow][fromCol] = '_';
      board[toRow][toCol] = 'o';
      board[(toRow + fromRow) / 2][(toCol + fromCol) / 2] = '_';
    } else {
      throw new IllegalArgumentException("Invalid move");
    }


  }


  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */

  public boolean isGameOver() {
    boolean gameOver = false;
    int over = 1;
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++) {
        if (hasMove(r, c)) {
          over++;
        }
      }
    }
    return (over == 1);
  }





  /**
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return returns true if this move is valid
   */
  public boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if ((toRow > board.length - 1) || (toCol > board.length - 1) || (toRow < 0) || (toCol < 0)) {
      return false;
    } else if (((Math.abs(fromRow - toRow) == 2) || (Math.abs(fromCol - toCol) == 2)) // either is 2 away
            && (board[(toRow + fromRow) / 2][(toCol + fromCol) / 2] == 'o') // middle has piece
            && (board[fromRow][fromCol] == 'o') && (board[toRow][toCol] == '_')
            && (fromRow != toRow || fromCol != toCol)) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * @param fromRow the row of the piece being tested
   * @param fromCol the col of the piece being tested
   * @return true if the piece at the given row and column has any valid moves to play
   */
  public boolean hasMove(int fromRow, int fromCol) {
    return ((validMove(fromRow, fromCol, (fromRow + 2), fromCol)) ||
            (validMove(fromRow, fromCol, (fromRow - 2), fromCol)) ||
            (validMove(fromRow, fromCol, fromRow, (fromCol + 2))) ||
            (validMove(fromRow, fromCol, fromRow, (fromCol - 2))));

  }


}





