package processor;

import category.YearlyCategory;
import filter.FileFilter;
import filter.QuickenFilter;
import parser.Parser;
import parser.QuickenParser;
import reader.FileContentsExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuickenProcessor implements Processor {
  @Override
  public List<YearlyCategory> process(String fileName, String year) {
    List<String> fileContents = FileContentsExtractor.readFile(fileName);
    FileFilter fileFilter = new QuickenFilter();
    List<String> filteredContents = fileFilter.filter(fileContents);
    String categories = filteredContents.get(0);
    List<String> rawExpenses = filteredContents.subList(1, filteredContents.size());
    List<String> expenses = modifyExpensesString(rawExpenses);
    Parser parser = new QuickenParser(categories);
    List<YearlyCategory> yearlyCategories = expenses.stream().map(x -> parser.parse(year, x)).collect(Collectors.toList());
    return yearlyCategories;
  }

  private List<String> modifyExpensesString(List<String> expensesStrings) {
    List<String> modifiedStrings = new ArrayList<>();
    for (String string : expensesStrings) {
      modifiedStrings.add(string.replace("\"", ""));
    }
    return modifiedStrings;
  }
}
