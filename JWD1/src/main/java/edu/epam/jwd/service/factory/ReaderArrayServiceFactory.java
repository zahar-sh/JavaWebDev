package edu.epam.jwd.service.factory;

import edu.epam.jwd.service.ReaderArrayService;
import edu.epam.jwd.service.ServiceFactory;
import edu.epam.jwd.service.impl.ReaderArrayServiceImpl;

public class ReaderArrayServiceFactory implements ServiceFactory<ReaderArrayService> {
    private static final ReaderArrayServiceFactory INSTANCE = new ReaderArrayServiceFactory();

    public static ReaderArrayServiceFactory getInstance() {
        return INSTANCE;
    }

    private final ReaderArrayServiceImpl DEFAULT = new ReaderArrayServiceImpl();

    @Override
    public ReaderArrayService getService() {
        return DEFAULT;
    }
}
