package api.services;

import api.POJOs.requestPOJO.ProductRequest;
import api.base.BaseService;
import api.payloads.RequestPayloads;
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
}