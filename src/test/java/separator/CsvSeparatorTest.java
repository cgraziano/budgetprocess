package separator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CsvSeparatorTest {
  private static final String DELIMITER = ",";

  @Test
  public void aStringIsSeparated() {
    String stringToSeparate = "a" + DELIMITER + "b" + DELIMITER + "c";

    CsvSeparator csvSeparator = new CsvSeparator();
    String[] actualSeparatedString = csvSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {"a", "b", "c"};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }

  @Test
  public void blankStringWithoutADelimeterResultsInOneArrayElementThatIsABlankString() {
    String stringToSeparate = "";

    CsvSeparator csvSeparator = new CsvSeparator();
    String[] actualSeparatedString = csvSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {""};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }

  @Test
  public void blankStringWithADelimeterResultsInTwoElementsThatAreBothBlankStrings() {
    String stringToSeparate = DELIMITER;

    CsvSeparator csvSeparator = new CsvSeparator();
    String[] actualSeparatedString = csvSeparator.separate(stringToSeparate);
    String[] expectedSeparatedString = {"",""};

    assertArrayEquals(expectedSeparatedString, actualSeparatedString);
  }
}
