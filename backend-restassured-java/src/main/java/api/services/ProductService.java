package api.services;

import api.POJOs.requestPOJO.ProductRequest;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;

public class ProductService extends BaseService {
    RequestPayloads requestPayloads;

    public ProductService(){
        this.requestPayloads = new RequestPayloads();
    }


    public final static String newProductName = Faker.getFirstName();

    public Response getProductCategories() {
        return sendGet(Routes.CATEGORIES, null, null, ADMIN);
    }

    public Response getSingleCategory(String slug, LoginAs loginAs) {
        Map<String, Object> path = Map.of("slug", slug);
        return sendGet(Routes.SINGLE_CATEGORY, path, null, loginAs);
    }

    public Response createCategory(String parentId) {
        return sendPost(Routes.CATEGORIES, new RequestPayloads().createCategoryPayload(parentId), null, ADMIN);
    }

    public Response getProducts(LoginAs loginAs) {
        return sendGet(Routes.PRODUCT, null, null, loginAs);
    }

    public Response getSingleProduct(String slug, LoginAs loginAs){
        Map<String, Object> param = new HashMap<>();
        param.put("slug", slug);
        return sendGet(Routes.SINGLE_PRODUCT, param, null, loginAs);
    }

    public Response updateProduct(String id, LoginAs loginAs) {
        Map<String, Object> path = new HashMap<>();
        path.put("id", id);
        Map<String, Object> body = Map.of("name", newProductName);
        return sendPut(Routes.UPDATE_PRODUCT, body, path, loginAs);
    }

    public Response deleteProduct(String id, LoginAs loginAs) {
        Map<String, Object> param = Map.of("id", id);
        return sendDelete(Routes.DELETE_PRODUCT, param, loginAs);
    }

    public Response createProduct(LoginAs loginAs) {
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPost(Routes.PRODUCT, requestPayloads, null, loginAs);
    }

    public Map<String, Object> getProductParams(LoginAs loginAs) {
        return requestPayloads.createdProductParam(loginAs);
    }

    public Response uploadProductImage(String id) {
        File image = new RequestPayloads().createFile(Config.getFilePath());
        Map<String, Object> path = Map.of("id", id);

        return sendPostMultipartWithAuth(Routes.UPLOAD_IMAGE, image, "images", path, ADMIN);
    }

    public Response getTrendingProduct(LoginAs loginAs) {
        return sendGet(Routes.TRENDING, null, null, loginAs);
    }

    public Response getProductFlashSales(LoginAs loginAs) {
        return sendGet(Routes.FLESH_SALES, null, null, loginAs);
    }

    public Response getRelatedProducts(String id, LoginAs loginAs) {
        Map<String, Object> path = new HashMap<>();
        path.put("id", id);
        return sendGet(Routes.RELATED_PRODUCT, path, null, loginAs);
    }

    public Response getWishList(LoginAs loginAs) {
        return sendGet(Routes.WISHLIST, null, null, loginAs);
    }

    public Response addProductToWishlist(String productId, LoginAs loginAs) {
        Map<String, Object> path = Map.of("productId", productId);
        return sendPost(Routes.ADD_REMOVE_TO_WISHLIST, null, path, loginAs);
    }

    public String getProductIdToRemove(LoginAs loginAs) {
        Response response = getWishList(loginAs);
        List<Object> wishlist = response.jsonPath().getList("data");
        if (wishlist.isEmpty()) {
            String id = new ProductService().getProductParams(loginAs).get("productid").toString();
            Response addResponse = addProductToWishlist(id, loginAs);
            return addResponse.jsonPath().getString("data.productId");
        }
        return response.jsonPath().getString("data[0].productId");
    }

    public Response removeFromWishlist(String productId, LoginAs loginAs) {
        Map<String, Object> path = Map.of("productId", productId);
        return sendDelete(Routes.ADD_REMOVE_TO_WISHLIST, path, loginAs);
    }

    public Response moveWishlistToCart(String productId, LoginAs loginAs) {
        Map<String, Object> path = Map.of("productId", productId);
        return sendPost(Routes.MOVE_TO_CART, null, path, loginAs);
    }

    public Response getProductReviews(String productId, int page, String sort, LoginAs loginAs){
    Map<String, Object> path = requestPayloads.reviewParams(productId, page, sort).getFirst();
    Map<String, Object> query = requestPayloads.reviewParams(productId, page, sort).getLast();
    return sendGet(Routes.REVIEWS, path, query, loginAs);
    }

    public Response submitReview(int rating, String title, String body, String id, LoginAs loginAs){
        Map<String, Object> reqBody = requestPayloads.submitREviewBody(rating, title, body);
        Map<String, Object> path = Map.of("productId", id);
        return sendPost(Routes.REVIEWS, reqBody, path, loginAs);
    }

    public Response fullTextSearch(String q, Integer page, Integer limit, String category, Integer minPrice, Integer maxPrice, String sort, LoginAs loginAs){
       Map<String, Object> searchQuery = requestPayloads.searchParam(q, page, limit, category, minPrice, maxPrice, sort);
       return sendGet(Routes.FULL_TEXT, null, searchQuery, loginAs);
    }
}