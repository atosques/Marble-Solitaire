package marblesolitaire.view;

import marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class TriangleSolitaireTextView extends AMarbleSolitaireTextView{

  /** Constructor that only takes in a state and has destination set to System.out by default
   * @param state the marble solitaire model that will be turned into a text representation
   *              by this object
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  /** Constructor that takes a state and a destination
   *
   * @param state the marble solitaire model that will be turned into a text representation
   *    *         by this object
   * @param destination the appendable object that the view will use as its destination
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    super(state, destination);
  }


  /**
   * @return a string representing this board
   */
  @Override
  public String toString() {
    String boardString = "";
    int boardSize = this.state.getBoardSize();

    // for each row
    for (int r = 0; r < boardSize; r++) {

      // for each column within that row
      // make spaces on the right
      for (int c = 0; c < boardSize - r - 1; c++) {

        boardString = boardString + " ";
      }

      // add marbles and empty spaces in the middle
      for (int c = 0; c < boardSize; c++) {
        if (this.state.getSlotAt(r, c).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          boardString = boardString + "o ";
        }
        if (this.state.getSlotAt(r, c).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          boardString = boardString + "_ ";
        }

      }

      // get the next line of the board
      //boardString = boardString.substring(0, boardString.length() - 1);
      if (r < boardSize - 1) {
        boardString = boardString + "\n";
      }
    }
    return boardString;

  }

}
