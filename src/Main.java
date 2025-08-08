import java.util.Scanner;
import java.math.BigDecimal;
import java.util.List;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;

import models.*;

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationManager manager = new ApplicationManager();

        Scanner scnr = new Scanner(System.in);
        int num;

        String resultMessage = "";

        boolean isRunning = true;

        System.out.println("Welcome to your job application manager :)");

        while (isRunning) {
            System.out.println("Please select a number corresponding to an action you would like to take :)");
            System.out.println(
                "1. Add a Job" + "\n" +
                "2. View All Jobs" + "\n" +
                "3. View Jobs at a Certain Stage" + "\n" +
                "4. Delete a Job" + "\n" + 
                "5. Delete ALL Jobs" + "\n" +
                "6. Show Job Count" + "\n" +
                "7. Export Jobs" + "\n" +
                "8. Exit" + "\n"
            );

            String input = scnr.nextLine();
            System.out.println("\n");

            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println(e);
                System.out.println("Your input is not valid. Please enter a valid NUMBER");
                System.out.println("\n");
                continue;
            }

            switch (num) {
                case 1:
                    String company;
                    String role;
                    String location;
                    WorkFormat workFormat;
                    String paymentAmount;
                    PaymentType paymentType;
                    URL trackingLink;

                    System.out.println("What is the company name:");
                    company = scnr.nextLine();

                    System.out.println("Enter the job title:");
                    role = scnr.nextLine();

                    System.out.println("What city is the role in?");
                    location = scnr.nextLine();

                    System.out.println("Is the role in-person, hybrid, or remote?");
                    String workFormatInput = scnr.nextLine();

                    workFormat = WorkFormat.fromString(workFormatInput);
                    if (workFormat == null) {
                        System.out.println("Invalid work format. Please enter: In person, Hybrid, or Remote");
                        break;
                    }

                    System.out.println("What is the pay?");
                    paymentAmount = scnr.nextLine();

                    System.out.println("What is the payment type?");
                    String paymentTypeString = scnr.nextLine();

                    paymentType = PaymentType.fromString(paymentTypeString);

                    System.out.println("Enter link to where the application is housed"  );
                    String trackingLinkString = scnr.nextLine();

                    try {
                        trackingLink = new URL(trackingLinkString);
                    } catch (MalformedURLException e) {
                        System.err.println("Invalid URL format. Please try again with a valid URL.");
                        continue;
                    }

                    JobApplication job = new JobApplication(company, role, location, workFormat, paymentAmount, paymentType, trackingLink);

                    manager.addApplication(job);

                    System.out.println("Job successfully added!");
                    System.out.println("\n");
                    
                    break;
            
                case 2:
                    if (manager.getJobCount() == 0) {
                        System.out.println("There are no jobs at this time. Please add some jobs you have applied for.");
                        System.out.println("\n");
                        break;
                    }
                    manager.viewApplications();
                    System.out.println("\n");
                    break;

                case 3:
                    String inputStage = "";

                    if (manager.getJobCount() == 0) {
                        System.out.println("There are no jobs at this time. Please add some jobs you have applied for.");
                        System.out.println("\n");
                        break;
                    }

                    System.out.println("Enter the stage you would like to view: (S)ent, (I)nterviewing, (O)ffered, (A)ccepted, (R)ejected");
                    String letter = scnr.nextLine();
                    System.out.println("\n");


                    switch (letter.toLowerCase()) {
                        case "s":
                            inputStage = "Sent";
                            break;

                        case "i":
                            inputStage = "Interviewing";
                            break;

                        case "o":
                            inputStage = "Offered";
                            break;

                        case "a":
                            inputStage = "Accepted";
                            break;

                        case "r":
                            inputStage = "Rejected";
                            break;
                    
                        default:
                            break;
                    }

                    if (inputStage.isEmpty()) {
                        System.out.println("Invalid stage selection");
                    } else {
                        List<String> resultingList = manager.viewStageJobs(inputStage);
                        for (String jobString : resultingList) {
                            System.out.println(jobString);
                        }
                    }

                    
                    break;


                case 4:

                    if (manager.getJobCount() == 0) {
                        System.out.println("There are no jobs at this time. Please add some jobs you have applied for.");
                        System.out.println("\n");
                        break;
                    }
                    // tbd stands for "to be deleted"
                    int tbd = 0;

                    manager.viewApplications();

                    System.out.println(
                        "The currently saved jobs have been listed again for your convenience" 
                        + "\n" + 
                        "Enter the number for the job you would like to delete:"
                    );

                    String tempResult = scnr.nextLine();

                    try {
                        tbd = Integer.parseInt(tempResult);
                    } catch (NumberFormatException e) {
                        System.err.println(e);
                        System.out.println("Please enter a valid number.");
                        break;
                    }
                    
                    resultMessage = manager.deleteJob(tbd);
                    System.out.println(resultMessage);
                    break;
                
                case 5:
                    if (manager.getJobCount() == 0) {
                        System.out.println("There are no jobs to delete at this time. Please add some jobs you have applied for.");
                        System.out.println("\n");
                        break;
                    }
                    resultMessage = manager.deleteAllJobs();
                    System.out.println(resultMessage);
                    break;

                case 6:
                    Integer jobCount = manager.getJobCount();
                    System.out.println("You have currently logged " + jobCount + " jobs.");
                    System.out.println("\n");
                    break;
                
                case 7:
                    manager.exportJobs();
                    System.out.println("Jobs successfully exported!");
                    break;

                case 8:
                    manager.exit();
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }

        
        scnr.close();
    }
}
