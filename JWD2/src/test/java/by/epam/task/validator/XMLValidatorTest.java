package by.epam.task.validator;

import by.epam.task.validation.XmlValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLValidatorTest {
    private static final String INCORRECT_XML_FILE_PATH = "src/test/resources/test_data/tariffsInvalid.xml";
    private static final String CORRECT_XML_FILE_PATH = "src/test/resources/test_data/tariffsValid.xml";
    private static final  String XSD_FILE_PATH = "src/main/resources/data_xml/tariffs.xsd";

    @Test (groups = "Validator")
    public void testIsValidXmlFileShouldReturnTrueFileIsCorrect() {
        boolean isValid = XmlValidator.isValid(CORRECT_XML_FILE_PATH, XSD_FILE_PATH);
        Assert.assertTrue(isValid);
    }

    @Test (groups = "Validator")
    public void testIsInvalidFilelShouldReturnFalseFileIsIncorrect() {
        boolean isValid = XmlValidator.isValid(INCORRECT_XML_FILE_PATH, XSD_FILE_PATH);
        Assert.assertFalse(isValid);
    }

    @Test (groups = "Validator")
    public void testIsInvalidPathFileNullShouldReturnFalseFileIsIncorrect() {
        boolean isValid = XmlValidator.isValid(null, XSD_FILE_PATH);
        Assert.assertFalse(isValid);
    }
}
