package ui;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.List;
import java.net.URL;
import java.util.function.Function;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.util.logging.Logger;
import models.*;
import service.ApplicationManager;
import ui.PromptUtils;

@SuppressWarnings("unused")
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws Exception {
        ApplicationManager manager = new ApplicationManager();

        Scanner scnr = new Scanner(System.in);
        int num;

        String resultMessage = "";

        boolean isRunning = true;

        logger.info("Welcome to your job application manager :)");

        while (isRunning) {
            num = PromptUtils.promptFixedInt(scnr,
                "Please select a number corresponding to an action you would like to take :)" + "\n" +
                "1. Add a Job" + "\n" +
                "2. View Jobs" + "\n" +
                "4. Delete a Job" + "\n" + 
                "5. Delete ALL Jobs" + "\n" +
                "6. Show Job Count" + "\n" +
                "7. Export Jobs" + "\n" +
                "8. Import Jobs" + "\n" +
                "9. Exit" + "\n" 
                , 
                10
            );

            switch (num) {
                case 1:
                    String company;
                    String role;
                    String location;
                    WorkFormat workFormat;
                    BigDecimal paymentAmount;
                    PaymentType paymentType;
                    URL trackingLink;

                    company = PromptUtils.promptNonEmptyString(scnr, "What is the company name:");

                    role = PromptUtils.promptNonEmptyString(scnr, "Enter the job title:");

                    location = PromptUtils.promptNonEmptyString(scnr, "What city is the role in?");

                    workFormat = PromptUtils.promptEnum(scnr, "Is the role (i)n-person, (h)ybrid, or (r)emote? (enter one of the three letters)", WorkFormat::fromString);

                    paymentAmount = PromptUtils.promptBigDec(scnr, "What is the pay?");

                    paymentType = PromptUtils.promptEnum(scnr, "Is the role (h)ourly, (s)alary, s(t)ipend, or (c)ommission? (enter one of the four letters)", PaymentType::fromString);

                    trackingLink = PromptUtils.promptURL(scnr, "Enter the tracking link to the application");

                    JobApplication job = new JobApplication(company, role, location, workFormat, paymentAmount, paymentType, trackingLink);

                    manager.addApplication(job);

                    logger.info("Job successfully added!");
                    logger.info("\n");
                    
                    break;

                case 2:
                    num = PromptUtils.promptFixedInt(scnr, 
                        "Please select an option below:" + "\n" +
                        "1. View ALL Jobs" + "\n" +
                        "2. View Jobs at a Certain Stage" + "\n", 2
                    );

                    switch (num) {
                        case 1:
                        if (manager.getJobCount() == 0) {
                            logger.info("There are no jobs at this time. Please add some jobs that you have applied for.");
                            logger.info("\n");
                        }
                            manager.viewApplications();
                            logger.info("\n");
                            break;
                        
                        case 2:
                            String message;
                            message = String.valueOf(PromptUtils.promptFixedInt(scnr,
                                "What stage would you like to see jobs of? (Enter a number from 1-7)" + "\n" +
                                "1. First Round" + "\n" +
                                "2. Second Round" + "\n" +
                                "3. Third Round" + "\n" +
                                "4. Fourth Round" + "\n" +
                                "5. Fifth Round" + "\n" + 
                                "6. Sixth Round" + "\n" + 
                                "7. Final Round" + "\n"
                            , 7));
                            Stage chosenStage = Stage.fromString(message);
                            manager.viewStageJobs(chosenStage);
                            break;
                        default:
                            break;
                    }
                    break;

                case 4:
                    if (manager.getJobCount() == 0) {
                        logger.info("There are no jobs at this time. Please add some jobs you have applied for.");
                        logger.info("\n");
                        break;
                    }
                    // tbd stands for "to be deleted"
                    int tbd = 0;

                    manager.viewApplications();

                    logger.info(
                        "The currently saved jobs have been listed again for your convenience" 
                        + "\n" + 
                        "Enter the number for the job you would like to delete:"
                    );

                    String tempResult = scnr.nextLine();

                    try {
                        tbd = Integer.parseInt(tempResult);
                    } catch (NumberFormatException e) {
                        logger.severe("Error encountered: " + e.getMessage());
                        logger.info("Please enter a valid number.");
                        break;
                    }
                    
                    resultMessage = manager.deleteJob(tbd);
                    logger.info(resultMessage);
                    break;
                
                case 5:
                    resultMessage = manager.deleteAllJobs();
                    logger.info(resultMessage);
                    break;

                case 6:
                    Integer jobCount = manager.getJobCount();
                    logger.info("You have currently logged " + jobCount + " jobs.");
                    logger.info("\n");
                    break;
                
                case 7:
                    manager.exportJobs();
                    logger.info("Jobs successfully exported!");
                    break;

                case 8:
                    manager.importJobs();
                    break;

                case 9:
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
