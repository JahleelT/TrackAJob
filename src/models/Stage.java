package models;

import java.util.Map;
import java.util.HashMap;

public enum Stage {
  FIRST_ROUND("first"),
  INTERVIEWING("interviewing"),
  ASSESSMENT("assessment"),
  SECOND_ROUND("second"),
  THIRD_ROUND("third"),
  FOURTH_ROUND("fourth"),
  FIFTH_ROUND("fifth"),
  SIXTH_ROUND("sixth"),
  FINAL_ROUND("final");

  private final String stage;
  private static final Map<String, Stage> mappedVals = new HashMap<>();

  static {
    mappedVals.put("1", Stage.FIRST_ROUND);
    mappedVals.put("2", Stage.SECOND_ROUND);
    mappedVals.put("3", Stage.THIRD_ROUND);
    mappedVals.put("4", Stage.FOURTH_ROUND);
    mappedVals.put("5", Stage.FIFTH_ROUND);
    mappedVals.put("6", Stage.SIXTH_ROUND);
    mappedVals.put("7", Stage.FINAL_ROUND);
  }

  Stage (String stage) {
    this.stage = stage;
  }

  public String getStage() {
    return this.stage;
  }

  public static Stage fromString(String input) {
    try {
      int value = Integer.parseInt(input.trim());
      Stage result = mappedVals.get(value);
      if (result != null) {
        return result;
      }
    } catch (NumberFormatException e) {
      return Stage.FIRST_ROUND;
    }
    return Stage.FIRST_ROUND;
  }
}
