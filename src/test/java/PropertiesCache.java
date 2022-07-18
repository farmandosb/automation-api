import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {

    private final Properties configProp = new Properties();

    private PropertiesCache() {
        loadProperties("application.properties");
        loadProperties(getProperty("currentProperties"));
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void loadProperties(String propFileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(propFileName);
        System.out.printf("Reading all properties from: %s", propFileName);
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    //Bill Pugh Solution for singleton pattern
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }
}
