package by.epam.task.parser;

import by.epam.task.entity.Tariff;
import by.epam.task.exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class AbstractTariffsBuilder {
    public static Logger logger = LogManager.getLogger();
    public ArrayList<Tariff> tariffs;

    public AbstractTariffsBuilder() {
        tariffs = new ArrayList<>();
    }

    public AbstractTariffsBuilder(ArrayList<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public ArrayList<Tariff> getTariffs() {
        logger.info("Tariffs read are successful, size: " + tariffs.size());
        return this.tariffs;
    }

    public abstract void buildSetTariffs(String filePath) throws TariffException;
}
