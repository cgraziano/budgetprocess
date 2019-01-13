package processor;

import category.YearlyCategory;

import java.util.List;
import java.util.Map;

/**
 * Combines file reading, filtering, and parsing into one step.
 */
public interface Processor {
  List<YearlyCategory> process(String fileName, String year);
}
