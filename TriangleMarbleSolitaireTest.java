package marblesolitaire.model.hw02;

import org.junit.jupiter.api.Test;

import marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.*;

class TriangleMarbleSolitaireTest {
  // test constructor to see if it throws an exception
  // test toString method
  MarbleSolitaireTextView baseExample = new MarbleSolitaireTextView(new TriangleMarbleSolitaire());

  // TODO done
  @Test
  void testConstructorRowCol() {
    // TriangleMarbleSolitaire(int sRow, intsCol)
    AMarbleSolitaire baseTriangleSolitaire = new TriangleMarbleSolitaire(4, 2);
    Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
            new TriangleMarbleSolitaire(0, 2));
    assertEquals(baseTriangleSolitaire.board[4][2], '_');
    assertEquals(("Invalid empty cell position (0, 2)"), exception1.getMessage());


  }


  // TODO done
  @Test
  void testConstructorArmThickness() {
    // TriangleMarbleSolitaire(int armThickness)
    AMarbleSolitaire baseTriangleSolitaire = new TriangleMarbleSolitaire(3);
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new TriangleMarbleSolitaire(-3));
    assertEquals("Arm thickness must be positive number", exception.getMessage());
    assertEquals(baseTriangleSolitaire.board[0][0], '_');
  }


  // TODO done
  @Test
  void testConstructorArmThicknessAndRowCol() {
    // TriangleMarbleSolitaire(int armThickness)
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new TriangleMarbleSolitaire(-3, 2, 2));
    assertEquals("Arm thickness must be positive number", exception.getMessage());

  }

  @Test
  void testBoardMaker() {

  }

  // TODO done
  @Test
  void testGetBoardSize() {
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    AMarbleSolitaire smallMarbleSolitaire = new TriangleMarbleSolitaire(2);
    AMarbleSolitaire bigMarbleSolitaire = new TriangleMarbleSolitaire(7);
    assertEquals(5, baseMarbleSolitaire.getBoardSize());
    assertEquals(2, smallMarbleSolitaire.getBoardSize());
    assertEquals(7, bigMarbleSolitaire.getBoardSize());

  }

  // TODO done
  @Test
  void testGetSlotAt() {
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, baseMarbleSolitaire.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(3, 3));

  }

  // TODO done
  @Test
  void testGetScore() {
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    AMarbleSolitaire baseMarbleSolitaire31pts = new TriangleMarbleSolitaire();
    baseMarbleSolitaire31pts.board[1][1] = '_';
    assertEquals(14, baseMarbleSolitaire.getScore());
    assertEquals(13, baseMarbleSolitaire31pts.getScore());
  }

  // TODO done
  @Test
  void testMove() {
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    // move(int fromRow, int fromCol, int toRow, int toCol)
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new TriangleMarbleSolitaire().move(1, 1, 2, 1));
    assertEquals("Invalid move", exception.getMessage());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, baseMarbleSolitaire.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(2, 2));
    baseMarbleSolitaire.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, baseMarbleSolitaire.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, baseMarbleSolitaire.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, baseMarbleSolitaire.getSlotAt(2, 0));

  }

  // TODO done
  @Test
  void testIsGameOver() {
    // isGameOver()

    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    AMarbleSolitaire smallMarbleSolitaire = new TriangleMarbleSolitaire(2);
    assertEquals(true, smallMarbleSolitaire.isGameOver());
    assertEquals(false, baseMarbleSolitaire.isGameOver());

  }

  // TODO done
  @Test
  void testValidMove() {
    // validMove (int fromRow, int fromCol, int toRow, int toCol)
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    assertEquals(false, baseMarbleSolitaire.validMove(2, 0, 2, 2));
    assertEquals(true, baseMarbleSolitaire.validMove(2, 0, 0, 0));
  }


  @Test
  void testHasMove() {
    // hasMove (int fromRow, int fromCol)
    AMarbleSolitaire baseMarbleSolitaire = new TriangleMarbleSolitaire();
    assertEquals(false, baseMarbleSolitaire.hasMove(4, 2));
    assertEquals(true, baseMarbleSolitaire.hasMove(2, 0));

  }
}