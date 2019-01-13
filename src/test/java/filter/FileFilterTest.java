package filter;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileFilterTest {

  @Test
  public void testThatEmptyStringsAreFiltered() {
    String notNeeded = "Current Budget - Year to Date\n";
    String category = "\tCategory\t Actual\tJanuary 2018 Budget\t Difference\t\t Actual\tFebruary 2018 Budget\t";
    String expenseHeader = "\tEXPENSES\t6,026.53\t4,151.11\t-1,875.42\t\t3,144.85\t4,995.55\t1,850.70\t\t2,338.06\t";
    String expense = "\tAuto & Transport\t254.90\t477.12\t222.23\t\t236.41\t572.77\t336.36\t\t341.08\t358.77\t17.69\t";
    String expense1 = "\tSavingsCategories:Tickets\t0.00\t0.00\t0.00\t\t0.00\t0.00\t0.00\t\t0.00\t0.00\t0.00\t\t549.";
    String expense2 = "\tSavingsCategories:Vacation\t1,910.10\t190.00\t-1,720.10\t\t264.62\t288.79\t24.17\t\t609.48";
    String netDifference = "\tNet Difference:\t-6,026.53\t-4,151.11\t-1,875.42\t\t-3,144.85\t-4,995.55\t1,850.70";
    List<String> stringToFilter = new ArrayList<>();
    stringToFilter.add(notNeeded);
    stringToFilter.add(notNeeded);
    stringToFilter.add(category);
    stringToFilter.add(notNeeded);
    stringToFilter.add(expenseHeader);
    stringToFilter.add(expense);
    stringToFilter.add(expense1);
    stringToFilter.add(expense2);
    stringToFilter.add(notNeeded);
    stringToFilter.add(netDifference);

    FileFilter fileFilter = new QuickenFilter();
    List<String> filteredListOfString = fileFilter.filter(stringToFilter);

    int expectedLengthOfList = 4;
    assertEquals(expectedLengthOfList, filteredListOfString.size());

    assertEquals(category.replaceFirst("\t",""), filteredListOfString.get(0));
    assertEquals(expense.replaceFirst("\t",""), filteredListOfString.get(1));
    assertEquals(expense1.replaceFirst("\t",""), filteredListOfString.get(2));
    assertEquals(expense2.replaceFirst("\t",""), filteredListOfString.get(3));
  }

}
