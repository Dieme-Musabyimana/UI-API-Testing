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
    // Kept these fields exactly as they were so other classes can use them
    TokenManager tokenManager;
    String token;
    String refreshToken;

    public ProductService (){
        this.tokenManager = new TokenManager();
        // FIXED: These now dynamically read from the cache!
        // No redundant API login loops will happen here anymore.
        this.token = tokenManager.getToken();
        this.refreshToken = tokenManager.getRefreshToken();
    }

    public final static String newProductName = FakerUtils.getFirstName();

    public Response getProductCategories(){
        return sendGet(Routes.CATEGORIES);
    }

    public Response getSingleCategory(){
        return sendGet(Routes.SINGLE_CATEGORY);
    }

    public Response createCategory(){
        // Fixed: Stopped a recursive method loop bug from your original code
        return sendPost(Routes.CATEGORIES, new RequestPayloads().createProductBody());
    }

    public Response getProducts(String path){
        return sendGet(path);
    }

    public Response updateProduct(String path, Object object, String token){
        return sendPutWithAuth(path, object, token);
    }

    // METHOD SIGNATURE MATCHED: Other tests calling deleteProduct(token, id) will still work perfectly
    public Response deleteProduct(String token, String id){
        // Fix for the delete endpoint: ensures path evaluates to /products/{id} instead of just /products
        String finalUrl = Routes.DELETE_PRODUCT + id;
        return sendDeleteWithAuth(finalUrl, token);
    }

    // METHOD SIGNATURE MATCHED: Other tests calling createProduct(token) will still work perfectly
    public Response createProduct(String token){
        ProductRequest requestPayloads = new RequestPayloads().createProductBody();
        return sendPostWithAuth(Routes.PRODUCT, requestPayloads, token);
    }

    public String getProductSlug(){
        Response response = getProducts((Routes.PRODUCT));
        return response.path("data[0].slug");
    }

    public List<String> getProductIds(){
        Response response = createProduct(token);

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
        return sendPostMultipartWithAuth(Routes.UPLOAD_IMAGE, testImage, "images", token);
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