package parser;

import category.BudgetedMonthlyCategory;
import category.BudgetedYearlyCategory;
import category.MonthlyCategory;
import category.YearlyCategory;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class QuickenParser implements Parser {
  private static final String DELIMETER = "\t";
  private List<Pair<QuickenMonths, Integer>> indicesByMonths;

  /**
   *
   * @param categoryString String of category line from Quicken file.
   */
  public QuickenParser(String categoryString) {
    indicesByMonths = identifyBudgetIndices(categoryString);
  }

  @Override
  public YearlyCategory parse(String categoryYear, String expenseString) {
    String[] splitExpenseString = expenseString.split(DELIMETER);
    String categoryName = splitExpenseString[0];
    List<MonthlyCategory> monthlyCategories = indicesByMonths.stream()
                   .map(x -> new BudgetedMonthlyCategory(categoryName, x.getKey(), new BigDecimal(splitExpenseString[x.getValue().intValue()].replace(",","")), new BigDecimal(splitExpenseString[x.getValue().intValue()-1].replace(",",""))) )
                   .collect(Collectors.toList());
    return new BudgetedYearlyCategory(categoryName, categoryYear, monthlyCategories);
  }



  private List<Pair<QuickenMonths, Integer>> identifyBudgetIndices(String categories) {
    String[] splitString = categories.split(DELIMETER);
    int numberOfSplit = splitString.length;
    List<Pair<QuickenMonths, Integer>> listOfIndicesByMonths = new ArrayList<>();
    for (int i = 0; i < numberOfSplit; ++i) {
      String word = splitString[i];
      if (QuickenMonths.doesStringContainAnEnum(word)) {
        QuickenMonths monthFound = QuickenMonths.whatEnumDoesTheStringContain(word);
        listOfIndicesByMonths.add(new Pair<>(monthFound, i));
      }
    }
    return listOfIndicesByMonths;
  }

}


