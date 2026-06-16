package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.LoginAs;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;

public class CartService extends BaseService {

    public Response getCurrentCart(LoginAs loginAs){
        return sendGet(Routes.CART, null, null, loginAs);
    }

    public Response clearEntireCart(){
        return sendDelete(Routes.CART, null, ADMIN);
    }

    public Response addToCart(LoginAs loginAs){
        Response response = new ProductService().createProduct();
        String productId = new ProductService().getProductIds(response).get(0);
        String variantId = new ProductService().getProductIds(response).get(1);

        return sendPost(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload(productId, variantId), null, loginAs);
    }

    public String getCartItemId() {
        List<String> itemIds = getCurrentCart(ADMIN).jsonPath().getList("data.items.id");
        if (itemIds == null || itemIds.isEmpty()) {
            addToCart(ADMIN);
            itemIds = getCurrentCart(ADMIN).jsonPath().getList("data.items.id");
        }
        return itemIds.get(0);
    }

    public Response updateQuantity(String id, LoginAs loginAs){
        Map<String, Object> param = Map.of("itemId", id);
        return sendPut(Routes.UPDATE_QUANTITY, Map.of("quantity", 10), param, loginAs);
    }

    public Response removeItemFromCart(String id){
        Map<String, Object> param = Map.of("itemId", getCartItemId());
        return sendDelete(Routes.UPDATE_QUANTITY, param, ADMIN);
    }

    public Response saveForLaterUse(){
        return sendPatch(Routes.SAVE_FOR_LATER, null, Map.of("itemId", getCartItemId()), null, ADMIN);
    }

    public Response applyCoupon(){
        return sendPost(Routes.APPLY_COUPON, Map.of("code",  "SAVE15NOW"), null, ADMIN);
    }
}