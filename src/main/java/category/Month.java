package category;

public enum Month implements MonthForm {
  JANUARY,
  FEBRUARY,
  MARCH,
  APRIL,
  MAY,
  JUNE,
  JULY,
  AUGUST,
  SEPTEMBER,
  OCTOBER,
  NOVEMBER,
  DECEMBER;


  @Override
  public Month convertToMonth() {
    return this;
  }
}
