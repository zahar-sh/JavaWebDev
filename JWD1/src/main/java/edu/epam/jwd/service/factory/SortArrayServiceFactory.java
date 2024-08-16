package edu.epam.jwd.service.factory;

import edu.epam.jwd.service.SortArrayService;
import edu.epam.jwd.service.ServiceFactory;
import edu.epam.jwd.service.impl.SortArrayServiceImpl;

public class SortArrayServiceFactory implements ServiceFactory<SortArrayService> {
    private static final SortArrayServiceFactory INSTANCE = new SortArrayServiceFactory();

    public static SortArrayServiceFactory getInstance() {
        return INSTANCE;
    }

    private static final SortArrayServiceImpl DEFAULT = new SortArrayServiceImpl();

    @Override
    public SortArrayService getService() {
        return DEFAULT;
    }
}
