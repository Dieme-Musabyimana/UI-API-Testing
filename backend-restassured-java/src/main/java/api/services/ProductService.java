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
import java.util.ArrayList;
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
        Map<String, Object> query = Map.of("slug", slug);
        return sendGet(Routes.SINGLE_CATEGORY, null, query, loginAs);
    }

    public Response createCategory(String parentId) {
        return sendPost(Routes.CATEGORIES, new RequestPayloads().createCategoryPayload(parentId), null, ADMIN);
    }

    public Response getProducts(String path, String slug) {
        Map<String, Object> param = Map.of("slug", slug);
        return sendGet(path, param, null, ADMIN);
    }

    public Response updateProduct(LoginAs loginAs) {
        Map<String, Object> body = Map.of("name", newProductName);
        return sendPut(Routes.UPDATE_PRODUCT, body, null, loginAs);
    }

    public Response deleteProduct(String id) {
        String finalUrl = Routes.DELETE_PRODUCT;
        Map<String, Object> param = Map.of("id", id);
        return sendDelete(finalUrl, param, ADMIN);
    }

    public Response createProduct() {
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPost(Routes.PRODUCT, requestPayloads, null, ADMIN);
    }

    public String getProductSlug() {
        Response response = getProducts((Routes.PRODUCT), null);
        return response.path("data[0].slug");
    }

    public List<String> getProductIds(Response response) {
        String productId = response.jsonPath().getString("data.id");
        String variantId = response.jsonPath().getString("data.variants[0].id");

        List<String> ids = new ArrayList<>();
        ids.add(productId);
        ids.add(variantId);

        return ids;
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

    public Response getRelatedProducts(LoginAs loginAs) {
        return sendGet(Routes.RELATED_PRODUCT, null, null, loginAs);
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
            String id = new ProductService().getProductIds(createProduct()).getFirst();
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
}