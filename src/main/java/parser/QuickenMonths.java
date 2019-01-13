package parser;

import category.Month;
import category.MonthForm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuickenMonths implements MonthForm{
  JANUARY("January"),
  FEBRUARY("February"),
  MARCH("March"),
  APRIL("April"),
  MAY("May"),
  JUNE("June"),
  JULY("July"),
  AUGUST("August"),
  SEPTEMBER("September"),
  OCTOBER("October"),
  NOVEMBER("November"),
  DECEMBER("December");

  private String monthName;

  QuickenMonths(String monthName) {
    setMonthName(monthName);
  }

  public String getMonthName() {
    return monthName;
  }

  private void setMonthName(String monthName) {
    this.monthName = monthName;
  }

  public static boolean doesStringContainAnEnum(String stringToCheck) {
    QuickenMonths[] validMonths = QuickenMonths.values();
    List<String> foundMonths = Arrays.stream(validMonths)
          .map(x -> x.getMonthName())
          .filter(x -> stringToCheck.contains(x))
          .collect(Collectors.toList());

    return foundMonths.size() == 1;
  }

  public static QuickenMonths whatEnumDoesTheStringContain(String stringToCheck) {
    QuickenMonths[] validMonths = QuickenMonths.values();
    List<QuickenMonths> foundMonths = Arrays.stream(validMonths)
            .filter(x -> stringToCheck.contains(x.getMonthName()))
            .collect(Collectors.toList());

    return foundMonths.get(0);
  }


  @Override
  public Month convertToMonth() {
    return Month.valueOf(this.getMonthName().toUpperCase());
  }
}
