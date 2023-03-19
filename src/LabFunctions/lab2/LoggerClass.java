package LabFunctions.lab2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class LoggerClass {

    private static final Logger logger = LogManager.getLogger(LoggerClass.class);

    public LoggerClass() {
        // настройка конфигурации логгера
        Configurator.initialize(null, "log4j2.xml");
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }

    public void logWarning(String message) {
        logger.warn(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void logFatal(String message, Throwable throwable) {
        logger.fatal(message, throwable);
    }
}


