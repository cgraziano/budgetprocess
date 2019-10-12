package formatter;

import category.BudgetedYearlyCategory;
import category.Month;
import category.MonthlyCategory;
import category.YearlyCategory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatterTest {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testThatFormatterThrowsExceptionIfMappingDoesNotExist() {
    Map<String, String> emptyCategoryMap = new HashMap<>();

    List<YearlyCategory> yearlyCategories = new ArrayList<>();
    YearlyCategory yearlyCategory1 = new BudgetedYearlyCategory("name1", "time", new ArrayList<MonthlyCategory>());
    YearlyCategory yearlyCategory2 = new BudgetedYearlyCategory("name2", "time", new ArrayList<MonthlyCategory>());
    yearlyCategories.add(yearlyCategory1);
    yearlyCategories.add(yearlyCategory2);

    PersonalExpensesDocFileFormatter personalExpensesDocFileFormatter = new PersonalExpensesDocFileFormatter(emptyCategoryMap, FormatTypes.CATEGORY_NAME);

    expectedException.expect(MissingFromCategoryMapException.class);
    String expectedErrorMessage = "The following categories are missing from the category map: name1, name2";
    expectedException.expectMessage("");
    personalExpensesDocFileFormatter.format(yearlyCategories, Month.JANUARY);




  }
}
