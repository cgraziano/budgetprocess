package formatter;

import category.*;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FormatterTest {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

  @Test
  public void testThatFormatterThrowsExceptionIfExpectedNumberOfUnMappedCategoriesDoesNotExist() {
    Map<String, String> emptyCategoryMap = new HashMap<>();

    List<YearlyCategory> yearlyCategories = new ArrayList<>();
    YearlyCategory yearlyCategory1 = new BudgetedYearlyCategory("name1", "time", new ArrayList<MonthlyCategory>());
    YearlyCategory yearlyCategory2 = new BudgetedYearlyCategory("name2", "time", new ArrayList<MonthlyCategory>());
    yearlyCategories.add(yearlyCategory1);
    yearlyCategories.add(yearlyCategory2);

    PersonalExpensesDocFileFormatter personalExpensesDocFileFormatter = new PersonalExpensesDocFileFormatter(emptyCategoryMap, FormatTypes.CATEGORY_NAME);

    expectedException.expect(MissingFromCategoryMapException.class);
    String expectedErrorMessage = "There are 2 categories missing from the category map: name1, name2";
    expectedException.expectMessage(expectedErrorMessage);
    personalExpensesDocFileFormatter.format(yearlyCategories, Month.JANUARY);
  }

  @Test
  public void testCategoryNameFormatTypes() {

    FormatTypes formatType = FormatTypes.CATEGORY_NAME;
    String budgetedName1 = "name1";
    String budgetedName2 = "name2";
    Map<String, String> emptyCategoryMap = new HashMap<>();

    MonthlyCategory monthlyCategory1 = new BudgetedMonthlyCategory(budgetedName1, Month.JANUARY, BigDecimal.ZERO, BigDecimal.ZERO);
    MonthlyCategory monthlyCategory2 = new BudgetedMonthlyCategory(budgetedName2, Month.JANUARY, BigDecimal.ZERO, BigDecimal.ZERO);
    List<MonthlyCategory> monthlyCategories1 = new ArrayList<>();
    List<MonthlyCategory> monthlyCategories2 = new ArrayList<>();
    monthlyCategories1.add(monthlyCategory1);
    monthlyCategories2.add(monthlyCategory2);

    List<YearlyCategory> yearlyCategories = new ArrayList<>();
    YearlyCategory yearlyCategory1 = new BudgetedYearlyCategory(budgetedName1, "time", monthlyCategories1);
    YearlyCategory yearlyCategory2 = new BudgetedYearlyCategory(budgetedName2, "time", monthlyCategories2);
    yearlyCategories.add(yearlyCategory1);
    yearlyCategories.add(yearlyCategory2);

    int expectedNumberOfUnMappedCategories = 2;
    PersonalExpensesDocFileFormatter personalExpensesDocFileFormatter = new PersonalExpensesDocFileFormatter(emptyCategoryMap, formatType, expectedNumberOfUnMappedCategories);
    List<String> output = personalExpensesDocFileFormatter.format(yearlyCategories, Month.JANUARY);
    String expectedString1 = budgetedName1;
    String expectedString2 = budgetedName2;
    Assert.assertEquals(expectedString1, output.get(0));
    Assert.assertEquals(expectedString2, output.get(1));


    formatType = FormatTypes.CATEGORY_NAME_BY_DIFFERENCE;
    personalExpensesDocFileFormatter = new PersonalExpensesDocFileFormatter(emptyCategoryMap, formatType, expectedNumberOfUnMappedCategories);
    output = personalExpensesDocFileFormatter.format(yearlyCategories, Month.JANUARY);

    String expectedValue = "\t0\n";
    expectedString1 = budgetedName1 + expectedValue;
    expectedString2 = budgetedName2 + expectedValue;
    Assert.assertEquals(expectedString1, output.get(0));
    Assert.assertEquals(expectedString2, output.get(1));


    formatType = FormatTypes.CATEGORY_NAME_BY_BUDGETED_SPENT_DIFFERENCE;
    personalExpensesDocFileFormatter = new PersonalExpensesDocFileFormatter(emptyCategoryMap, formatType, expectedNumberOfUnMappedCategories);
    output = personalExpensesDocFileFormatter.format(yearlyCategories, Month.JANUARY);

    expectedValue = "\t0\t0\t0\n";
    expectedString1 = budgetedName1 + expectedValue;
    expectedString2 = budgetedName2 + expectedValue;
    Assert.assertEquals(expectedString1, output.get(0));
    Assert.assertEquals(expectedString2, output.get(1));
  }
}
