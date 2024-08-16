package edu.epam.jwd.service.factory;

import edu.epam.jwd.service.IntArrayService;
import edu.epam.jwd.service.ServiceFactory;
import edu.epam.jwd.service.impl.IntArrayServiceImpl;

public class IntArrayServiceFactory implements ServiceFactory<IntArrayService> {
    private static final IntArrayServiceFactory INSTANCE = new IntArrayServiceFactory();

    public static IntArrayServiceFactory getInstance() {
        return INSTANCE;
    }

    private final IntArrayServiceImpl DEFAULT = new IntArrayServiceImpl();

    @Override
    public IntArrayService getService() {
        return DEFAULT;
    }
}
