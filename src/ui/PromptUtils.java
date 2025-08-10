package ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Function;
import java.math.BigDecimal;
import java.util.logging.Logger;

final class PromptUtils {

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
        logger.warning("Error encountered: Invalid number format. Please enter a positive number");
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
        logger.warning("Invalid URL format. Please try again with a valid URL.");
        continue;
      }
    }
  }

  public static int promptFixedInt(Scanner scnr, String prompt, int acceptableNums) {

    while (true) {
      try {
        String input = promptNonEmptyString(scnr, prompt);
        int result = Integer.parseInt(input);
        if (result >= 1 && result <= acceptableNums) {
          return result;
        } else {
          logger.warning("You did not enter a number within the range of options. Please try again.");
          continue;
        }
      } catch (NumberFormatException e) {
        logger.warning("You entered a non-number input. Please try again with a number from the set list of options.");
      }
    } 
  }


  private PromptUtils() {
    // Private to prevent instantiation.
  }
}