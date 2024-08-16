package by.epam.task.parser;

import by.epam.task.entity.Tariff;
import by.epam.task.exception.TariffException;
import by.epam.task.parser.impl.stax.TariffsStaxParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;

@Test (groups = "Parser")
public class TariffsStaxParserTest {

    @Test(dataProvider = "fourTariffs", dataProviderClass = XmlParserTestDataProvider.class)
    public void testTariffs(String xmlPathFile, List<Tariff> expected) throws TariffException {
        TariffsStaxParser tariffsStaxParser = new TariffsStaxParser();
        tariffsStaxParser.buildSetTariffs(xmlPathFile);
        List<Tariff> actual = tariffsStaxParser.getTariffs();
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(dataProvider = "invalidPathFile", dataProviderClass = XmlParserTestDataProvider.class)
    public void test(String xmlPathFile) {
        TariffsStaxParser tariffsStaxParser = new TariffsStaxParser();
        assertThrows(TariffException.class, () -> tariffsStaxParser.buildSetTariffs(xmlPathFile));
    }
}
