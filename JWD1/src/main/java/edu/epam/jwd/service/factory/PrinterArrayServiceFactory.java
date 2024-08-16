package edu.epam.jwd.service.factory;

import edu.epam.jwd.service.ServiceFactory;
import edu.epam.jwd.service.PrinterArrayService;
import edu.epam.jwd.service.impl.PrinterArrayServiceImpl;

public class PrinterArrayServiceFactory implements ServiceFactory<PrinterArrayService> {
    private static final PrinterArrayServiceFactory INSTANCE = new PrinterArrayServiceFactory();

    public static PrinterArrayServiceFactory getInstance() {
        return INSTANCE;
    }

    private static final PrinterArrayServiceImpl DEFAULT = new PrinterArrayServiceImpl();

    @Override
    public PrinterArrayService getService() {
        return DEFAULT;
    }
}
