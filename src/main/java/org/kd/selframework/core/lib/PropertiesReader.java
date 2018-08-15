package org.kd.selframework.core.lib;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);

    public static <T> T readFromConfig(String keyName) {
        PropertiesConfiguration config = new PropertiesConfiguration();
        String propertiesFilePath = "src/main/resources/application.properties";
        try {
            config.load(propertiesFilePath);
            try {
                Integer value = config.getInt(keyName);
                return (T) value;
            } catch (ConversionException notInteger) {
                try {
                    Double value = config.getDouble(keyName);
                    return (T) value;
                } catch (ConversionException notDouble) {
                    return (T) config.getString(keyName);
                }
            }
        } catch (ConfigurationException e) {
            logger.warn("\\u001b[0;34m" + "Could not parse {}", propertiesFilePath);
            return (T) "";
        }
    }
}
