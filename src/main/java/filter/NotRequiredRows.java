package filter;

public enum NotRequiredRows {
  WAGES("Wages");


  NotRequiredRows(String fields) {
    setFields(fields);
  }

  private String fields;

  public String getFields() {
    return this.fields;
  }

  public void setFields(String fields) {
    this.fields = fields;
  }


}
