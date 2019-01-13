package parser;

import category.Month;
import category.MonthlyCategory;
import category.YearlyCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

public class QuickenParserTest {
  private String categoryLine;
  private String expenseLine;
  private String expectedCategoryName;

  @Before
  public void setup() {
    categoryLine = "Category\t Actual\tJanuary 2018 Budget\t Difference\t\t Actual\tFebruary 2018 Budget\t Difference\t\t Actual\tMarch 2018 Budget\t Difference\t\t Actual\tApril 2018 Budget\t Difference\t\t Actual\tMay 2018 Budget\t Difference\t\t Actual\tJune 2018 Budget\t Difference\t\t Actual\tJuly 2018 Budget\t Difference\t\t Actual\tAugust 2018 Budget\t Difference\t\t Actual\tSeptember 2018 Budget\t Difference\t\t Actual\tOctober 2018 Budget\t Difference\t\t Actual\tNovember 2018 Budget\t Difference\t\t1/1/2018 Actual\t- Budget\t11/17/2018 Difference\t\t\n";
    expectedCategoryName = "Auto & Transport";
    expenseLine = expectedCategoryName+ "\t255.90\t477.12\t222.23\t\t236.41\t572.77\t336.36\t\t341.08\t358.77\t17.69\t\t201.14\t441.55\t240.41\t\t246.85\t438.77\t191.92\t\t296.31\t458.77\t162.46\t\t257.00\t408.77\t151.77\t\t2,756.75\t559.32\t-2,197.43\t\t296.14\t558.77\t262.63\t\t484.72\t358.77\t-125.95\t\t69.00\t203.30\t134.30\t\t5,440.30\t4,836.68\t-603.62\n";
  }

  @Test
  public void testQuickenParserCorrectlyParsesJanuaryMonthlyCategory() {
    Month expectedMonth = Month.JANUARY;
    Parser parser = new QuickenParser(categoryLine);
    YearlyCategory yearlyCategory = parser.parse( "2018", expenseLine);
    MonthlyCategory monthlyCategory = yearlyCategory.getMonthlyCategory(expectedMonth);
    testMonthAndCategoryNamedAreCorrectlyRetrieved(monthlyCategory, expectedMonth, expectedCategoryName);

    BigDecimal expectedBudgetedAmount = new BigDecimal("477.12");
    BigDecimal actualBudgetedAmount = monthlyCategory.getBudgetedAmount();
    Assert.assertEquals(expectedBudgetedAmount, actualBudgetedAmount);

    BigDecimal expectedAmountSpent = new BigDecimal("255.90");
    BigDecimal actualAmountSpent = monthlyCategory.getAmountSpent();
    Assert.assertEquals(expectedAmountSpent, actualAmountSpent);

    BigDecimal expectedDiff = new BigDecimal("221.22");
    BigDecimal actualDiff = monthlyCategory.diffBtwnBudgetedAndAmountSpent();
    Assert.assertEquals(expectedDiff, actualDiff);

  }

  @Test
  public void testQuickenParserCorrectlyFindsAprilMonthlyCategory() {
    Month expectedMonth = Month.APRIL;
    Parser parser = new QuickenParser(categoryLine);
    YearlyCategory yearlyCategory = parser.parse( "2018", expenseLine);
    MonthlyCategory monthlyCategory = yearlyCategory.getMonthlyCategory(expectedMonth);
    testMonthAndCategoryNamedAreCorrectlyRetrieved(monthlyCategory, expectedMonth, expectedCategoryName);

    BigDecimal expectedBudgetedAmount = new BigDecimal("441.55");
    BigDecimal actualBudgetedAmount = monthlyCategory.getBudgetedAmount();
    Assert.assertEquals(expectedBudgetedAmount, actualBudgetedAmount);

    BigDecimal expectedAmountSpent = new BigDecimal("201.14");
    BigDecimal actualAmountSpent = monthlyCategory.getAmountSpent();
    Assert.assertEquals(expectedAmountSpent, actualAmountSpent);

    BigDecimal expectedDiff = new BigDecimal("240.41");
    BigDecimal actualDiff = monthlyCategory.diffBtwnBudgetedAndAmountSpent();
    Assert.assertEquals(expectedDiff, actualDiff);


  }

  @Test
  public void testQuickenParserCorrectlyFindsNovemberMonthlyCategory() {
    Month expectedMonth = Month.NOVEMBER;
    Parser parser = new QuickenParser(categoryLine);
    YearlyCategory yearlyCategory = parser.parse( "2018", expenseLine);
    MonthlyCategory monthlyCategory = yearlyCategory.getMonthlyCategory(expectedMonth);
    testMonthAndCategoryNamedAreCorrectlyRetrieved(monthlyCategory, expectedMonth, expectedCategoryName);

    BigDecimal expectedBudgetedAmount = new BigDecimal("203.30");
    BigDecimal actualBudgetedAmount = monthlyCategory.getBudgetedAmount();
    Assert.assertEquals(expectedBudgetedAmount, actualBudgetedAmount);

    BigDecimal expectedAmountSpent = new BigDecimal("69.00");
    BigDecimal actualAmountSpent = monthlyCategory.getAmountSpent();
    Assert.assertEquals(expectedAmountSpent, actualAmountSpent);

    BigDecimal expectedDiff = new BigDecimal("134.30");
    BigDecimal actualDiff = monthlyCategory.diffBtwnBudgetedAndAmountSpent();
    Assert.assertEquals(expectedDiff, actualDiff);

  }

  private void testMonthAndCategoryNamedAreCorrectlyRetrieved(MonthlyCategory monthlyCategory, Month expectedMonth, String expectedCategoryName) {
    Month actualMonth = monthlyCategory.getTimeOfBudgeted();
    Assert.assertEquals(expectedMonth, actualMonth);

    String categoryName = monthlyCategory.getNameOfBudgeted();
    Assert.assertEquals(expectedCategoryName, categoryName);
  }
}
