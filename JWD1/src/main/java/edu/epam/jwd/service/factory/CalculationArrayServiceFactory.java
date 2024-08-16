package edu.epam.jwd.service.factory;

import edu.epam.jwd.service.CalculationArrayService;
import edu.epam.jwd.service.ServiceFactory;
import edu.epam.jwd.service.impl.CalculationArrayServiceImpl;

public class CalculationArrayServiceFactory implements ServiceFactory<CalculationArrayService> {
    private static final CalculationArrayServiceFactory INSTANCE = new CalculationArrayServiceFactory();

    public static CalculationArrayServiceFactory getInstance() {
        return INSTANCE;
    }

    private final CalculationArrayServiceImpl DEFAULT = new CalculationArrayServiceImpl();

    @Override
    public CalculationArrayService getService() {
        return DEFAULT;
    }
}
