package formatter;

import category.Month;
import category.YearlyCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonalExpensesDocFileFormatter implements Formatter {
  private Map<String, String> categoryMap;
  private FormatTypes formatType;
  private int expectedNumberOfUnMappedCategories;
  private static final String REMOVE = "REMOVE";

  /**
   * If a category is not found within map, the un-mapped categories will be
   * accumulated in a MissingFromCategoryMapException message.
   * If the category maps to "REMOVE", the category is not printed in the final output.
   * @param categoryMap
   */
  public PersonalExpensesDocFileFormatter(Map<String, String> categoryMap, FormatTypes formatType) {
    setCategoryMap(categoryMap);
    setFormatType(formatType);
    setExpectedNumberOfUnMappedCategories(0);
  }

  /**
   * If a category is not found within map, the number un-mapped categories will be
   * compared against the expected number of un-mapped categories. If the
   * the expected number of un-mapped categories does not match the actual
   * number of un-mapped categories, then the un-mapped categories will be
   * accumulated in a MissingFromCategoryMapException message.
   * If the category maps to "REMOVE", the category is not printed in the final output.
   * @param categoryMap
   */
  public PersonalExpensesDocFileFormatter(Map<String, String> categoryMap, FormatTypes formatType,
                                          int expectedNumberOfUnMappedCategories) {
    setCategoryMap(categoryMap);
    setFormatType(formatType);
    setExpectedNumberOfUnMappedCategories(expectedNumberOfUnMappedCategories);
  }

  @Override
  public List<String> format(List<YearlyCategory> yearlyCategories, Month month) {
    checkThatAllCategoriesHaveMapping(yearlyCategories);
    List<YearlyCategory> finalYearlyCategories = performRemoveAction(yearlyCategories);
    for (YearlyCategory yearlyCategory : finalYearlyCategories) {
      String yearlyCategoryName = yearlyCategory.getNameOfBudgeted();
      String formattedName = formatCategoryName(yearlyCategoryName);
      yearlyCategory.setNameOfBudgeted(formattedName);
      yearlyCategory.setNameOfMonthlyCategories(formattedName);
    }
    List<String> formattedLines = finalYearlyCategories.stream()
            .map(x -> x.getMonthlyCategory(month))
            .map(getFormatType().getFullFunction())
            .collect(Collectors.toList());
    return formattedLines;
  }

  public Map<String, String> getCategoryMap() {
    return categoryMap;
  }

  public void setCategoryMap(Map<String, String> categoryMap) {
    this.categoryMap = new HashMap<>(categoryMap);
  }

  public FormatTypes getFormatType() {
    return formatType;
  }

  public void setFormatType(FormatTypes formatType) {
    this.formatType = formatType;
  }

  public int getExpectedNumberOfUnMappedCategories() {
    return expectedNumberOfUnMappedCategories;
  }

  public void setExpectedNumberOfUnMappedCategories(int expectedNumberOfUnMappedCategories) {
    this.expectedNumberOfUnMappedCategories = expectedNumberOfUnMappedCategories;
  }

  private boolean shouldYearlyCategoryBeRemoved(YearlyCategory yearlyCategory) {
    boolean shouldRemove = false;
    String name = yearlyCategory.getNameOfBudgeted();
    String mappedName = getCategoryMap().get(name);
    if (mappedName != null && mappedName == REMOVE) {
      shouldRemove = true;
    }
    return shouldRemove;
  }

  private List<YearlyCategory> performRemoveAction(List<YearlyCategory> yearlyCategories) {
    return yearlyCategories.stream()
            .filter(x -> !shouldYearlyCategoryBeRemoved(x))
            .collect(Collectors.toList());
  }

  /**
   * If original name is not found in category mapping, then no modification is done ot the name.
   * Assuming that REMOVE filtering has already been done.
   * @param originalName
   * @return
   */
  private String formatCategoryName(String originalName) {
    return getCategoryMap().containsKey(originalName) ? getCategoryMap().get(originalName) : originalName;
  }

  private void checkThatAllCategoriesHaveMapping(List<YearlyCategory> yearlyCategories) {
    List<YearlyCategory> categoriesWithoutMap = yearlyCategories
            .stream()
            .filter(x -> !getCategoryMap().containsKey(x.getNameOfBudgeted()))
            .collect(Collectors.toList());
    if (!categoriesWithoutMap.isEmpty() && categoriesWithoutMap.size() != getExpectedNumberOfUnMappedCategories()) {
      StringBuilder builder = new StringBuilder("There are " );
      builder.append(categoriesWithoutMap.size());
      builder.append(" categories missing from the category map: ");
      builder.append(categoriesWithoutMap
              .stream()
              .map(x -> x.getNameOfBudgeted())
              .collect(Collectors.joining(", ")));
      String errorMessage = builder.toString();
      throw new MissingFromCategoryMapException(errorMessage);
    }


  }
}
