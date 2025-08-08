import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

import models.JobApplication;

public class ApplicationManager {

  private ArrayList<JobApplication> jobs = new ArrayList<>();

  public ApplicationManager() {
    this.jobs = new ArrayList<>();
  }

  public void addApplication(JobApplication application) {
    this.jobs.add(application);
  }

  public void viewApplications() {
    if (jobs.size() == 0) {
      System.out.println("No jobs have been added yet. Please add 1 or more jobs and try again!");
    } else {
      for (int i = 0; i < jobs.size(); i++) {
        Integer number = i + 1;
        System.out.println(number + ". " + jobs.get(i).toString());
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

  public List<String> viewStageJobs(String stage) {
    List<String> stageJobs = new ArrayList<>();

    if (jobs.isEmpty()) {
      return stageJobs;
    }

    for (int i = 0; i < jobs.size(); i++) {
      if (jobs.get(i).getStage().equals(stage)) {
        stageJobs.add(jobs.get(i).toString());
      }  
    }

    if (stageJobs.size() == 0) {
      System.out.println("There are no jobs of stage: " + stage);
    }

    return stageJobs;

  }

  public String deleteAllJobs() {
    jobs.clear();
    return "Successfully deleted ALL jobs :)";
  }

  public void exportJobs() {
      // Create file
      try (FileWriter file = new FileWriter("applications.xml")) {
        // open the applicationS outer container
        file.write("<applications>\n");


        for (JobApplication job : jobs) {

          // open the application inner container
          file.write("  <application>\n");

          file.write(String.format("    <company>%s</company>\n", job.getCompany()));
          file.write(String.format("    <role>%s</role>\n", job.getRole()));
          file.write(String.format("    <location>%s</location>\n", job.getLocation()));
          file.write(String.format("    <workFormat>%s</workFormat>\n", job.getWorkFormatString()));
          file.write(String.format("    <payment>%s/%s</payment>", job.getPayment(), job.getPayTypeString()));
          file.write(String.format("    <stage>%s</stage>\n", job.getStageString()));
          file.write(String.format("    <trackingLink>%s</trackingLink>\n", job.getTrackingLink()));
          file.write(String.format("    <applicationDate>%s</applicationDate>\n", job.getAppliedDateString()));


          file.write("  </application>\n");
        }

        file.write("</applications>\n");

      } catch (IOException e) {
        e.printStackTrace();
      };
  }

  public Integer getJobCount() {
    return jobs.size();
  }

  public void exit() {
    System.out.println("Program shutting down, it's been fun! Have a good one :)");
  }




}