package edu.epam.jwd.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntArrayValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String NUMBER_REGEX = "^[-?\\d+\\s]+$";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    private IntArrayValidator() {
    }

    public static boolean isValidDigitLine(String line) {
        if (line == null || line.isEmpty()) {
            LOGGER.log(Level.ERROR, "line is empty");
            return false;
        }
        Matcher matcher = NUMBER_PATTERN.matcher(line);
        return matcher.matches();
    }
}
