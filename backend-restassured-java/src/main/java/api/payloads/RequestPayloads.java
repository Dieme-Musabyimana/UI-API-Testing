package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.ProductRequest;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.requestPOJO.Variant;
import api.utils.Config;
import api.utils.FakerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class RequestPayloads {
    public static final String sku = FakerUtils.getVariantSku();
    public static final String name = FakerUtils.getFirstName();

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


    public Map<String, Object> updateProductBody(){
        updateProductBody().put("name", "Sport");
        updateProductBody().put("description", "For doing sports");
        updateProductBody().put("parentId", "Spots_clothes");
        return updateProductBody();
    }

    public ProductRequest createProductBody(){
        ProductRequest productRequest = new ProductRequest();
        Variant singleVariant = new Variant();

        productRequest.setName(name);
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
        singleVariant.setSku(sku);
        singleVariant.setStock(Integer.parseInt(Config.getVariantStock()));
        singleVariant.setPrice(parseDouble(Config.getVariantPrice()));

        List<Variant> variantList = new ArrayList<>();
        variantList.add(singleVariant);

        productRequest.setVariants(variantList);
     return productRequest;
    }
}
