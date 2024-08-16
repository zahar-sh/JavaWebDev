package edu.epam.jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;

public interface PrinterArrayService {
    void print(Appendable out, IntArray array) throws CustomException;
}
