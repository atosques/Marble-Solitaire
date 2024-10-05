package marblesolitaire.view;

import java.io.IOException;

import marblesolitaire.model.hw02.MarbleSolitaireModelState;

public abstract class AMarbleSolitaireTextView implements MarbleSolitaireView {
  //
  protected MarbleSolitaireModelState state;

  protected Appendable destination;

  /** Constructor that only takes in a state and has destination set to System.out by default
   * @param state the marble solitaire model that will be turned into a text representation
   *              by this object
   */
  public AMarbleSolitaireTextView(MarbleSolitaireModelState state) {
    if (state == null) {
      throw new IllegalArgumentException("state is null");
    } else {
      this.state = state;
      this.destination = System.out;
    }
  }

  /** Constructor that takes a state and a destination
   *
   * @param state the marble solitaire model that will be turned into a text representation
   *    *         by this object
   * @param destination the appendable object that the view will use as its destination
   */
  public AMarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    if ((state == null) || (destination == null)) {
      throw new IllegalArgumentException("state or destination is null");
    } else {
      this.destination = destination;
      this.state = state;
    }
  }



  /**
   * @return a string representing this board
   */
  abstract public String toString();

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderBoard() throws IOException {
   this.destination.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      throw new IOException();
    }
    else {
      this.destination.append(message);
    }

  }
}
