package formatter;

import category.MonthlyCategory;

import java.util.function.Function;

public enum FormatTypes {
  CATEGORY_NAME(x -> x.getNameOfBudgeted()),
  CATEGORY_NAME_BY_DIFFERENCE(x -> x.getNameOfBudgeted() + "\t"
          + x.diffBtwnBudgetedAndAmountSpent().toPlainString() + "\n"),
  CATEGORY_NAME_BY_BUDGETED_SPENT_DIFFERENCE(x -> x.getNameOfBudgeted() + "\t"
          + x.getBudgetedAmount().toPlainString() + "\t"
          + x.getAmountSpent().toPlainString() + "\t"
          + x.diffBtwnBudgetedAndAmountSpent() + "\n");

  private Function<MonthlyCategory, String> fullFunction;
  FormatTypes(Function<MonthlyCategory, String> fullFunction) {
    setFullFunction(fullFunction);
  }

  public Function<MonthlyCategory, String> getFullFunction() {
    return fullFunction;
  }

  private void setFullFunction(Function<MonthlyCategory, String> fullFunction) {
    this.fullFunction = fullFunction;
  }
}
