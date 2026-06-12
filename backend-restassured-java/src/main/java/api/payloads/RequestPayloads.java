package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.ProductRequest;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.requestPOJO.Variant;
import api.utils.Config;
import api.utils.Faker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class RequestPayloads {

    public String getSku(){
        return Faker.getVariantSku();
    }
    public String getName(){
        return Faker.getFirstName();
    }
    public static RegisterReqPOJO createReqBody(){
        RegisterReqPOJO registerData = new RegisterReqPOJO();
        registerData.setFirstName(Faker.getFirstName());
        registerData.setLastName(Faker.getLastName());
        registerData.setEmail(Faker.getEmail());
        registerData.setPassword(Faker.getPassword());
        registerData.setPhone(Faker.getPhone());
        return registerData;
    }

    public static LoginPOJO createLoginBody(){
        LoginPOJO loginData = new LoginPOJO();
        loginData.setEmail(Config.getLoginEmail());
        loginData.setPassword(Config.getLoginpsswd());
        return loginData;
    }

    public Map<String, Object> changePasswordBody(){
        Map<String, Object> body = new HashMap<>();
        body.put("currentPassword", Config.getLoginPassd2());
        body.put("newPassword", Faker.getPassword());
        return body;
    }

    public Map<String, Object> updateProductBody(Map<String, Object> existingMap){
        existingMap.put("name", "Sport");
        existingMap.put("description", "For doing sports");
        existingMap.put("parentId", "Spots_clothes");
        return existingMap;
    }

    public ProductRequest createProductBody(){
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

//    public static String productId = new ProductService().getProductIds().get(0);
//    public static String variantId = new ProductService().getProductIds().get(1);
    public ProductRequest addToCartPayload(String productId, String variantId){
        ProductRequest addToCartPayload = new ProductRequest();
        addToCartPayload.setProductId(productId);
        addToCartPayload.setVariantId(variantId);
        addToCartPayload.setQuantity(3);
        return addToCartPayload;
    }
    public static Map<String, Object> createCouponPayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("code", "SAVE15NOW");
        payload.put("description", "15% off on all items with minimum checkout order fulfillment.");
        payload.put("discountType", "PERCENTAGE");
        payload.put("discountValue", 15);
        payload.put("minOrderAmount", 50);
        payload.put("maxUses", 200);
        payload.put("expiresAt", "2026-12-31T23:59:59.000Z");

        return payload;
    }

    public Map<String, Object> orderPayload(){
        Map<String, Object> body = new HashMap<>();
        body.put("addressId", "57c06e75-0b9d-4891-ad38-ad1d430bacb9");
        body.put("paymentMethod", "CASH_ON_DELIVERY");
        body.put("notes", "This is my first order");
        body.put("shippingFee", 2);

        return body;
    }

    public Object createCategoryPayload(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", Faker.getFirstName());
        body.put("description", Config.getProductDescription());
        body.put("parentId", Faker.getRandomParentId());
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
}