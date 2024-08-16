package edu.epam.jwd.service.impl;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.ReaderArrayService;
import edu.epam.jwd.validator.IntArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ReaderArrayServiceImpl implements ReaderArrayService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public IntArray readFrom(String path, String delimiterRegex) throws CustomException {
        try (Scanner scanner = new Scanner(new File(path)).useDelimiter(delimiterRegex)) {
            IntStream.Builder builder = IntStream.builder();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (IntArrayValidator.isValidDigitLine(line)) {
                    String[] tokens = line.split(delimiterRegex);
                    for (String token : tokens) {
                        try {
                            int number = Integer.parseInt(token);
                            builder.add(number);
                        } catch (NumberFormatException e) {
                            LOGGER.log(Level.ERROR, String.format("Cannot parse token: '%s'", token));
                        }
                    }
                } else {
                    LOGGER.log(Level.ERROR, String.format("Cannot parse line: '%s'", line));
                }
            }
            int[] array = builder.build().toArray();
            return new IntArray(array);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
