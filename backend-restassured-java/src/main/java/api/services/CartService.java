package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.TokenManager;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class CartService extends BaseService {
    TokenManager tokenManager;
    String token;

    public CartService(){
        this.tokenManager = new TokenManager();
        // Pulls token directly from the shared memory cache
        this.token = tokenManager.getToken();
    }

    public Response getCurrentCart(){
        return sendGetWithAuth(Routes.CART, token);
    }

    public Response clearEntireCart(){
        return sendDeleteWithAuth(Routes.CART, token);
    }

    public Response addToCart(){
        return sendPostWithAuth(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload(), token);
    }

    public String getCartItemId() {
        List<String> itemIds = getCurrentCart().jsonPath().getList("data.items.id");
        if (itemIds == null || itemIds.isEmpty()) {
            addToCart();
            itemIds = getCurrentCart().jsonPath().getList("data.items.id");
        }
        return itemIds.get(0);
    }

    public Response updateQuantity(){
        String path = Routes.UPDATE_QUANTITY + "/" + getCartItemId();
        return sendPutWithAuth(path, Map.of("quantity", 10), token);
    }

    public Response removeItemFromCart(){
        String path = Routes.UPDATE_QUANTITY + "/" + getCartItemId();
        return sendDeleteWithAuth(path, token);
    }

    public Response saveForLaterUse(){
        String path = Routes.ADD_TO_CART + "/" + getCartItemId() + "/save-for-later";
        return sendPatchWithAuth(path, Map.of("id", getCartItemId()), token);
    }

    public Response applyCoupon(){
        return sendPostWithAuth(Routes.APPLY_COUPON, Map.of("code",  "SAVE15NOW"), token);
    }
}