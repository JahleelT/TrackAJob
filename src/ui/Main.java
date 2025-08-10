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

@SuppressWarnings("unused")
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static String promptNonEmptyString(Scanner scnr, String prompt) {
        String input;
        do {
            logger.info(prompt);
            input = scnr.nextLine();
            if (isBlank(input)) {
                logger.info("Input cannot be empty/whitespace. Try again.");
            }
        } while (isBlank(input));
        return input;
    }

    public static <T> T promptEnum(Scanner scnr, String prompt, Function<String, T> fromString) {

        while (true) {
            String answer = promptNonEmptyString(scnr, prompt).trim();

            T value = fromString.apply(answer);

            if (value != null) {
                return value;
            } else {
                logger.info("Invalid input. Try again.");
                continue;
            }
        }
    }

    public static BigDecimal promptBigDec(Scanner scnr, String prompt) {
        BigDecimal result;

        while (true) {
            String answer = promptNonEmptyString(scnr, prompt)
                .trim()
                .replace(",", "")
                .replace("$", "");

            try {
                result = new BigDecimal(answer);
                if (result.compareTo(BigDecimal.ZERO) > 0) {
                    break;
                } else {
                    logger.info("Your input was not a postiive number. Please enter a positive number for the role's compensation.");
                }
            } catch (NumberFormatException e) {
                logger.severe("Error encountered: Invalid number format. Please enter a positive number");
            }
        }
        return result;
    }

    public static URL promptURL(Scanner scnr, String prompt) {
        while (true) {
            String link = promptNonEmptyString(scnr, prompt);
            try {
                URL url = new URL(link);
                return url;
            } catch (MalformedURLException e) {
                logger.severe("Invalid URL format. Please try again with a valid URL.");
                continue;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        ApplicationManager manager = new ApplicationManager();

        Scanner scnr = new Scanner(System.in);
        int num;

        String resultMessage = "";

        boolean isRunning = true;

        logger.info("Welcome to your job application manager :)");

        while (isRunning) {
            logger.info("Please select a number corresponding to an action you would like to take :)");
            logger.info(
                "1. Add a Job" + "\n" +
                "2. View All Jobs" + "\n" +
                "3. View Jobs at a Certain Stage" + "\n" +
                "4. Delete a Job" + "\n" + 
                "5. Delete ALL Jobs" + "\n" +
                "6. Show Job Count" + "\n" +
                "7. Export Jobs" + "\n" +
                "8. Import Jobs" + "\n" +
                "9. Exit" + "\n" +
                "11. View Jobs" + "\n" 
            );

            String input = scnr.nextLine();
            logger.info("\n");

            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                logger.severe("Error encountered: " + e.getMessage());
                logger.info("Your input is not valid. Please enter a valid NUMBER");
                logger.info("\n");
                continue;
            }

            switch (num) {
                case 1:
                    String company;
                    String role;
                    String location;
                    WorkFormat workFormat;
                    BigDecimal paymentAmount;
                    PaymentType paymentType;
                    URL trackingLink;

                    company = promptNonEmptyString(scnr, "What is the company name:");

                    role = promptNonEmptyString(scnr, "Enter the job title:");

                    location = promptNonEmptyString(scnr, "What city is the role in?");

                    workFormat = promptEnum(scnr, "Is the role (i)n-person, (h)ybrid, or (r)emote? (enter one of the three letters)", WorkFormat::fromString);

                    paymentAmount = promptBigDec(scnr, "What is the pay?");

                    paymentType = promptEnum(scnr, "Is the role (h)ourly, (s)alary, s(t)ipend, or (c)ommission? (enter one of the four letters)", PaymentType::fromString);

                    trackingLink = promptURL(scnr, "Enter the tracking link to the application");

                    JobApplication job = new JobApplication(company, role, location, workFormat, paymentAmount, paymentType, trackingLink);

                    manager.addApplication(job);

                    logger.info("Job successfully added!");
                    logger.info("\n");
                    
                    break;
            
                case 2:
                    if (manager.getJobCount() == 0) {
                        logger.info("There are no jobs at this time. Please add some jobs you have applied for.");
                        logger.info("\n");
                        break;
                    }
                    manager.viewApplications();
                    logger.info("\n");
                    break;

                case 11:
                    logger.info(
                        "Please select an option below:" + "\n" +
                        "1. View ALL Jobs" + "\n" +
                        "2. View Jobs at a Certain Stage" + "\n"
                    );

                    input = scnr.nextLine();
                    switch (Integer.parseInt(input)) {
                        case 1:
                            manager.viewApplications();
                            break;
                        
                        case 2:
                            String message;
                            logger.info(
                                "What stage would you like to see jobs of?" + "\n" +
                                "1. First Round" + "\n" +
                                "2. Second Round" + "\n" +
                                "3. Third Round" + "\n" +
                                "4. Fourth Round" + "\n" +
                                "5. Fifth Round" + "\n" + 
                                "6. Sixth Round" + "\n" + 
                                "7. Final Round" + "\n"
                            );
                            message = scnr.nextLine();
                            manager.viewStageJobs(message);
                            break;
                        default:
                            break;
                    }
                    break;

                case 3:
                    String inputStage = "";

                    if (manager.getJobCount() == 0) {
                        logger.info("There are no jobs at this time. Please add some jobs you have applied for.");
                        logger.info("\n");
                        break;
                    }

                    logger.info("Enter the stage you would like to view: (S)ent, (I)nterviewing, (O)ffered, (A)ccepted, (R)ejected");
                    String letter = scnr.nextLine();
                    logger.info("\n");


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
                        logger.info("Invalid stage selection");
                    } else {
                        List<String> resultingList = manager.viewStageJobs(inputStage);
                        for (String jobString : resultingList) {
                            logger.info(jobString);
                        }
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
