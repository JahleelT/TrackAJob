package models;

public enum WorkFormat {
  IN_PERSON("In Person"),
  HYBRID("Hybrid"),
  REMOTE("Remote");

  private final String workFormat;

  WorkFormat(String workFormat) {
    this.workFormat = workFormat;
  }

  public String getWorkFormat() {
    return workFormat;
  }

  public static WorkFormat fromString(String input) {
    for (WorkFormat wf : WorkFormat.values()) {
      if (wf.getWorkFormat().equalsIgnoreCase(input)) {
        return wf;
      }
    }

    return null;
  }
}