package marblesolitaire.model.hw02;

import org.junit.jupiter.api.Test;

import marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EnglishMarbleSolitaireTest {
  // test constructor to see if it throws an exception
  // test toString method
  MarbleSolitaireTextView baseExample = new MarbleSolitaireTextView(new EnglishMarbleSolitaire());

  // TODO done
  @Test
  void testConstructorRowCol() {
    // EnglishMarbleSolitaire(int sRow, intsCol)
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire(3, 4);
    Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
            new EnglishMarbleSolitaire(1, 1));
    assertEquals(baseMarbleSolitaire.board[3][4], '_');
    assertEquals(("Invalid empty cell position (1, 1)"), exception1.getMessage());


  }


  // TODO done
  @Test
  void testConstructorArmThickness() {
    // EnglishMarbleSolitaire(int armThickness)
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new EnglishMarbleSolitaire(2));
    assertEquals("Arm thickness must be positive and odd number", exception.getMessage());
  }


  // TODO done
  @Test
  void testConstructorArmThicknessAndRowCol() {
    // EnglishMarbleSolitaire(int armThickness)
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new EnglishMarbleSolitaire(4, 2, 2));
    Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
            new EnglishMarbleSolitaire(3, 1, 0));
    assertEquals("Arm thickness must be positive and odd number", exception.getMessage());
    assertEquals(("Invalid empty cell position (1, 0)"), exception2.getMessage());

  }

  @Test
  void testBoardMaker() {

  }

  // TODO done
  @Test
  void testGetBoardSize() {
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    AMarbleSolitaire smallMarbleSolitaire = new EnglishMarbleSolitaire(1);
    AMarbleSolitaire bigMarbleSolitaire = new EnglishMarbleSolitaire(5);
    assertEquals(7, baseMarbleSolitaire.getBoardSize());
    assertEquals(3, smallMarbleSolitaire.getBoardSize());
    assertEquals(11, bigMarbleSolitaire.getBoardSize());

  }

  // TODO done
  @Test
  void testGetSlotAt() {
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, baseMarbleSolitaire.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, baseMarbleSolitaire.getSlotAt(3, 3));

  }

  // TODO done
  @Test
  void testGetScore() {
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    AMarbleSolitaire baseMarbleSolitaire31pts = new EnglishMarbleSolitaire();
    baseMarbleSolitaire31pts.board[3][4] = '_';
    assertEquals(32, baseMarbleSolitaire.getScore());
    assertEquals(31, baseMarbleSolitaire31pts.getScore());
  }

  // TODO done
  @Test
  void testMove() {
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    // move(int fromRow, int fromCol, int toRow, int toCol)
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new EnglishMarbleSolitaire().move(1, 1, 2, 2));
    assertEquals("Invalid move", exception.getMessage());
    assertEquals(baseMarbleSolitaire.board[1][3], 'o');
    assertEquals(baseMarbleSolitaire.board[3][3], '_');
    assertEquals(baseMarbleSolitaire.board[2][3], 'o');
    baseMarbleSolitaire.move(1, 3, 3, 3);
    assertEquals(baseMarbleSolitaire.board[1][3], '_');
    assertEquals(baseMarbleSolitaire.board[3][3], 'o');
    assertEquals(baseMarbleSolitaire.board[2][3], '_');


  }

  // TODO done
  @Test
  void testIsGameOver() {
    // isGameOver()

    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    AMarbleSolitaire smallMarbleSolitaire = new EnglishMarbleSolitaire(1);
    //assertEquals(true, smallMarbleSolitaire.isGameOver());
    assertEquals(false, baseMarbleSolitaire.isGameOver());

  }

  // TODO done
  @Test
  void testValidMove() {
    // validMove (int fromRow, int fromCol, int toRow, int toCol)
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    assertEquals(false, baseMarbleSolitaire.validMove(1, 1, 3, 4));
    assertEquals(true, baseMarbleSolitaire.validMove(1, 3, 3, 3));
  }


  @Test
  void testHasMove() {
    // hasMove (int fromRow, int fromCol)
    AMarbleSolitaire baseMarbleSolitaire = new EnglishMarbleSolitaire();
    AMarbleSolitaire smallMarbleSolitaire = new EnglishMarbleSolitaire(1);
    assertEquals(false, smallMarbleSolitaire.hasMove(1, 1));
    assertEquals(true, baseMarbleSolitaire.hasMove(1, 3));

  }

}