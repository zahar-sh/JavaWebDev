package edu.epam.jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;

public interface ReaderArrayService {
    String DEFAULT_DELIMITER_REGEX = "(,|\\s|\\n)+";

    IntArray readFrom(String path, String delimiterRegex) throws CustomException;
}
