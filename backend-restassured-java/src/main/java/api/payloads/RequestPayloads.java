package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.ProductRequest;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.requestPOJO.Variant;
import api.services.ProductService;
import api.utils.Config;
import api.utils.FakerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class RequestPayloads {

    public String getSku(){
        return FakerUtils.getVariantSku();
    }
    public String getName(){
        return FakerUtils.getFirstName();
    }
    public static RegisterReqPOJO createReqBody(){
        RegisterReqPOJO registerData = new RegisterReqPOJO();
        registerData.setFirstName(FakerUtils.getFirstName());
        registerData.setLastName(FakerUtils.getLastName());
        registerData.setEmail(FakerUtils.getEmail());
        registerData.setPassword(FakerUtils.getPassword());
        registerData.setPhone(FakerUtils.getPhone());
        return registerData;
    }

    public static LoginPOJO createLoginBody(){
        LoginPOJO loginData = new LoginPOJO();
        loginData.setEmail(Config.getLoginEmail());
        loginData.setPassword(Config.getLoginpsswd());
        return loginData;
    }

    // Fix potential infinite recursion loop if this was hitting itself previously
    public Map<String, Object> updateProductBody(Map<String, Object> existingMap){
        existingMap.put("name", "Sport");
        existingMap.put("description", "For doing sports");
        existingMap.put("parentId", "Spots_clothes");
        return existingMap;
    }

    public ProductRequest createProductBody(){
        ProductRequest productRequest = new ProductRequest();
        Variant singleVariant = new Variant();

        // Uses the unique instance variables generated during 'new RequestPayloads()'
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
        singleVariant.setSku(getSku()); // Completely unique SKU bound to this payload instance
        singleVariant.setStock(Integer.parseInt(Config.getVariantStock()));
        singleVariant.setPrice(parseDouble(Config.getVariantPrice()));

        List<Variant> variantList = new ArrayList<>();
        variantList.add(singleVariant);

        productRequest.setVariants(variantList);
        return productRequest;
    }

    // CLEANER HOOK: Accept explicit IDs directly within your runner architecture
    public ProductRequest addToCartPayload(){
        ProductRequest addToCartPayload = new ProductRequest();
        addToCartPayload.setProductId(new ProductService().getProductIds().get(0));
        addToCartPayload.setVariantId(new ProductService().getProductIds().get(1));
        addToCartPayload.setQuantity(3);
        return addToCartPayload;
    }
}