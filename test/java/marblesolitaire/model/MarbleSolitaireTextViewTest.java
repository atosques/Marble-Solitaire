package marblesolitaire.model.hw02;

import org.junit.jupiter.api.Test;

import marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.jupiter.api.Assertions.*;

public class MarbleSolitaireTextViewTest {
  // test constructor to see if it throws an exception
  // test toString method
  MarbleSolitaireTextView english1 = new MarbleSolitaireTextView(new EnglishMarbleSolitaire());
  MarbleSolitaireTextView english2 = new MarbleSolitaireTextView(new EnglishMarbleSolitaire(5));
  MarbleSolitaireTextView european1 = new MarbleSolitaireTextView(new EuropeanMarbleSolitaire());
  MarbleSolitaireTextView european2 = new MarbleSolitaireTextView(new EuropeanMarbleSolitaire(5));
  MarbleSolitaireTextView triangle1 = new MarbleSolitaireTextView(new TriangleMarbleSolitaire());
  MarbleSolitaireTextView triangle2 = new MarbleSolitaireTextView(new TriangleMarbleSolitaire(7));


  @Test
  void testToString() {
    String english1String = english1.toString();
    assertEquals(
            "  ooo  \n" +
                    "  ooo  \n" +
                    "ooooooo\n" +
                    "ooo_ooo\n" +
                    "ooooooo\n" +
                    "  ooo  \n" +
                    "  ooo  \n", english1String);

  }

  @Test
  void testToString2() {
    String european1String = european1.toString();
    assertEquals(
            "  ooo  \n" +
                    " ooooo \n" +
                    "ooooooo\n" +
                    "ooo_ooo\n" +
                    "ooooooo\n" +
                    " ooooo \n" +
                    "  ooo  \n", european1String);

  }


  @Test
  void testToString3() {
    String english2String = english2.toString();
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
            "   ooooo   \n", english2String);

  }

  @Test
  void testToString4() {
    String european2String = european2.toString();
    assertEquals("    ooooo    \n" +
            "   ooooooo   \n" +
            "  ooooooooo  \n" +
            " ooooooooooo \n" +
            "ooooooooooooo\n" +
            "ooooooooooooo\n" +
            "oooooo_oooooo\n" +
            "ooooooooooooo\n" +
            "ooooooooooooo\n" +
            " ooooooooooo \n" +
            "  ooooooooo  \n" +
            "   ooooooo   \n" +
            "    ooooo    \n", european2String);

  }

  @Test
  void testToString5() {
    String triangle1String = triangle1.toString();
    assertEquals("_    \n" +
            "oo   \n" +
            "ooo  \n" +
            "oooo \n" +
            "ooooo\n" , triangle1String);

  }

  @Test
  void testToString6() {
    String triangle2String = triangle2.toString();
    assertEquals("_      \n" +
            "oo     \n" +
            "ooo    \n" +
            "oooo   \n" +
            "ooooo  \n" +
            "oooooo \n" +
            "ooooooo\n", triangle2String);

  }


  @Test
  void testMarbleSolitaireTextViewConstructor() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new MarbleSolitaireTextView(null));
    assertEquals("state is null", exception.getMessage());
  }




}
