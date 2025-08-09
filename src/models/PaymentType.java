package models;

import java.util.Map;
import java.util.HashMap;

public enum PaymentType {
  HOURLY("hourly"),
  SALARY("salary"),
  COMMISSION("commission"),
  STIPEND("stipend");

  private final String payType;
  private static final Map<String, PaymentType> mappedVals = new HashMap<>();

  static {
    mappedVals.put("H", PaymentType.HOURLY);
    mappedVals.put("S", PaymentType.SALARY);
    mappedVals.put("C", PaymentType.COMMISSION);
    mappedVals.put("T", PaymentType.STIPEND);
  }

  PaymentType(String payType) {
    this.payType = payType;
  }

  public String getPayType() {
    return payType;
  }

  public static PaymentType fromString(String input) {
    String normalizedInput = input.trim().toUpperCase();
    if (normalizedInput.length() != 1) {
      return PaymentType.SALARY;
    } else {
      PaymentType result = mappedVals.get(normalizedInput);
      if (result != null) {
        return result;
      } else {
        return PaymentType.SALARY;
      }
    }
  }
}