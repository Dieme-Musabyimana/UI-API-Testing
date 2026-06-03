package api.services;

import api.base.BaseService;
import api.routes.Routes;
import io.restassured.response.Response;

public class ProductService extends BaseService {

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
}