import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationManager {

  private ArrayList<JobApplication> jobs;
  private static final List<String> stages = Arrays.asList(
    "Applied", "Interviewing", "Offered", "Accepted", "Rejected"
  );

  public ApplicationManager() {
    this.jobs = new ArrayList<>();
  }

  public String addApplication(JobApplication application) {
    this.jobs.add(application);
    return "Job successfully added :)";
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
    Integer target = index - 1;

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

  public String deleteAllJobs(ArrayList<JobApplication> jobs) {
    jobs.clear();
    return "Successfully deleted ALL jobs :)";
  }

  public void exportJobs() {

  }

  public Integer getJobCount() {
    return jobs.size();
  }

  public void exit() {
    System.out.println("Exiting...");
    System.exit(0);
  }




}