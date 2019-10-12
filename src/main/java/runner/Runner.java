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
    String year = "2019";
    Month month = Month.SEPTEMBER;
    Map<String, String> categoryMap = defineIndividaulCategoryMap();
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

  private static Map<String, String> defineIndividaulCategoryMap() {
    Map<String, String> categoryMap = new HashMap<>();
    categoryMap.put("Auto & Transport", REMOVE);
    categoryMap.put("SavingsCategories:Car Insurance", REMOVE);
    categoryMap.put("SavingsCategories:Car Registration", REMOVE);
    categoryMap.put("SavingsCategories:Car Wash", REMOVE);
    categoryMap.put("Auto & Transport:Gas & Fuel", REMOVE);
    categoryMap.put("Auto & Transport:OnStar", REMOVE);
    categoryMap.put("SavingsCategories:Parking", REMOVE);
    categoryMap.put("SavingsCategories:Public Transportation", REMOVE);
    categoryMap.put("Auto & Transport:Service & Parts", REMOVE);
    categoryMap.put("Bills & Utilities", REMOVE);
    categoryMap.put("Bills & Utilities:Cable and Internet", REMOVE);
    categoryMap.put("Bills & Utilities:Cell Phone", REMOVE);
    categoryMap.put("Bills & Utilities:Electricity", REMOVE);
    categoryMap.put("Bills & Utilities:Iphone Cloud Service", REMOVE);
    categoryMap.put("Bills & Utilities:Rent", REMOVE);
    categoryMap.put("Bills & Utilities:Renters Insurance", REMOVE);
    categoryMap.put("Clothes Cleaning", REMOVE);
    categoryMap.put("SavingsCategories:Dry Cleaning", REMOVE);
    categoryMap.put("Clothes Cleaning:Laundry", REMOVE);
    categoryMap.put("Clothing", REMOVE);
    categoryMap.put("Credit Cards", REMOVE);
    categoryMap.put("Credit Cards:Annual Fees", REMOVE);
    categoryMap.put("Dads Tickets", REMOVE);
    categoryMap.put("Dads Tickets:Chris - Dad's TIckets", REMOVE);
    categoryMap.put("Dads Tickets:Laura - Dad's Tickets", REMOVE);
    categoryMap.put("SavingsCategories:Date", REMOVE);
    categoryMap.put("Entertainment", REMOVE);
    categoryMap.put("Food & Dining", REMOVE);
    categoryMap.put("SavingsCategories:Groceries", REMOVE);
    categoryMap.put("Food & Dining:Meals For Others", REMOVE);
    categoryMap.put("SavingsCategories:Restaurants", REMOVE);
    categoryMap.put("Gifts & Donations", REMOVE);
    categoryMap.put("SavingsCategories:Charity", REMOVE);
    categoryMap.put("SavingsCategories:Gifts For Others", REMOVE);
    categoryMap.put("Gifts For Others", REMOVE);
    categoryMap.put("General Savings", REMOVE);
    categoryMap.put("SavingsCategories:Massage", REMOVE);
    categoryMap.put("SavingsCategories:Wedding", REMOVE);
    categoryMap.put("Hair cut", REMOVE);
    categoryMap.put("Health & Fitness", REMOVE);
    categoryMap.put("Health & Fitness:Chiropractor", REMOVE);
    categoryMap.put("Health & Fitness:Competitions", REMOVE);
    categoryMap.put("SavingsCategories:Dentist", REMOVE);
    categoryMap.put("Health & Fitness:Gym", REMOVE);
    categoryMap.put("Health & Fitness:Health", REMOVE);
    categoryMap.put("Health & Fitness:Sports", REMOVE);
    categoryMap.put("Health & Fitness:StepBet", REMOVE);
    categoryMap.put("Health & Fitness:Massage", REMOVE);
    categoryMap.put("SavingsCategories:Vision", REMOVE);
    categoryMap.put("Home Office Supplies", REMOVE);
    categoryMap.put("House", REMOVE);
    categoryMap.put("SavingsCategories:Investments", REMOVE);
    categoryMap.put("Investments:Individual Portfolio", REMOVE);
    categoryMap.put("SavingsCategories:Roth IRA", REMOVE);
    categoryMap.put("Junior League", REMOVE);
    categoryMap.put("Large Purchases", REMOVE);
    categoryMap.put("LifeLock", REMOVE);
    categoryMap.put("Malware Bytes", REMOVE);
    categoryMap.put("SavingsCategories:Personal Care", REMOVE);
    categoryMap.put("Tax", REMOVE);
    categoryMap.put("SavingsCategories:Tickets", REMOVE);
    categoryMap.put("SavingsCategories:Vacation", REMOVE);
    categoryMap.put("SavingsCategories:Work Research", REMOVE);
    categoryMap.put("WorkVacation", REMOVE);
    categoryMap.put("Wedding", REMOVE);
    categoryMap.put("SavingsCategories:Junior League", REMOVE);
    categoryMap.put("SavingsCategories:House", REMOVE);

    return categoryMap;
  }

  private static Map<String, String> defineBlogCategoryMap() {
    Map<String, String> categoryMap = new HashMap<>();
    categoryMap.put("Auto & Transport", REMOVE);
    categoryMap.put("SavingsCategories:Car Insurance", "Car Insurance");
    categoryMap.put("SavingsCategories:Car Registration", "Car Registration");
    categoryMap.put("SavingsCategories:Car Wash", "Car Wash");
    categoryMap.put("Auto & Transport:Gas & Fuel", "Gas");
    categoryMap.put("Auto & Transport:OnStar", "Roadside Service");
    categoryMap.put("SavingsCategories:Parking", "Parking");
    categoryMap.put("SavingsCategories:Public Transportation", "Public Transportation");
    categoryMap.put("Auto & Transport:Service & Parts", "Car Service");
    categoryMap.put("Bills & Utilities", REMOVE);
    categoryMap.put("Bills & Utilities:Cable and Internet", "Cable & Internet");
    categoryMap.put("Bills & Utilities:Cell Phone", "Cell Phone");
    categoryMap.put("Bills & Utilities:Electricity", "Utilities");
    categoryMap.put("Bills & Utilities:Iphone Cloud Service", "Cloud Service");
    categoryMap.put("Bills & Utilities:Rent", "Rent");
    categoryMap.put("Bills & Utilities:Renters Insurance", "Renter's Insurance");
    categoryMap.put("Clothes Cleaning", "Dry Cleaning");
    categoryMap.put("Clothing", "Clothes");
    categoryMap.put("Credit Cards", REMOVE);
    categoryMap.put("Credit Cards:Annual Fees", REMOVE);
    categoryMap.put("Credit Cards:Annual Fees:Chris - Chase Preferred", "Chase Preferred Credit Card Annual Fee");
    categoryMap.put("Credit Cards:Annual Fees:Chris - SPG", "SPG Credit Card Annual Fee");
    categoryMap.put("Credit Cards:Annual Fees:Laura - Chase Reserved", "Chase Reserved Credit Card Annual Fee");
    categoryMap.put("Credit Cards:Annual Fees:Laura - Southwest Plus", "Southwest Plus Credit Card Annual Fee");
    categoryMap.put("Credit Cards:Annual Fees:Laura - Southwest Primier", "Southwest Premier Credit Card Annual Fee");
    categoryMap.put("Dads Tickets", REMOVE);
    categoryMap.put("SavingsCategories:Date", "Date");
    categoryMap.put("Entertainment", REMOVE);
    categoryMap.put("Entertainment:Chris - Netflix", "Streaming Service");
    categoryMap.put("Food & Dining", REMOVE);
    categoryMap.put("SavingsCategories:Groceries", "Groceries");
    categoryMap.put("Food & Dining:Meals For Others", "Meals For Others");
    categoryMap.put("SavingsCategories:Restaurants", "Restaurants");
    categoryMap.put("Gifts & Donations", REMOVE);
    categoryMap.put("Gifts & Donations:Charity", "Charity");
    categoryMap.put("SavingsCategories:Gifts For Others", REMOVE);
    categoryMap.put("Gifts For Others:Chris - Gifts For Laura", "My Fiancee Gifts");
    categoryMap.put("Gifts For Others:Chris - Gifts For Others", "My Gifts for Others");
    categoryMap.put("Gifts For Others:Laura - Gifts For Kara", "Fiancee Gifts for Sister");
    categoryMap.put("Gifts For Others:Laura - Gifts For Others", "Fiancee Gifts for Others");
    categoryMap.put("Hair cut", "Hair Cut");
    categoryMap.put("Health & Fitness", REMOVE);
    categoryMap.put("Health & Fitness:Chiropractor", "Chiropractor");
    categoryMap.put("Health & Fitness:Competitions", "Competitions");
    categoryMap.put("SavingsCategories:Dentist", "Dentist");
    categoryMap.put("Health & Fitness:Gym", "Gym");
    categoryMap.put("Health & Fitness:Health", "Health Care");
    categoryMap.put("Health & Fitness:Sports", "Sports");
    categoryMap.put("Health & Fitness:StepBet", "Workout Program");
    categoryMap.put("SavingsCategories:Vision", "Vision");
    categoryMap.put("Home Office Supplies", "Home Office");
    categoryMap.put("House", "House Savings");
    categoryMap.put("SavingsCategories:Investments", REMOVE);
    categoryMap.put("Investments:Individual Portfolio", REMOVE);
    categoryMap.put("Investments:Individual Portfolio:Chris - CryptoCurrency", "Alternative Investments");
    categoryMap.put("Investments:Individual Portfolio:Chris - Individual (ETrade)", "Stock Investments");
    categoryMap.put("SavingsCategories:Roth IRA", REMOVE);
    categoryMap.put("Investments:Roth IRA:Chris - Roth IRA (DWM)", "My Roth IRA");
    categoryMap.put("Investments:Roth IRA:Laura - Roth IRA", "Fiancee Roth IRA");
    categoryMap.put("Junior League", "Networking Event");
    categoryMap.put("Large Purchases", "Large Purchases");
    categoryMap.put("LifeLock", "ID Protection");
    categoryMap.put("Malware Bytes", "Virus Protection");
    categoryMap.put("SavingsCategories:Personal Care", REMOVE);
    categoryMap.put("Personal Care:Chris - Personal Care", "My Personal Care");
    categoryMap.put("Personal Care:Laura - Personal Care", "Fiancee Personal Care");
    categoryMap.put("Tax", "Tax");
    categoryMap.put("SavingsCategories:Tickets", "Tickets");
    categoryMap.put("SavingsCategories:Vacation", "Vacation");
    categoryMap.put("SavingsCategories:Work Research", "Work Research");

    return categoryMap;
  }

}
