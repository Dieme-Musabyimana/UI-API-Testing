package api.utils;

import java.util.UUID;

public class Faker {

    private static final com.github.javafaker.Faker faker = new com.github.javafaker.Faker();

    public static String getFirstName() {
        return faker.regexify("[A-Z][a-z]{6,10}");
    }

    public static String getLastName() {
        return faker.regexify("[A-Z][a-z]{6,12}");
    }

    public static String getEmail() {
        return faker.regexify("[a-z]{8}") + "@example.com";
    }

    public static String getPassword() {
        return faker.regexify("[A-Z][a-z]{5}[0-9]{2}!");
    }

    public static String getPhone() {
        return "+25078" + faker.regexify("[0-9]{7}");
    }
    public static String getVariantSku() { return "SKU-" + faker.regexify("[A-Z0-9]{8}");
    }
    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }
    public static String getTrackingNumber() {
        return faker.regexify("[A-Z]{2}[0-9]{9}RW");
    }
    public static String generateCouponCode() {
        return faker.regexify("[A-Z]{4}[0-9]{2}[A-Z]{3}");
}
}