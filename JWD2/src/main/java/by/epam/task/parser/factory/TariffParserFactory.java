package by.epam.task.parser.factory;

import by.epam.task.parser.AbstractTariffsBuilder;
import by.epam.task.parser.impl.dom.TariffsDomParser;
import by.epam.task.parser.impl.sax.TariffsSaxParser;
import by.epam.task.parser.impl.stax.TariffsStaxParser;
import by.epam.task.exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffParserFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE_ERROR_TYPE_PARSER = "Type parser is invalid";

    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffParserFactory() {
    }

    public static AbstractTariffsBuilder createTariffBuilder(String typeParser) throws TariffException {
        if (typeParser == null || typeParser.isEmpty()) {
            logger.error(MESSAGE_ERROR_TYPE_PARSER);
            throw new TariffException(MESSAGE_ERROR_TYPE_PARSER);
        }
        TypeParser type = null;
        try {
            type = TypeParser.valueOf(typeParser.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Create tariff builder: " + e);
            throw new TariffException(MESSAGE_ERROR_TYPE_PARSER);
        }
        switch (type) {
            case DOM -> {
                return new TariffsDomParser();
            }
            case STAX -> {
                return new TariffsStaxParser();
            }
            case SAX -> {
                return new TariffsSaxParser();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }
}