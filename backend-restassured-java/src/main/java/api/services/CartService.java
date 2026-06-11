package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class CartService extends BaseService {

    public CartService(){
    }

    public Response getCurrentCart(){
        return sendGetWithAuth(Routes.CART, null);
    }

    public Response clearEntireCart(){
        return sendDeleteWithAuth(Routes.CART);
    }

    public Response addToCart(){
        return sendPostWithAuth(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload());
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
        return sendPutWithAuth(path, Map.of("quantity", 10));
    }

    public Response removeItemFromCart(){
        String path = Routes.UPDATE_QUANTITY + "/" + getCartItemId();
        return sendDeleteWithAuth(path);
    }

    public Response saveForLaterUse(){
        String path = Routes.ADD_TO_CART + "/" + getCartItemId() + "/save-for-later";
        return sendPatchWithAuth(path, Map.of("id", getCartItemId()), null);
    }

    public Response applyCoupon(){
        return sendPostWithAuth(Routes.APPLY_COUPON, Map.of("code",  "SAVE15NOW"));
    }
}