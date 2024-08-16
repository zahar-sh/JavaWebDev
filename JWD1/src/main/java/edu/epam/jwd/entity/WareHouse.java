package edu.epam.jwd.entity;

import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.repository.impl.ObservableMapRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class WareHouse extends ObservableMapRepository<Integer, ArrayStatistics> {
    private static final WareHouse INSTANCE;

    static {
        try {
            INSTANCE = new WareHouse();
        } catch (CustomException e) {
            Logger logger = LogManager.getLogger();
            logger.log(Level.ERROR, e);
            throw new Error(e);
        }
    }

    public static WareHouse getInstance() {
        return INSTANCE;
    }

    private WareHouse() throws CustomException {
        super(new Supplier<Integer>() {
            private int seed = 0;

            @Override
            public Integer get() {
                return ++seed;
            }
        });
    }
}
