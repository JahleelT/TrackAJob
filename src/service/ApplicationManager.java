package service;

// Data type imports
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
// File manipulation/creation related imports
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Exception imports
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.OutputStreamWriter;

// XML Streaming
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;

// Logging import
import java.util.logging.*;

// Custom model imports
import models.JobApplication;
import models.PaymentType;
import models.WorkFormat;
import models.Stage;

public class ApplicationManager {

  private ArrayList<JobApplication> jobs = new ArrayList<>();

  private static final Logger logger = Logger.getLogger(ApplicationManager.class.getName());

  private boolean imported;

  public ApplicationManager() {
    this.jobs = new ArrayList<>();
  }

  public void addApplication(JobApplication application) {
    this.jobs.add(application);
  }

  public void viewApplications() {
    if (jobs.size() == 0) {
      logger.info("No jobs have been added yet. Please add 1 or more jobs and try again!");
    } else {
      for (int i = 0; i < jobs.size(); i++) {
        Integer number = i + 1;
        logger.info(number + ". " + jobs.get(i).toString());
      }
    }
  }

  public String deleteJob(Integer index) {
    int target = index - 1;
    
    if (jobs.size() == 0) {
      return "No jobs have been added yet. Please add 1 or more jobs before trying again!";
    } else if (target >= jobs.size() || target < 0) {
      return "The job number you selected is out of bounds. Please select a number connected to an existing job!";
    } else {
      jobs.remove(target);
      return "Job successfully deleted :)";
    }

  }

  public void viewStageJobs(Stage stage) {
    List<String> stageJobs = new ArrayList<>();

    if (jobs.isEmpty()) {
      System.out.println("There are no jobs to show or look through. Please add some jobs first!");
    } else {
      for (JobApplication job : jobs) {
        if (job.getStage() == stage) {
          stageJobs.add(job.toString());
        }  
      }

      if (stageJobs.isEmpty()) {
        logger.info("There are no jobs of stage: " + stage);
      }

      System.out.println(stageJobs);
    }

  }

  public String deleteAllJobs() {
    jobs.clear();
    imported = false;
    return "Successfully deleted ALL jobs :)";
  }

  public void exportJobs() {
      // Create file
      try {
        // Setting up XML Stream helpers
        XMLOutputFactory xof = XMLOutputFactory.newInstance();

        FileOutputStream fos = new FileOutputStream("applications.xml");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

        XMLStreamWriter xsw = xof.createXMLStreamWriter(osw);


        // Starting document
        xsw.writeStartDocument("UTF-8", "1.0");
        xsw.writeCharacters("\n");
        xsw.writeStartElement("applications");
        xsw.writeCharacters("\n");

        for (JobApplication job: jobs) {
          // Individual application and attributes below
          xsw.writeCharacters("    ");
          xsw.writeStartElement("application");
          xsw.writeCharacters("\n");

          // Company attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("company");
          xsw.writeCharacters(job.getCompany());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // Role attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("role");
          xsw.writeCharacters(job.getRole());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // Location attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("location");
          xsw.writeCharacters(job.getLocation());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // workFormat attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("workFormat");
          xsw.writeCharacters(job.getWorkFormatString());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // PaymentAmount & PaymentType attrs
          xsw.writeCharacters("        ");
          xsw.writeStartElement("payment");
          xsw.writeCharacters(job.getPayment().toString() + "/" + job.getPayTypeString());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // Stage attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("stage");
          xsw.writeCharacters(job.getStageString());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // trackingLink attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("trackingLink");
          xsw.writeCharacters(job.getTrackingLink().toString());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // appliedDate attr
          xsw.writeCharacters("        ");
          xsw.writeStartElement("appliedDate");
          xsw.writeCharacters(job.getAppliedDateString());
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

          // Ending application element
          xsw.writeCharacters("    ");
          xsw.writeEndElement();
          xsw.writeCharacters("\n");

        }
        
        // Closing out file
        xsw.writeEndElement();
        xsw.writeEndDocument();
        xsw.flush();
        xsw.close();
        
        imported = true;

      } catch (IOException | XMLStreamException e) {
        e.printStackTrace();
      };
  }

  public void importJobs() {

    if (!imported) {
      try  {
        // Open and prepare the file
        File importFile = new File("applications.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(importFile);
        doc.normalize();
        
        // Prepare all args for constructor
        NodeList nodes = doc.getElementsByTagName("application"); 
        
        for (int i = 0; i < nodes.getLength(); i++) {
          try {
            Node node = nodes.item(i);
            Element appElement = (Element) node;

            String company = ((Element) appElement)
              .getElementsByTagName("company")
              .item(0)
              .getTextContent()
              .trim();

            String role = ((Element) appElement)
              .getElementsByTagName("role")
              .item(0)
              .getTextContent()
              .trim();

            String location = ((Element) appElement)
              .getElementsByTagName("location")
              .item(0)
              .getTextContent()
              .trim();

            String wfString = ((Element) appElement)
              .getElementsByTagName("workFormat")
              .item(0)
              .getTextContent();
            WorkFormat workFormat = WorkFormat.fromString(wfString);

            String pString = ((Element) appElement)
              .getElementsByTagName("payment")
              .item(0)
              .getTextContent();
            String[] payParts = pString.split("/");

            BigDecimal paymentAmount = new BigDecimal(payParts[0]); 

            PaymentType paymentType = PaymentType.fromString(payParts[1]);

            String stageString = ((Element) appElement)
              .getElementsByTagName("stage")
              .item(0)
              .getTextContent();
            Stage stage = Stage.fromString(stageString);

            String trackingLinkString = ((Element) appElement)
              .getElementsByTagName("trackingLink")
              .item(0)
              .getTextContent();
            URL trackingLink = new URL(trackingLinkString);

            String localDateString = ((Element) appElement)
              .getElementsByTagName("appliedDate")
              .item(0)
              .getTextContent();
            LocalDate appliedDate = LocalDate.parse(localDateString);

            // Construct the job
            JobApplication job = new JobApplication(company, role, location, workFormat, paymentAmount, paymentType, stage, trackingLink, appliedDate);

            // Add the job
            addApplication(job);
          } catch (Exception e) {
            logger.severe("Skipping malformed job entry at index " + i + ": " + e.getMessage());
          }
        }

        

        imported = true;
        logger.info("Successfully loaded jobs!");
        
      } catch (NullPointerException | ParserConfigurationException | IOException | SAXException | IllegalArgumentException | DateTimeParseException e) {
        logger.severe("Failed to import jobs, " + e.getMessage());
      }
    } else {
      logger.info("You have to delete all jobs before you can import these jobs again.");
    }

  }

  public Integer getJobCount() {
    return jobs.size();
  }

  public void exit() {
    logger.info("Program shutting down, it's been fun! Have a good one :)");
  }

}