package api.services;

import api.POJOs.requestPOJO.ProductRequest;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.FakerUtils;
import api.utils.TokenManager;
import io.restassured.response.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends BaseService {
    TokenManager tokenManager;
    String token;

    public ProductService (){
    }

    public final static String newProductName = FakerUtils.getFirstName();

    public Response getProductCategories(){
        return sendGet(Routes.CATEGORIES);
    }

    public Response getSingleCategory(){
        return sendGet(Routes.SINGLE_CATEGORY);
    }

    public Response createCategory(){
        return sendPost(Routes.CATEGORIES, new RequestPayloads().createProductBody());
    }

    public Response getProducts(String path){
        return sendGet(path);
    }

    public Response updateProduct(String path, Object object, String token){
        return sendPutWithAuth(path, object);
    }

    public Response deleteProduct(String id){
        String finalUrl = Routes.DELETE_PRODUCT + id;
        return sendDeleteWithAuth(finalUrl);
    }

    public Response createProduct(){
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPostWithAuth(Routes.PRODUCT, requestPayloads);
    }

    public String getProductSlug(){
        Response response = getProducts((Routes.PRODUCT));
        return response.path("data[0].slug");
    }

    public List<String> getProductIds(){
        Response response = createProduct();

        String productId = response.jsonPath().getString("data.id");
        String variantId = response.jsonPath().getString("data.variants[0].id");

        List<String> ids = new ArrayList<>();
        ids.add(productId);
        ids.add(variantId);

        return ids;
    }

    public Response uploadProductImage(){
        String path = "C:\\DOM\\api-ui-test\\backend-restassured-java\\src\\main\\resources\\avatar.png";
        File testImage = new File(path);
        return sendPostMultipartWithAuth(Routes.UPLOAD_IMAGE, testImage, "images");
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