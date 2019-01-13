package category;

import java.math.BigDecimal;

/**
 * Implementation of MonthlyCategory.
 */
public class BudgetedMonthlyCategory implements MonthlyCategory {
  private String budgetedName = "";
  private Month month;
  private BigDecimal budgetedAmount = new BigDecimal("0.00");
  private BigDecimal amountSpent = new BigDecimal("0.00");

  /**
   * Constructor
   * @param budgetedName
   * @param month
   * @param budgetedAmount
   * @param amountSpent
   */
  public BudgetedMonthlyCategory(String budgetedName, MonthForm month, BigDecimal budgetedAmount, BigDecimal amountSpent) {
    setNameOfBudgeted(budgetedName);
    setTimeOfBudgeted(month.convertToMonth());
    setBudgetedAmount(budgetedAmount);
    setAmountSpent(amountSpent);
  }

  @Override
  public String getNameOfBudgeted() {
    return budgetedName;
  }

  @Override
  public Month getTimeOfBudgeted() {
    return month;
  }

  @Override
  public BigDecimal getBudgetedAmount() {
    return budgetedAmount;
  }

  @Override
  public BigDecimal getAmountSpent() {
    return amountSpent;
  }

  @Override
  public void setNameOfBudgeted(String budgetedName) {
    this.budgetedName = budgetedName;
  }

  @Override
  public void setTimeOfBudgeted(Month budgetedTime) {
    this.month = budgetedTime;
  }

  public void setBudgetedAmount(BigDecimal budgetedAmount) {
    this.budgetedAmount = budgetedAmount;
  }

  public void setAmountSpent(BigDecimal amountSpent) {
    this.amountSpent = amountSpent;
  }
}
