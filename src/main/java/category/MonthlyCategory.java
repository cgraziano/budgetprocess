package category;

import parser.QuickenMonths;

import java.math.BigDecimal;
import java.util.List;

public interface MonthlyCategory {
  String getNameOfBudgeted();
  Month getTimeOfBudgeted();
  void setNameOfBudgeted(String name);
  void setTimeOfBudgeted(Month time);
  BigDecimal getBudgetedAmount();
  BigDecimal getAmountSpent();
  default BigDecimal diffBtwnBudgetedAndAmountSpent() {
    return getBudgetedAmount().subtract(getAmountSpent());
  }
}
