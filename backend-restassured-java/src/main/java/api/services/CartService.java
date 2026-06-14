package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class CartService extends BaseService {

    public Response getCurrentCart(boolean userAuth){
        return sendGet(Routes.CART, null, null, userAuth);
    }

    public Response clearEntireCart(){
        return sendDelete(Routes.CART, null, true);
    }

    public Response addToCart(boolean login){
        Response response = new ProductService().createProduct();
        String productId = new ProductService().getProductIds(response).get(0);
        String variantId = new ProductService().getProductIds(response).get(1);

        return sendPost(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload(productId, variantId), login);
    }

    public String getCartItemId() {
        List<String> itemIds = getCurrentCart(true).jsonPath().getList("data.items.id");
        if (itemIds == null || itemIds.isEmpty()) {
            addToCart(true);
            itemIds = getCurrentCart(true).jsonPath().getList("data.items.id");
        }
        return itemIds.get(0);
    }

    public Response updateQuantity(String id, boolean useAuth){
        Map<String, Object> param = Map.of("itemId", id);
        return sendPut(Routes.UPDATE_QUANTITY, Map.of("quantity", 10), param, useAuth);
    }

    public Response removeItemFromCart(String id){
        Map<String, Object> param = Map.of("itemId", getCartItemId());
        return sendDelete(Routes.UPDATE_QUANTITY, param, true);
    }

    public Response saveForLaterUse(){
        return sendPatch(Routes.SAVE_FOR_LATER, null, Map.of("itemId", getCartItemId()), null, true);
    }

    public Response applyCoupon(){
        return sendPost(Routes.APPLY_COUPON, Map.of("code",  "SAVE15NOW"), true);
    }
}