package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.ProductRequest;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.requestPOJO.Variant;
import api.utils.Config;
import api.utils.Faker;
import api.utils.LoginAs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class RequestPayloads {

    public String getSku() {
        return Faker.getVariantSku();
    }

    public String getName() {
        return Faker.getFirstName();
    }
    public Map<String, Object> login2Body() {
        Map<String, Object> loginBody = new HashMap<>();
        loginBody.put("email", Config.getCustomerLoginEmail());
        loginBody.put("password", Config.getCustomerLoginPassword());
        return loginBody;
    }

    public static RegisterReqPOJO createReqBody() {
        RegisterReqPOJO registerData = new RegisterReqPOJO();
        registerData.setFirstName(Faker.getFirstName());
        registerData.setLastName(Faker.getLastName());
        registerData.setEmail(Faker.getEmail());
        registerData.setPassword(Faker.getPassword());
        registerData.setPhone(Faker.getPhone());
        return registerData;
    }

    public static LoginPOJO createLoginBody() {
        LoginPOJO loginData = new LoginPOJO();
        loginData.setEmail(Config.getAdminLoginEmail());
        loginData.setPassword(Config.getAdminLoginPassword());
        return loginData;
    }

    public Map<String, Object> changePasswordBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("currentPassword", Config.getCustomerLoginPassword());
        body.put("newPassword", Faker.getPassword());
        return body;
    }

    public RegisterReqPOJO updateProfilePayload(String firstName, String lastName){
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(firstName);
        registerReqPOJO.setLastName(lastName);
        registerReqPOJO.setPhone(Faker.getPhone());
        return registerReqPOJO;
    }

    public Map<String, Object> updateProductBody(Map<String, Object> existingMap) {
        existingMap.put("name", "Sport");
        existingMap.put("description", "For doing sports");
        existingMap.put("parentId", "Spots_clothes");
        return existingMap;
    }

    public ProductRequest createProductBody() {
        ProductRequest productRequest = new ProductRequest();
        Variant singleVariant = new Variant();

        productRequest.setName(getName());
        productRequest.setDescription(Config.getProductDescription());
        productRequest.setPrice(parseDouble(Config.getProductPrice()));
        productRequest.setComparePrice(parseDouble(Config.getProductComparePrice()));
        productRequest.setCategoryId(Config.getProductCategoryId());

        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        productRequest.setTags(tags);
        productRequest.setIsFeatured(true);
        productRequest.setIsFlashSale(true);
        productRequest.setFlashSalePrice(parseDouble(Config.getProductFlashSalePrice()));

        singleVariant.setSize(Config.getVariantSize());
        singleVariant.setColor(Config.getVariantColor());
        singleVariant.setColorHex(Config.getVariantColorHex());
        singleVariant.setSku(getSku());
        singleVariant.setStock(Integer.parseInt(Config.getVariantStock()));
        singleVariant.setPrice(parseDouble(Config.getVariantPrice()));

        List<Variant> variantList = new ArrayList<>();
        variantList.add(singleVariant);

        productRequest.setVariants(variantList);
        return productRequest;
    }

    public ProductRequest addToCartPayload(String productId, String variantId) {
        ProductRequest addToCartPayload = new ProductRequest();
        addToCartPayload.setProductId(productId);
        addToCartPayload.setVariantId(variantId);
        addToCartPayload.setQuantity(3);
        return addToCartPayload;
    }

    public  Map<String, Object> getAddressPayload(String firsName, String lastName) {
        Map<String, Object> addressBody = new HashMap<>();
        addressBody.put("label", Config.getLabel());
        addressBody.put("firstName", firsName);
        addressBody.put("lastName", lastName);
        addressBody.put("phone", Faker.getPhone());
        addressBody.put("street", Config.getStreet());
        addressBody.put("city", Config.getCity());
        addressBody.put("state", Config.getState());
        addressBody.put("country", Config.getCountry());
        addressBody.put("postalCode",Config.getPostalCode());
        addressBody.put("isDefault", true);

        return addressBody;
    }

    public Map<String, Object> createCouponPayload(String couponCode) {
        Map<String, Object> payload = new HashMap<>();

        payload.put("code", couponCode);
        payload.put("description", "15% off on all items with minimum checkout order fulfillment.");
        payload.put("discountType", "PERCENTAGE");
        payload.put("discountValue", 15);
        payload.put("minOrderAmount", 50);
        payload.put("maxUses", 200);
        payload.put("expiresAt", "2026-12-31T23:59:59.000Z");

        return payload;
    }



    public Map<String, Object> orderPayload(String paymentMethod) {
        Map<String, Object> body = new HashMap<>();
        body.put("addressId", "57c06e75-0b9d-4891-ad38-ad1d430bacb9");
        body.put("paymentMethod", paymentMethod);
        body.put("notes", "This is my first order");
        body.put("shippingFee", 2);

        return body;
    }

    public Object createCategoryPayload(String parentId) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", Faker.getFirstName());
        body.put("description", Config.getProductDescription());
        body.put("parentId", parentId);
        return body;
    }

    public Map<String, Object> updateOrderStatusPayload(String status) {
        Map<String, Object> body = new HashMap<>();
        String trackingNumber = Faker.getTrackingNumber();
        body.put("status", status);
        body.put("message", Config.getMessage());
        body.put("trackingNumber", trackingNumber);

        return body;
    }

    public Map<String, Object> returnReason() {
        Map<String, Object> reason = new HashMap<>();
        reason.put("reason", Config.getReturnReason());
        return reason;
    }

    public File createFile(String path) {
        File file = new File(path);
        return file;
    }


    public static LoginPOJO getCredentials(LoginAs context) {
        return switch (context) {
            case ADMIN -> new LoginPOJO(Config.getAdminLoginEmail(), Config.getAdminLoginPassword());
            case CUSTOMER -> new LoginPOJO(Config.getCustomerLoginEmail(), Config.getCustomerLoginPassword());
            case SELLER -> new LoginPOJO("seller@example.com", "Seller@123456");
            case NONE -> throw new IllegalArgumentException("Cannot fetch credentials for LoginAs.NONE because no authentication is required!");

            default -> throw new IllegalArgumentException("Unexpected login option value: " + context);
        };
    }

    public List<Map<String, Object>> reviewParams(String productId, int page, String sort){
        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> path = new HashMap<>();
        path.put("productId", productId);
        Map<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("sort", sort);
        params.add(path);
        params.add(query);

        return params;
    }
    {
//        "rating": 5,
//            "title": "string",
//            "body": "string"
    }
    public Map<String, Object> submitREviewBody(int rating, String title, String body){
        Map<String, Object> reviewBody = new HashMap<>();
        reviewBody.put("rating", rating);
        reviewBody.put("title", title);
        reviewBody.put("body", body);
        return reviewBody;
    }
}




