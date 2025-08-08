package models;

public enum PaymentType {
  HOURLY("hourly"),
  SALARY("salary"),
  COMMISSION("commission"),
  STIPEND("stipend");

  private final String payType;

  PaymentType(String payType) {
    this.payType = payType;
  }

  public String getPayType() {
    return payType;
  }

  public static PaymentType fromString(String input) {
    for (PaymentType pt : PaymentType.values()) {
      if (pt.getPayType().equalsIgnoreCase(input)) {
        return pt;
      }
    }

    return null;
  }
}