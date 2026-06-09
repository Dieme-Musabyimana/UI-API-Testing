package api.services;

import api.POJOs.requestPOJO.ProductRequest;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.FakerUtils;
import io.restassured.response.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProductService extends BaseService {
    AuthService authService;

    public ProductService(){
        this.authService = new AuthService();
    }
    public final static String newProductName = FakerUtils.getFirstName();

    public Response getProductCategories(){
        return sendGet(Routes.CATEGORIES);
    }
    public Response getSingleCategory(){
        return sendGet(Routes.SINGLE_CATEGORY);
    }

    public Response createCategory(){
        return sendPost(Routes.CATEGORIES, createCategory());
    }

    public Response getProducts(String path){
        return sendGet(path);
    }
    public Response updateProduct(String path, Object object, String token){
        return sendPutWithAuth(path, object, token);
    }

    public Response deleteProduct(String token, String id){
        return sendDeleteWithAuth(Routes.DELETE_PRODUCT, token);
    }

    public Response createProduct(String token){
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPostWithAuth(Routes.PRODUCT, requestPayloads, token);
    }
    public List<String> getProductIds(){
        Response response = createProduct(authService.getLoginToken());

        // Correct paths: data is an object, variants is an array
        String productId = response.jsonPath().getString("data.id");
        String variantId = response.jsonPath().getString("data.variants[0].id");

        // Initialize and return a simple list container
        List<String> ids = new ArrayList<>();
        ids.add(productId);
        ids.add(variantId);

        return ids;
    }


    public Response uploadProductImage(){
        String path = "C:\\DOM\\api-ui-test\\backend-restassured-java\\src\\main\\resources\\avatar.png";
        System.out.println(",,,,,,,,,,,,,,,,," + path);
        File testImage = new File(path);
//        List<File> imageList = Collections.singletonList(testImage);
        return sendPostMultipartWithAuth(Routes.UPLOAD_IMAGE, testImage, "images", authService.getLoginToken());

    }

    public Response getTrendingProduct(){
        return sendGet(Routes.TRENDING);
    }

    public Response getProductFlashSales(){
        return sendGet(Routes.FLESH_SALES);
    }

    public Response getRelatedProducts(){
        return sendGet(Routes.RELATED_PRODUCT);
    }

}