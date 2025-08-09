package models;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

public class JobApplication {

  private String company; // perm
  private String role; // perm 
  private String location; // perm
  private WorkFormat workFormat; // perm
  private BigDecimal paymentAmount; 
  private PaymentType paymentType;
  private Stage stage;
  private URL trackingLink;
  private LocalDate appliedDate;

  // Add Job method constructor
  public JobApplication(String company, String role, String location, WorkFormat workFormat, String paymentAmountStr, PaymentType paymentType, URL trackingLink) {
    this.company = company;
    this.role = role;
    this.location = location;
    this.workFormat = workFormat;
    this.paymentAmount = new BigDecimal(paymentAmountStr);
    this.paymentType = paymentType;
    this.stage = Stage.FIRST_ROUND;
    this.trackingLink = trackingLink;
    this.appliedDate = LocalDate.now();
  }

  // Imported job constructor (all values already filled)
  public JobApplication(String company, String role, String location, WorkFormat workFormat, BigDecimal paymentAmount, PaymentType paymentType, Stage stage, URL trackingLink, LocalDate appliedDate) {
    this.company = company;
    this.role = role;
    this.location = location;
    this.workFormat = workFormat;
    this.paymentAmount = paymentAmount;
    this.paymentType = paymentType;
    this.stage = stage;
    this.trackingLink = trackingLink;
    this.appliedDate = appliedDate;
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

  public void setWorkFormat(WorkFormat newForm) {
    this.workFormat = newForm;
  }

  public void setPaymentAmount(BigDecimal newPay) {
    this.paymentAmount = newPay;
  }

  public void setPaymentType(PaymentType type) {
    this.paymentType = type;
  }
  
  public void setStage(Stage newStage) {
    this.stage = newStage;
  }
  
  public void setTrackingLink(URL newLink) {
    this.trackingLink = newLink;
  }

  public Stage getStage() {
    return stage;
  }

  public String getStageString() {
    return stage.getStage();
  }

  public String getCompany() {
    return company;
  }

  public String getRole() {
    return role;
  }

  public String getLocation() {
    return location;
  }

  public WorkFormat getWorkFormat() {
    return workFormat;
  }

  public String getWorkFormatString() {
    return workFormat.getWorkFormat();
  }

  public BigDecimal getPayment() {
    return paymentAmount; 
  }

  public String getPayTypeString() {
    return paymentType.getPayType();
  }

  public URL getTrackingLink() {
    return trackingLink;
  }

  public LocalDate getAppliedDate() {
    return appliedDate;
  }

  public String getAppliedDateString() {
    return appliedDate.toString();
  }

  public String toString() {
    return 
      "Company: " + company + " | " +
      "Role: " + role + " | " +
      "Location: " + location + " | " +
      "Work Format: " + workFormat.getWorkFormat() + " | " +
      "Payment: " + paymentAmount + " | " +
      "Payment Type: " + paymentType.getPayType() + " | " +
      "Stage: " + stage.getStage() + " | " +
      "Tracking Link: " + trackingLink + " | " +
      "Date of Application: " + appliedDate + " | ";
  }


}