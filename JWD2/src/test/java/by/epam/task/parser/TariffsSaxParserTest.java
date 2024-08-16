package by.epam.task.parser;

import by.epam.task.entity.Tariff;
import by.epam.task.exception.TariffException;
import by.epam.task.parser.impl.sax.TariffsSaxParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;

@Test (groups = "Parser")
public class TariffsSaxParserTest {

    @Test(dataProvider = "fourTariffs", dataProviderClass = XmlParserTestDataProvider.class)
    public void testTariffs(String xmlPathFile, List<Tariff> expected) throws TariffException {
        TariffsSaxParser tariffsSaxParser = new TariffsSaxParser();
        tariffsSaxParser.buildSetTariffs(xmlPathFile);
        List<Tariff> actual = tariffsSaxParser.getTariffs();
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(dataProvider = "invalidPathFile", dataProviderClass = XmlParserTestDataProvider.class)
    public void test(String xmlPathFile) {
        TariffsSaxParser tariffsSaxParser = new TariffsSaxParser();
        assertThrows(TariffException.class, () -> tariffsSaxParser.buildSetTariffs(xmlPathFile));
    }
}
