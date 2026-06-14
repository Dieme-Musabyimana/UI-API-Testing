package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {private static Properties properties;
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

    public static String getRunTimeException(){ return getProperty("run_time_exception"); }

    public static String getBaseUri() {
        return getProperty("baseUri");
    }
    public static String getTestEmail(){ return getProperty("email"); }
    public static String getLoginEmail(){return getProperty("loginEmail");}
    public static String getLoginpsswd(){ return getProperty("loginPssd");}
    public static String getFilePath(){ return getProperty("filePath"); }
    public static String getTempFilePath(){ return getProperty("tempFilePath"); }
    public static String getTempFileName(){ return getProperty("tempFileName"); }
    public static String getFormat(){ return getProperty("format"); }
    public static String getTokenPath(){ return getProperty("tokenPath"); }
    public static String getLoginEmail2(){ return getProperty("loginEmail2"); }
    public static String getLoginPassd2(){ return getProperty("loginPassd2"); }
    public static String getLabel(){ return getProperty("label"); }
    public static String getStreet(){ return getProperty("street"); }
    public static String getCity(){ return getProperty("city"); }
    public static String getCountry(){ return getProperty("country"); }
    public static String getState(){ return getProperty("state"); }
    public static String getPostalCode(){ return getProperty("postalCode"); }
    public static String getCategorySlug(){ return getProperty("category_slug"); }
    public static String getProductSlug(){ return getProperty("product_slug"); }
    public static String getProductId(){ return getProperty("product_id"); }
    public static String getIdToDelete(){ return getProperty("product2Id"); }
    public static String getUnExistingId(){ return  getProperty("unExistingId"); }

    public static String getProductName() { return getProperty("product.create.name"); }
    public static String getProductDescription() { return getProperty("product.create.description"); }
    public static String getProductPrice() { return getProperty("product.create.price"); }
    public static String getProductComparePrice() { return getProperty("product.create.comparePrice"); }
    public static String getProductCategoryId() { return getProperty("product.create.categoryId"); }
//    public static String getProductTags() { return getProperty("product.create.tags"); }
//    public static String getProductIsFeatured() { return getProperty("product.create.isFeatured"); }
//    public static String getProductIsFlashSale() { return getProperty("product.create.isFlashSale"); }
    public static String getProductFlashSalePrice() { return getProperty("product.create.flashSalePrice"); }

    public static String getVariantSize() { return getProperty("product.variant.size"); }
    public static String getVariantColor() { return getProperty("product.variant.color"); }
    public static String getVariantColorHex() { return getProperty("product.variant.colorHex"); }
    public static String getVariantStock() { return getProperty("product.variant.stock"); }
    public static String getVariantPrice() { return getProperty("product.variant.price"); }
    public static String getMessage(){ return  getProperty("updateStatusMessage"); }
    public static String getReturnReason(){ return  getProperty("returnReason"); }



}