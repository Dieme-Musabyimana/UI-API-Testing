package api.services;

import api.POJOs.requestPOJO.ProductRequest;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Faker;
import io.restassured.response.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService extends BaseService {

    public ProductService (){
    }

    public final static String newProductName = Faker.getFirstName();

    public Response getProductCategories(){
        return sendGet(Routes.CATEGORIES, null, null, true);
    }

    public Response getSingleCategory(String slug, boolean userAuth){
        Map<String, Object> query = Map.of("slug", slug);
        return sendGet(Routes.SINGLE_CATEGORY, null, query, userAuth);
    }

    public Response createCategory(String parentId){
        return sendPost(Routes.CATEGORIES, new RequestPayloads().createCategoryPayload(parentId),true);
    }

    public Response getProducts(String path, String slug ){
        Map<String, Object> param = Map.of("slug", slug);
        return sendGet(path, param, null, true);
    }

    public Response updateProduct(boolean userAuth){
        Map<String, Object> body = Map.of("name", newProductName);
        return sendPut(Routes.UPDATE_PRODUCT, body, null, userAuth);
    }

    public Response deleteProduct(String id){
        String finalUrl = Routes.DELETE_PRODUCT;
        Map<String, Object> param = Map.of("id", id);
        return sendDelete(finalUrl, param, true);
    }

    public Response createProduct(){
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPost(Routes.PRODUCT, requestPayloads, true);
    }

    public String getProductSlug(){
        Response response = getProducts((Routes.PRODUCT), null);
        return response.path("data[0].slug");
    }

    public List<String> getProductIds(Response response){

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
        return sendPostMultipartWithAuth(Routes.UPLOAD_IMAGE, testImage, "images", null);
    }

    public Response getTrendingProduct(boolean useAuth){
        return sendGet(Routes.TRENDING, null, null, useAuth);
    }

    public Response getProductFlashSales(boolean useAuth){
        return sendGet(Routes.FLESH_SALES, null, null, useAuth);
    }

    public Response getRelatedProducts(boolean useAuth){
        return sendGet(Routes.RELATED_PRODUCT, null, null, useAuth);
    }



}
