package api.services;

import api.base.BaseService;
import api.routes.Routes;
import api.utils.FakerUtils;
import io.restassured.response.Response;

import java.util.Map;

public class ProductService extends BaseService {
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

    public Response updateProduct(String path, String token){
        return sendPutWithAuth(path, Map.of("name", newProductName), token);
    }
}