package models;

import java.util.Map;
import java.util.HashMap;

public enum WorkFormat {
  IN_PERSON("In Person"),
  HYBRID("Hybrid"),
  REMOTE("Remote");

  private final String workFormat;
  private static final Map<String, WorkFormat> mappedVals = new HashMap<>();

  static {
    mappedVals.put("I", WorkFormat.IN_PERSON);
    mappedVals.put("H", WorkFormat.HYBRID);
    mappedVals.put("R", WorkFormat.REMOTE);
  }

  WorkFormat(String workFormat) {
    this.workFormat = workFormat;
  }

  public String getWorkFormat() {
    return workFormat;
  }

  public static WorkFormat fromString(String input) {
    String normalizedInput = input.trim().toUpperCase();
    if (normalizedInput.length() != 1) {
      return WorkFormat.HYBRID;
    } else {
      WorkFormat result = mappedVals.get(input);
      if (result != null) {
        return result;
      } else {
        return WorkFormat.HYBRID;
      }
    }
  }
}