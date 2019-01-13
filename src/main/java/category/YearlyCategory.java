package category;

import java.math.BigDecimal;
import java.util.List;

public interface YearlyCategory {
    String getNameOfBudgeted();
    String getTimeOfBudgeted();
    void setNameOfBudgeted(String name);
    void setTimeOfBudgeted(String time);
    List<MonthlyCategory> getMonthlyCategories();
    MonthlyCategory getMonthlyCategory(Month month);
    void setNameOfMonthlyCategories(String name);
}
