package reader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class GenericFileReaderTest {

  @Rule public ExpectedException expectedException = ExpectedException.none();
  private final String filePath = "file/not/found.txt";

  @Test
  public void readerExceptionThrownWhenFileIsNotFound() {
    String exceptionMessage = "An issue was found with locating and/or reading the file specified (" +
            filePath + ").";
    expectedException.expect(ReaderException.class);
    expectedException.expectMessage(exceptionMessage);
    FileContentsExtractor.readFile(filePath);

  }

  @Test
  public void testFileReadInCorrectly() {
    List<String> linesOfFile = FileContentsExtractor.readFile("target/test-classes/testReadingFile.txt");

    int expectedNumberOfLines = 7;
    int actualNumberOfLines = linesOfFile.size();
    assertEquals(expectedNumberOfLines, actualNumberOfLines);

    List<String> expectedListOfLines = new ArrayList<>();
    expectedListOfLines.add("abc");
    expectedListOfLines.add("a b c");
    expectedListOfLines.add("a   b   c");
    expectedListOfLines.add("");
    expectedListOfLines.add("efg");
    expectedListOfLines.add("e f g");
    expectedListOfLines.add("e   f   g");
    assertTrue(expectedListOfLines.equals(linesOfFile));


  }

}
