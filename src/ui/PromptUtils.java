package ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Function;
import java.math.BigDecimal;
import java.util.logging.Logger;

final class PromptUtils {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  /**
   * @param input the String to be checked.
   * @return Returns t/f of whether the input is empty.
   */
  public static boolean isBlank(String input) {
    return input == null || input.trim().isEmpty();
  }

  /**
   * A method for continuous prompting of the user so that there is no empty String inputs
   * 
   * @param Scanner scnr, String prompt : A scanner class object and a String prompt.
   * @return Returns a non-empty String to the user.
   */
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

  /**
   * A method for continuous prompting of the user for enums
   * 
   * @param Scanner scnr, String prompt, an enum class method : A scanner class object, a String prompt, and the fromString method of whatever enum.
   * @return Returns an enum class object to the user.
   */
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

  /**
   * A method for continuous prompting of the user for BigDecimal objects
   * 
   * @params Scanner scnr, String prompt : A scanner class object and a String prompt.
   * @return Returns a BigDecimal object to the user.
   * @throws NumberFormatException When the number format is invalid, the NumberFormatException will be caught.
   */
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


  /**
   * A method for continuous prompting of the user to get a proper URL (not worried about protocols, domains, etc.)
   * 
   * @param Scanner scnr, String prompt : A scanner class object and a String prompt.
   * @return Returns a URL class object to the user.
   * @throws MalformedURLException If the input was invalid for URL format, the MalformedURLException will be caught.
   */
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

  /**
   * A method for continuous prompting of the user to fix the input to an acceptable int
   * 
   * @param Scanner scnr, String prompt, int acceptableNums : A scanner class object, a String prompt, and the number that represents the upper bound of acceptable inputs.
   * @return Returns an int class object (for switch cases).
   * @throws NumberFormatException If there is a non-number or another invalid number format, the NumberFormatException will be caught.
   */
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