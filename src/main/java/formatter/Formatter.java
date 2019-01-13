package formatter;

import category.Month;
import category.YearlyCategory;

import java.util.List;

public interface Formatter {
  public List<String> format(List<YearlyCategory> yearlyCategories, Month month);
}
