package separator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TabSeparatorTest {
  private static final String DELIMITER = "\t";

  @Test
  public void aStringIsSeparated() {
    String stringToSeparate = "a" + DELIMITER + "b" + DELIMITER + "c";

    TabSeparator tabSeparator = new TabSeparator();
    String[] actualSeparatedString = tabSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {"a", "b", "c"};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }

  @Test
  public void blankStringWithoutADelimeterResultsInOneArrayElementThatIsABlankString() {
    String stringToSeparate = "";

    TabSeparator tabSeparator = new TabSeparator();
    String[] actualSeparatedString = tabSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {""};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }

  @Test
  public void blankStringWithADelimeterResultsInTwoElementsThatAreBothBlankStrings() {
    String stringToSeparate = DELIMITER;

    TabSeparator tabSeparator = new TabSeparator();
    String[] actualSeparatedString = tabSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {"",""};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }
}
