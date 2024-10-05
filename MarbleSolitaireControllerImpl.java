package marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import marblesolitaire.model.hw02.MarbleSolitaireModel;
import marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  public final Readable rd;
  public MarbleSolitaireView view;
  public MarbleSolitaireModel model;

  /**
   *
   * @param model the model that is being interacted with
   * @param view the object that will render the game
   * @param readable the readable that holds the values which are processed by the controller
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable readable) throws IllegalArgumentException {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    } else {
      this.rd = readable;
      this.view = view;
      this.model = model;
    }
  }

  /**
   * interacts with the user inputs by passing them to commands that change the view or the model
   * @throws IllegalStateException if any input is invalid or formatted incorrectly
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.rd);
    ArrayList<Integer> positions = new ArrayList<>();
    try {
      this.renderGame();
      while (!model.isGameOver()) {
        try {
          if (scan.hasNextInt()) {
            int position = scan.nextInt();
            positions.add(position);
          }
          else {
            String input = scan.next();
            if ((input.equalsIgnoreCase("q")) || (input.equalsIgnoreCase("Q"))) {
              view.renderMessage("Game quit!" + System.lineSeparator());
              view.renderMessage("State of game when quit:" + System.lineSeparator());
              view.renderBoard();
              view.renderMessage(System.lineSeparator());
              view.renderMessage("Score: " + model.getScore());
              return;
            }
          }
          if (positions.size() == 4) {
            int fromRow = positions.get(0);
            int fromCol = positions.get(1);
            int toRow = positions.get(2);
            int toCol = positions.get(3);
            try {
              model.move(fromRow - 1,fromCol - 1, toRow - 1, toCol - 1);
              this.renderGame();
            }
            catch (IllegalArgumentException e) {
              view.renderMessage("Invalid move. Play again." + System.lineSeparator());
              this.renderGame();
            }
            positions = new ArrayList<>();
          }
        }
        catch (InputMismatchException | NumberFormatException e) {
          view.renderMessage("Re-enter value" + System.lineSeparator());
        }
        catch (NoSuchElementException e) {
          throw new IllegalStateException("Bad Input");
        }
      }
      if (model.isGameOver()) {
        view.renderMessage("Game over!" + System.lineSeparator());
        this.renderGame();
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error reading input");
    }
  }
  /**
   * renders the game inlcluding the board, and the current score
   * @throws IOException if recieving or transferring a data between readable and appendable fails
   */
  public void renderGame() throws IOException {
    view.renderBoard();
    view.renderMessage("\n");
    view.renderMessage("Score: " + model.getScore() + "\n");
  }
}
