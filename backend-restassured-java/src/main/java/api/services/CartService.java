package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.Map;

public class CartService extends BaseService {


    public Response getCurrentCart(){
        return sendGetWithAuth(Routes.CART,new AuthService().getLoginToken());
    }

    public Response clearEntireCart(){
        return sendDeleteWithAuth(Routes.CART, new AuthService().getLoginToken());
    }

    public Response addToCart(){
        return sendPostWithAuth(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload(), new AuthService().getLoginToken());
    }

    public Response updateQuantity(){
        addToCart();
        return sendPatch(Routes.UPDATE_QUANTITY, Map.of("quantity", 10));
    }
}