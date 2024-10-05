package marblesolitaire.view;

import marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView extends AMarbleSolitaireTextView{

  /** Constructor that only takes in a state and has destination set to System.out by default
   * @param state the marble solitaire model that will be turned into a text representation
   *              by this object
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  /** Constructor that takes a state and a destination
   *
   * @param state the marble solitaire model that will be turned into a text representation
   *    *         by this object
   * @param destination the appendable object that the view will use as its destination
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    super(state, destination);
  }

  /**
   * @return a string representing this board
   */
  @Override
  public String toString() {
    int boardSize = state.getBoardSize();
    StringBuilder boardString = new StringBuilder();
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        MarbleSolitaireModelState.SlotState currentSlot = state.getSlotAt(i, j);
        if (currentSlot == MarbleSolitaireModelState.SlotState.Marble) {
          boardString.append("o");
        } else if (currentSlot == MarbleSolitaireModelState.SlotState.Empty) {
          boardString.append("_");
        } else {
          boardString.append(" ");
        }
      }
      boardString.append("\n");
    }
    return boardString.toString();
  }





}
