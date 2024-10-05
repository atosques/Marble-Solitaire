package marblesolitaire.controller;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import marblesolitaire.model.hw02.AMarbleSolitaire;
import marblesolitaire.model.hw02.EnglishMarbleSolitaire;
import marblesolitaire.model.hw02.EuropeanMarbleSolitaire;
import marblesolitaire.model.hw02.MarbleSolitaireModel;
import marblesolitaire.model.hw02.TriangleMarbleSolitaire;
import marblesolitaire.view.MarbleSolitaireTextView;
import marblesolitaire.view.MarbleSolitaireView;
import marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.*;

class MarbleSolitaireControllerImplTest {

  @Test
  public void resetVariables() {

    // regular input readable
    Readable rd1 = new StringReader("4 2 4 4 q");
    StringBuilder gameRecord1 = new StringBuilder();
    MarbleSolitaireModel mockModel1 = new MockModel(gameRecord1);
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(mockModel1, new StringBuilder());
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(mockModel1, view1, rd1);
    controller1.playGame();
    assertEquals("fromRow = 3, fromCol = 1, toRow = 3, toCol = 3\n", gameRecord1.toString());


    // i in the readable should have no impact on output
    Readable rd2 = new StringReader("4 i 5 4 3 q");
    StringBuilder gameRecord2 = new StringBuilder();
    MarbleSolitaireModel mockModel2 = new MockModel(gameRecord2);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(mockModel2, new StringBuilder());
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(mockModel2, view2, rd2);
    controller2.playGame();
    assertEquals("fromRow = 3, fromCol = 4, toRow = 3, toCol = 2\n", gameRecord2.toString());


    // should give bad input because not playable move (only shifts one space)
    Readable rd3 = new StringReader("5 4 5 3");
    StringBuilder gameRecord3 = new StringBuilder();
    MarbleSolitaireModel mockModel3 = new MockModel(gameRecord3);
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(mockModel3, new StringBuilder());
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(mockModel3, view3, rd3);
    Exception exception1 = assertThrows(IllegalStateException.class, () ->
            controller3.playGame());
    assertEquals(("Bad Input"), exception1.getMessage());


    // question mark should have no impact on output
    Readable rd4 = new StringReader("x 4 7 ? 4 5 q");
    StringBuilder gameRecord4 = new StringBuilder();
    MarbleSolitaireModel mockModel4 = new MockModel(gameRecord4);
    MarbleSolitaireView view4 = new MarbleSolitaireTextView(mockModel4, new StringBuilder());
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(mockModel4, view4, rd4);
    controller4.playGame();
    assertEquals("fromRow = 3, fromCol = 6, toRow = 3, toCol = 4\n", gameRecord4.toString());

    // multiple charachters in a row not q, should have no impact
    Readable rd5 = new StringReader("2 a f 4 4 m o 4 q");
    StringBuilder gameRecord5 = new StringBuilder();
    MarbleSolitaireModel mockModel5 = new MockModel(gameRecord5);
    MarbleSolitaireView view5 = new MarbleSolitaireTextView(mockModel5, new StringBuilder());
    MarbleSolitaireController controller5 = new MarbleSolitaireControllerImpl(mockModel5, view5, rd5);
    controller5.playGame();
    assertEquals("fromRow = 1, fromCol = 3, toRow = 3, toCol = 3\n", gameRecord5.toString());

    // line breaks should have no impact on output
    Readable rd6 = new StringReader("e 4 3 \n 4 5 \n q");
    StringBuilder gameRecord6 = new StringBuilder();
    MarbleSolitaireModel mockModel6 = new MockModel(gameRecord6);
    MarbleSolitaireView view6 = new MarbleSolitaireTextView(mockModel6, new StringBuilder());
    MarbleSolitaireController controller6 = new MarbleSolitaireControllerImpl(mockModel6, view6, rd6);
    controller6.playGame();
    assertEquals("fromRow = 3, fromCol = 2, toRow = 3, toCol = 4\n", gameRecord6.toString());

    // line breaks should have no impact on output
    Readable rd7 = new StringReader("\n 5 \n 4 \n 3 4 r q");
    StringBuilder gameRecord7 = new StringBuilder();
    MarbleSolitaireModel mockModel7 = new MockModel(gameRecord7);
    MarbleSolitaireView view7 = new MarbleSolitaireTextView(mockModel7, new StringBuilder());
    MarbleSolitaireController controller7 = new MarbleSolitaireControllerImpl(mockModel7, view7, rd7);
    controller7.playGame();
    assertEquals("fromRow = 4, fromCol = 3, toRow = 2, toCol = 3\n", gameRecord7.toString());


    Readable rd8 = new StringReader("s 7 4 \n \n 5 \n 4 \n q");
    StringBuilder gameRecord8 = new StringBuilder();
    MarbleSolitaireModel mockModel8 = new MockModel(gameRecord8);
    MarbleSolitaireView view8 = new MarbleSolitaireTextView(mockModel8, new StringBuilder());
    MarbleSolitaireController controller8 = new MarbleSolitaireControllerImpl(mockModel8, view8, rd8);
    controller8.playGame();
    assertEquals("fromRow = 6, fromCol = 3, toRow = 4, toCol = 3\n", gameRecord8.toString());


    // should have empty game log because game was quit before move was processed
    Readable rd9 = new StringReader("5 q 4 5 3");
    StringBuilder gameRecord9 = new StringBuilder();
    MarbleSolitaireModel mockModel9 = new MockModel(gameRecord9);
    MarbleSolitaireView view9 = new MarbleSolitaireTextView(mockModel9, new StringBuilder());
    MarbleSolitaireController controller9 = new MarbleSolitaireControllerImpl(mockModel9, view9, rd9);
    controller9.playGame();
    assertEquals("", gameRecord9.toString());

    // should give bad input because not playable move (more than four integers and
    // not divisible by four)
    Readable rd10 = new StringReader("5 4 5 3 4");
    StringBuilder gameRecord10 = new StringBuilder();
    MarbleSolitaireModel mockModel10 = new MockModel(gameRecord10);
    MarbleSolitaireView view10 = new MarbleSolitaireTextView(mockModel10, new StringBuilder());
    MarbleSolitaireController controller10 = new MarbleSolitaireControllerImpl(mockModel10, view10, rd10);
    Exception exception2 = assertThrows(IllegalStateException.class, () ->
            controller10.playGame());
    assertEquals(("Bad Input"), exception2.getMessage());

    // should give bad input because not playable move (less than four integers)
    Readable rd11 = new StringReader("5 4 5");
    StringBuilder gameRecord11 = new StringBuilder();
    MarbleSolitaireModel mockModel11 = new MockModel(gameRecord11);
    MarbleSolitaireView view11 = new MarbleSolitaireTextView(mockModel11, new StringBuilder());
    MarbleSolitaireController controller11 = new MarbleSolitaireControllerImpl(mockModel11, view11, rd11);
    Exception exception3 = assertThrows(IllegalStateException.class, () ->
            controller11.playGame());
    assertEquals(("Bad Input"), exception3.getMessage());



  }


  @Test
  public void testControllerConstructor() {

    AMarbleSolitaire model = new EnglishMarbleSolitaire();
    Readable readable = new StringReader("4 6 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    // pass case
    MarbleSolitaireController goodController = new MarbleSolitaireControllerImpl(
            model, view, readable);

    // testing constructor properly created object
    assertEquals(model, ((MarbleSolitaireControllerImpl) goodController).model);
    assertEquals(view, ((MarbleSolitaireControllerImpl) goodController).view);
    assertEquals(readable, ((MarbleSolitaireControllerImpl) goodController).rd);

    // exception thrown during a valid fail case, in this case if model is null
    Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
            new MarbleSolitaireControllerImpl(
                    null, view, readable));
    assertEquals(("Arguments cannot be null"), exception1.getMessage());

    // exception thrown during a valid fail case, in this case if view is null
    Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
            new MarbleSolitaireControllerImpl(
                    model, null, readable));
    assertEquals(("Arguments cannot be null"), exception2.getMessage());

    // exception thrown during a valid fail case, in this case if readable is null
    Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
            new MarbleSolitaireControllerImpl(
                    model, view, null));
    assertEquals(("Arguments cannot be null"), exception3.getMessage());
  }

  // tests controller with EnglishMarbleSolitaire constructor that has no arguments
  @Test
  public void testDefaultBoard() {
    Readable move = new StringReader("q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32", gameRecord.toString());
  }


  // tests controller with EnglishMarbleSolitaire constructor that has one argument
  @Test
  public void testCustomArmThicknessBoard() {
    Readable move = new StringReader("q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire(5);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooo_ooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "\n" +
            "Score: 84\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooo_ooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "\n" +
            "Score: 84", gameRecord.toString());
  }

  // tests controller with EnglishMarbleSolitaire constructor that has two arguments
  @Test
  public void testCustomEmptySlotBoard() {
    Readable move = new StringReader("q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire(2, 6);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "oooooo_\n" +
            "ooooooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "oooooo_\n" +
            "ooooooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32", gameRecord.toString());
  }


  // tests controller with EnglishMarbleSolitaire constructor that has three arguments
  @Test
  public void testCustomThicknessAndEmptySlotBoard() {
    Readable move = new StringReader("q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire(5,3,3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "ooo_ooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "\n" +
            "Score: 84\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "ooo_ooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "ooooooooooo\n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "   ooooo   \n" +
            "\n" +
            "Score: 84", gameRecord.toString());
  }

  // testing controller that reads one move and then quits
  @Test
  public void testOneMove() {
    Readable move = new StringReader("4 2 4 4 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31", gameRecord.toString());

  }

  // testing controller that reads one move (with letters that should be ignored) and then quits
  @Test
  public void testOneMoveWithLetters() {
    Readable move = new StringReader("4 2 w 4  a ? 4 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31", gameRecord.toString());

  }

  // testing controller that reads two moves and then quits
  @Test
  public void testTwoMoves() {
    Readable move = new StringReader("4 2 4 4 4 5 4 3 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o__oo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o__oo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 30", gameRecord.toString());

  }

  // tests two moves without quitting
  // should fail because game hasn't ended but hasn't been quit and no more ints in readable
  @Test
  public void testTwoMovesNoQuit() {
    Readable move = new StringReader("4 2 4 4 4 5 4 3");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);


    Exception exception = assertThrows(IllegalStateException.class, () ->
            controller.playGame());
    assertEquals(("Bad Input"), exception.getMessage());

  }


  // tests invalid move where from slot is empty
  @Test
  public void testInvalidMove1() {
    Readable move = new StringReader("4 4 6 4 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32", gameRecord.toString());
  }

  // tests invalid move where to slot is full
  @Test
  public void testInvalidMove2() {
    Readable move = new StringReader("4 1 4 3 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);

    controller.playGame();

    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32", gameRecord.toString());
  }


  // tests playing the game until the player loses instead of quitting
  @Test
  public void testGameUntilLoss() {
    Readable move = new StringReader("4 2 h 4 4 " +
            "4 5 hello 4 3 " + "4 7 4 5 " + "f m 2 4 4 4 " + "5 4 3 ? 4 " + "7 s 4 5 u 4");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();
    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o__oo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 30\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 29\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooo_ooo\n" +
            "o_ooo__\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 28\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooo_ooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 27\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  o_o  \n" +
            "  o_o  \n" +
            "\n" +
            "Score: 26\n" +
            "Game over!\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  o_o  \n" +
            "  o_o  \n" +
            "\n" +
            "Score: 26\n", gameRecord.toString());


  }


  // tests playing game until game is lost instead of being quit, and there are invalid moves 
  @Test
  public void testGameUntilLossWithInvalids() {
    Readable move = new StringReader("4 1 4 x 3 " + "4 2 4 4 " + "4 6 4 4 " +
            "4 5 4 3 as " + "4 7 4 s 5 " + "2 4 4 4 " + "1 4 3 w e 4 " + "5 hello 4 3 4 " + "7 4 5 4");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new EnglishMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();
    assertEquals("  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 32\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o__oooo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 31\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o__oo\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 30\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 29\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooo_ooo\n" +
            "o_ooo__\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 28\n" +
            "Invalid move. Play again.\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooo_ooo\n" +
            "o_ooo__\n" +
            "ooooooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 28\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooo_ooo\n" +
            "  ooo  \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 27\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  o_o  \n" +
            "  o_o  \n" +
            "\n" +
            "Score: 26\n" +
            "Game over!\n" +
            "  ooo  \n" +
            "  o_o  \n" +
            "ooooooo\n" +
            "o_o_o__\n" +
            "ooooooo\n" +
            "  o_o  \n" +
            "  o_o  \n" +
            "\n" +
            "Score: 26\n", gameRecord.toString());


  }

  @Test
  public void testEuropeanInitialization() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EuropeanMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);

    controller.playGame();

    assertEquals("  ooo  \n" +
            " ooooo \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            " ooooo \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            " ooooo \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            " ooooo \n" +
            "  ooo  \n" +
                    "\n" +
            "Score: 36", log.toString());
  }


  @Test
  public void testEuropeanOneMove() {
    Readable in = new StringReader("6 4 4 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EuropeanMarbleSolitaire();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);

    controller.playGame();

    assertEquals("  ooo  \n" +
            " ooooo \n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            "ooooooo\n" +
            " ooooo \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 36\n" +
            "  ooo  \n" +
            " ooooo \n" +
            "ooooooo\n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            " oo_oo \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "  ooo  \n" +
            " ooooo \n" +
            "ooooooo\n" +
            "ooooooo\n" +
            "ooo_ooo\n" +
            " oo_oo \n" +
            "  ooo  \n" +
            "\n" +
            "Score: 35", log.toString());
  }

  @Test
  public void testTriangleInitialization() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new TriangleMarbleSolitaire();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);

    controller.playGame();

    assertEquals("    _ \n" +
            "   o o \n" +
            "  o o o \n" +
            " o o o o \n" +
            "o o o o o \n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _ \n" +
            "   o o \n" +
            "  o o o \n" +
            " o o o o \n" +
            "o o o o o \n" +
            "Score: 14", log.toString());
  }


  // testing controller that reads one move and then quits
  @Test
  public void testOneTriangleMove() {
    Readable move = new StringReader("3 1 1 1 q");
    Appendable gameRecord = new StringBuilder();
    MarbleSolitaireModel model = new TriangleMarbleSolitaire();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, gameRecord);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, move);
    controller.playGame();

    assertEquals("    _ \n" +
            "   o o \n" +
            "  o o o \n" +
            " o o o o \n" +
            "o o o o o \n" +
            "Score: 14\n" +
            "    o \n" +
            "   _ o \n" +
            "  _ o o \n" +
            " o o o o \n" +
            "o o o o o \n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    o \n" +
            "   _ o \n" +
            "  _ o o \n" +
            " o o o o \n" +
            "o o o o o \n" +
            "Score: 13", gameRecord.toString());

  }


}
