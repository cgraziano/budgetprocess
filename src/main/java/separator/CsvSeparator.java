package separator;

public class CsvSeparator implements Separator {
  private static final String DELIMITER = ",";
  private static final int SPLIT_THRESHOLD = -1;//applies delimeter as many times a possible and
                                          // keeps empty zeros from being discarded (see split documentation)
  @Override
  public String[] separate(String string) {
    return string.split(DELIMITER, SPLIT_THRESHOLD);
  }
}
