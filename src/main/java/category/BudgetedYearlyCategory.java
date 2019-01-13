package category;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of YearlyCategory
 */
public class BudgetedYearlyCategory implements YearlyCategory {
  private String name;
  private String time;
  private List<MonthlyCategory> monthlyCategories;

  /**
   * Contains all monthly categories.
   * @param name
   * @param time
   * @param monthlyCategories
   */
  public BudgetedYearlyCategory(String name, String time, List<MonthlyCategory> monthlyCategories) {
    setMonthlyCategories(monthlyCategories);
    setNameOfBudgeted(name);
    setTimeOfBudgeted(time);
  }

  @Override
  public String getNameOfBudgeted() {
    return name;
  }

  @Override
  public String getTimeOfBudgeted() {
    return time;
  }

  @Override
  public List<MonthlyCategory> getMonthlyCategories() {
    return monthlyCategories;
  }

  @Override
  public void setNameOfBudgeted(String name) {
    this.name = name;
  }

  @Override
  public void setTimeOfBudgeted(String time) {
    this.time = time;
  }

  @Override
  public MonthlyCategory getMonthlyCategory(Month month) {
    return getMonthlyCategories().stream()
            .filter(x -> x.getTimeOfBudgeted().equals(month))
            .collect(Collectors.toList()).get(0);
  }

  private void setMonthlyCategories(List<MonthlyCategory> monthlyCategories) {
    this.monthlyCategories = monthlyCategories;
  }

  @Override
  public void setNameOfMonthlyCategories(String name) {
    for (MonthlyCategory monthlyCategory : getMonthlyCategories()) {
      monthlyCategory.setNameOfBudgeted(name);
    }
  }
}
