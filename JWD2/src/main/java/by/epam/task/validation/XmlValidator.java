package by.epam.task.validation;

import by.epam.task.exception.TariffErrorHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isValid(String pathToXmlFile, String pathToXsdSchema) {
        if (!(CustomFileValidator.isFileValid(pathToXmlFile) && CustomFileValidator.isFileValid(pathToXsdSchema))) {
            logger.warn("Path to files (xml or xsd) is invalid.");
            return false;
        }
        boolean answer = true;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(pathToXsdSchema);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(pathToXmlFile);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
        } catch (SAXException e) {
            answer = false;
            logger.error("XML file invalid " + e);
        } catch (IOException e) {
            answer = false;
            logger.error("XML file invalid " + e);
        }
        return answer;
    }
}
