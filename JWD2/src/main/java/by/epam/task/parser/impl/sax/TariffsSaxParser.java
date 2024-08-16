package by.epam.task.parser.impl.sax;

import by.epam.task.entity.Tariff;
import by.epam.task.parser.AbstractTariffsBuilder;
import by.epam.task.validation.CustomFileValidator;
import by.epam.task.exception.TariffErrorHandler;
import by.epam.task.exception.TariffException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class TariffsSaxParser extends AbstractTariffsBuilder {
    private XMLReader reader;
    private final TariffHandler handler = new TariffHandler();

    public TariffsSaxParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader  = saxParser.getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            logger.fatal("Initial config: " + e);
        }
        reader.setErrorHandler(new TariffErrorHandler());
        reader.setContentHandler(handler);
    }

    public void buildSetTariffs(String filePath) throws TariffException {
        if (!CustomFileValidator.isFileValid(filePath)) {
            logger.error("file invalid: " + filePath);
            throw new TariffException("file invalid: " + filePath);
        }
        try {
            reader.parse(filePath);
        } catch (NullPointerException e) {
            logger.fatal("Null pointer: " + e);
            throw new TariffException("Build tariffs: " + e);
        } catch (IOException | SAXException e) {
            logger.fatal("Build tariffs: " + e);
            throw new TariffException("Build tariffs: " + e);
        }
        tariffs = handler.getTariffs();
    }
}
