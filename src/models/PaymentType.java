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

  /**
   * A method to retrieve the enum object as a string.
   * 
   * @return Returns a string representing the enum.
   */
  public String getPayType() {
    return payType;
  }

  /**
   * A method to take a string and map it into the PaymentType equivalent
   * 
   * @param String prompt : A String class input object.
   * @return Returns a PaymentType enum object
   */
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