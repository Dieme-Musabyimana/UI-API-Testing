package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {private static Properties properties;
    private static final String CONFIG_FILE_PATH = "backend-restassured-java\\src\\main\\resources\\config.properties";

    static {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Could not load config.properties file at " + CONFIG_FILE_PATH, e);
        }
    }
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in config.properties file!");
        }
        return value.trim();
    }


    public static String getBaseUri() {
        return getProperty("baseUri");
    }
    public static String getTestEmail(){ return getProperty("email"); }
    public static String getSuccessMessage(){return getProperty("passedEmailVerificationMessage");}
    public static String getLoginEmail(){return getProperty("loginEmail");}
    public static String getLoginpsswd(){ return getProperty("loginPssd");}
    public static String getFilePath(){ return getProperty("filePath"); }
    public static String getTempFilePath(){ return getProperty("tempFilePath"); }
    public static String getTempFileName(){ return getProperty("tempFileName"); }
    public static String getFormat(){ return getProperty("format"); }
    public static String getTokenPath(){ return getProperty("tokenPath"); }

}