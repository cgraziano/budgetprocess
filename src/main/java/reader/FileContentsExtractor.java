package reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public interface FileContentsExtractor {
  static final Logger logger = LogManager.getLogger();
  /**
   * Will split the specified file into a list of Strings where each String is equivalent to a
   * line within the file.
   * @param filePath
   * @return
   */
  static List<String> readFile(String filePath) {
    List<String> linesOfAFile = new ArrayList<>();
    try (FileReader fileReader = new FileReader(filePath)) {
      linesOfAFile = readAllLines(fileReader);

    } catch (IOException iOException) {
      logger.error(iOException.getStackTrace());
      throw new ReaderException("An issue was found with locating and/or reading the file specified (" +
              filePath + ").", iOException);

    }
    return linesOfAFile;

  }

  /**
   * Uses a Reader to extract all lines of the file within the Reader to a List of Strings
   * @param reader
   * @return
   * @throws IOException
   */
  static List<String> readAllLines(Reader reader) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(reader);

    boolean endOfLineReached = false;
    List<String> linesOfAFile = new ArrayList<>();
    while (!endOfLineReached) {
      String line = bufferedReader.readLine();
      if (line == null) {
        endOfLineReached = true;
      } else {
        linesOfAFile.add(line);
      }
    }
    return linesOfAFile;
  }
}
