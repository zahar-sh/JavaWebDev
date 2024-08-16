package by.epam.task.parser;

import by.epam.task.entity.Tariff;
import by.epam.task.exception.TariffException;
import by.epam.task.parser.impl.dom.TariffsDomParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;

@Test (groups = "Parser")
public class TariffsDomParserTest {

    @Test(dataProvider = "fourTariffs", dataProviderClass = XmlParserTestDataProvider.class)
    public void testTariffs(String xmlPathFile, List<Tariff> expected) throws TariffException {
        TariffsDomParser tariffsDomParser = new TariffsDomParser();
        tariffsDomParser.buildSetTariffs(xmlPathFile);
        List<Tariff> actual = tariffsDomParser.getTariffs();
        Assert.assertEquals(expected, actual);
    }

    @Test(dataProvider = "invalidPathFile", dataProviderClass = XmlParserTestDataProvider.class)
    public void test(String xmlPathFile) {
        TariffsDomParser tariffsDomParser = new TariffsDomParser();
        assertThrows(TariffException.class, () -> tariffsDomParser.buildSetTariffs(xmlPathFile));
    }
}