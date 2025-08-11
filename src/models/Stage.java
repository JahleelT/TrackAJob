package models;

import java.util.Map;
import java.util.HashMap;

import java.util.logging.Logger;

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
  private static final Map<Integer, Stage> mappedVals = new HashMap<>();
  private static final Logger logger = Logger.getLogger(Stage.class.getName());

  static {
    mappedVals.put(1, Stage.FIRST_ROUND);
    mappedVals.put(2, Stage.SECOND_ROUND);
    mappedVals.put(3, Stage.THIRD_ROUND);
    mappedVals.put(4, Stage.FOURTH_ROUND);
    mappedVals.put(5, Stage.FIFTH_ROUND);
    mappedVals.put(6, Stage.SIXTH_ROUND);
    mappedVals.put(7, Stage.FINAL_ROUND);
  }

  Stage (String stage) {
    this.stage = stage.toLowerCase();
  }

  public String getStage() {
    return this.stage;
  }

  public static Stage fromString(String input) {

    for ( Stage s : Stage.values()) {
      if (s.getStage().equalsIgnoreCase(input.trim())) {
        return s;
      }
    }

    try {
      int value = Integer.parseInt(input.trim().toLowerCase());
      Stage result = mappedVals.get(value);
      if (result != null) {
        return result;
      }
    } catch (NumberFormatException e) {
      logger.warning("Error countered: " + e);
    }

    return Stage.FIRST_ROUND;
    
  }

}
