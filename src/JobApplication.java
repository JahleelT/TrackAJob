import java.time.LocalDate;

public class JobApplication {
  private String company;
  private String role;
  private String location;
  private String workFormat;
  private String payment;
  private String stage;
  private String trackingLink;
  private LocalDate appliedDate;

  public JobApplication(String company, String role, String location, String workFormat, String payment, String stage, String trackingLink) {
    this.company = company;
    this.role = role;
    this.location = location;
    this.workFormat = workFormat;
    this.payment = payment;
    this.stage = stage;
    this.trackingLink = trackingLink;
    this.appliedDate = LocalDate.now();
  }

  public void setCompany(String newCompany) {
    this.company = newCompany;
  }

  public void setRole(String newRole) {
    this.role = newRole;
  }

  public void setLocation(String newLoc) {
    this.location = newLoc;
  }

  public void setWorkFormat(String newForm) {
    this.workFormat = newForm;
  }

  public void setPayment(String newPay) {
    this.payment = newPay;
  }
  
  public void setStage(String newStage) {
    this.stage = newStage;
  }
  
  public void setTrackingLink(String newLink) {
    this.trackingLink = newLink;
  }

  public String getStage() {
    return stage;
  }

  public String toString() {
    return 
      "Company: " + company + " | " +
      "Role: " + role + " | " +
      "Location: " + location + " | " +
      "Work Format: " + workFormat + " | " +
      "Payment: " + payment + " | " +
      "Stage: " + stage + " | " +
      "Tracking Link: " + trackingLink + " | " +
      "Date of Application: " + appliedDate + " | ";
  }


}