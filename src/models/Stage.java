package models;

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

  Stage (String stage) {
    this.stage = stage;
  }

  public String getStage() {
    return this.stage;
  }
}
