package doc.scanner.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class PropertyFileReader {

    private static PropertyFileReader instance = null;
    private Properties properties = null;

    protected PropertyFileReader() {
        properties = new Properties();
    }

    public synchronized static PropertyFileReader getInstance() {
        if (instance == null) {
            instance = new PropertyFileReader();
            instance.init();
        }
        return instance;
    }

    public void init() {
        Stream.of("/app.properties", "/mail.properties")
                .map(PropertyFileReader.class::getResourceAsStream)
                .forEach(inputStream -> {
                    try {
                        properties.load(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public Properties getProperties() {
        return this.properties;
    }
}
