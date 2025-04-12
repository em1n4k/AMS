package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheConfig {
    private static final Logger logger = Logger.getLogger(CacheConfig.class.getName());
    private static final Properties properties = new Properties();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try (InputStream input = CacheConfig.class.getClassLoader()
                .getResourceAsStream("cache-config.properties")) {

            if (input != null) {
                properties.load(input);
                logger.info("Конфигурация кэша загружена успешно");
            } else {
                logger.warning("Файл cache-config.properties не найден. Используются значения по умолчанию.");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении файла конфигурации кэша", e);
        }
    }

    public static int getCacheCleanUpInterval() {
        return getIntProperty("cache.cleanup.interval", 10);
    }

    public static int getCacheTTL() {
        return getIntProperty("cache.ttl", 30);
    }

    private static int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Некорректное значение для свойства {0}. Используется значение по умолчанию: {1}",
                    new Object[]{key, defaultValue});
            return defaultValue;
        }
    }
}