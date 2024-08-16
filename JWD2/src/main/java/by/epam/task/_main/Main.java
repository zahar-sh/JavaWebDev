package by.epam.task._main;

import by.epam.task.exception.TariffException;
import by.epam.task.parser.factory.TariffParserFactory;
import by.epam.task.parser.AbstractTariffsBuilder;
import by.epam.task.validation.XmlValidator;

public class Main {
    public static void main(String... args) throws TariffException {
        String type = "stax";
        System.out.println(XmlValidator.isValid("src/main/resources/data_xml/tariffs.xml",
                "src/main/resources/data_xml/tariffs.xsd"));
        AbstractTariffsBuilder builder = TariffParserFactory.createTariffBuilder(type);
        builder.buildSetTariffs("src/test/resources/test_data/tariffsValid.xml");
        System.out.println(builder.getTariffs());

    }
}
