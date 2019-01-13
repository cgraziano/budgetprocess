package runner;

import category.Month;
import category.YearlyCategory;
import formatter.FormatTypes;
import formatter.Formatter;
import formatter.PersonalExpensesDocFileFormatter;
import parser.Parser;
import parser.QuickenParser;
import processor.Processor;
import processor.QuickenProcessor;
import reader.FileContentsExtractor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
  private static final String REMOVE = "REMOVE";
  public static void main(String[] args) {
    String filePath = "C:\\Users\\Chris\\Desktop\\TempQuicken\\Book1.txt";
    String year = "2018";
    Month month = Month.OCTOBER;
    Map<String, String> categoryMap = defineStandardCategoryMap();
    FormatTypes formatType = FormatTypes.CATEGORY_NAME_BY_BUDGETED_SPENT_DIFFERENCE;
    writeFormattedQuickenFile(categoryMap, formatType, filePath, year, month);

  }

  private static void writeFormattedQuickenFile(Map<String, String> categoryMap,
                                                FormatTypes formatTypes,
                                                String filePath,
                                                String year,
                                                Month month) {
    Processor processor = new QuickenProcessor();
    List<YearlyCategory> yearlyCategories = processor.process(filePath, year);

    Formatter formatter = new PersonalExpensesDocFileFormatter(categoryMap, formatTypes);
    List<String> months = formatter.format(yearlyCategories, month);
    try (FileWriter fileWriter = new FileWriter("C:\\Users\\Chris\\Desktop\\TempQuicken\\output.txt")) {
      for (String string : months) {
        fileWriter.write(string);
      }

    } catch (IOException exception) {

    }

    System.out.println("hi");

  }

  private static Map<String, String> defineStandardCategoryMap() {
    Map<String, String> categoryMap = new HashMap<>();
    categoryMap.put("Auto & Transport", REMOVE);
    categoryMap.put("Auto & Transport:Car Insurance", "Car Insurance");
    categoryMap.put("Auto & Transport:Car Registration", "Car Registration");
    categoryMap.put("Auto & Transport:Car Wash", "Car Wash");
    categoryMap.put("Auto & Transport:Gas & Fuel", "Gas");
    categoryMap.put("Auto & Transport:OnStar", "Roadside Service");
    categoryMap.put("Auto & Transport:Parking", "Parking");
    categoryMap.put("Auto & Transport:Public Transportation", "Public Transportation");
    categoryMap.put("Auto & Transport:Service & Parts", "Car Service");
    categoryMap.put("Bills & Utilities", REMOVE);
    categoryMap.put("Bills & Utilities:Cable and Internet", "Cable & Internet");
    categoryMap.put("Bills & Utilities:Cell Phone", "Cell Phone");
    categoryMap.put("Bills & Utilities:Electricity", "Utilities");
    categoryMap.put("Bills & Utilities:Internet", REMOVE);
    categoryMap.put("Bills & Utilities:Iphone Cloud Service", "Cloud Service");
    categoryMap.put("Bills & Utilities:Rent", "Rent");
    categoryMap.put("Bills & Utilities:Renters Insurance", "Renter's Insurance");
    categoryMap.put("Clothes Cleaning", REMOVE);
    categoryMap.put("Clothes Cleaning:Dry Cleaning", "Dry Cleaning");
    categoryMap.put("CreditCardFees", REMOVE);
    categoryMap.put("CreditCardFees:Chase Annual Membership Fee", "Chase Credit Card Annual Fee");
    categoryMap.put("CreditCardFees:SPG Annual Membership Fee", "SPG Credit Card Annual Fee");
    categoryMap.put("Dads Tickets", REMOVE);
    categoryMap.put("SavingsCategories:Date", "Date");
    categoryMap.put("Disability Insurance SPE", REMOVE);
    categoryMap.put("Date:Entertainment", REMOVE);
    categoryMap.put("Entertainment:Netflix", "Streaming Service");
    categoryMap.put("Food & Dining", REMOVE);
    categoryMap.put("SavingsCategories:Groceries", "Groceries");
    categoryMap.put("Food & Dining:Restaurants", "Restaurants");
    categoryMap.put("For yourself Savings", "For Yourself Savings");
    categoryMap.put("Gifts & Donations", REMOVE);
    categoryMap.put("Gifts & Donations:Charity", "Charity");
    categoryMap.put("Gifts For Laura", "Girlfriend Gifts");
    categoryMap.put("Gifts For Others", "Gifts for Others");
    categoryMap.put("Hair cut", "Hair Cut");
    categoryMap.put("Health & Fitness", REMOVE);
    categoryMap.put("Health & Fitness:Dentist", "Dentist");
    categoryMap.put("Health & Fitness:Health Insurance", REMOVE);
    categoryMap.put("Health & Fitness:Health Just in Case", "Health Care");
    categoryMap.put("Health & Fitness:Vision", "Vision");
    categoryMap.put("SavingsCategories:Investments", REMOVE);
    categoryMap.put("Investments:IRA Contribution (DWM LPL)", "Roth IRA");
    categoryMap.put("Investments:Personal", "Investments");
    categoryMap.put("LifeLock", "ID Protection");
    categoryMap.put("Office Supplies Yourself", "Home Office");
    categoryMap.put("SavingsCategories:Personal Care", "Personal");
    categoryMap.put("Research", REMOVE);
    categoryMap.put("Research:SEG membership", "SEG Membership");
    categoryMap.put("Shopping", REMOVE);
    categoryMap.put("Shopping:Clothing", "Clothes");
    categoryMap.put("Tax", "Tax");
    categoryMap.put("SavingsCategories:Tickets", "Tickets");
    categoryMap.put("SavingsCategories:Vacation", "Vacation");
    return categoryMap;
  }

}
