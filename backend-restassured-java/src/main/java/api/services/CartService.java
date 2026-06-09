package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

public class CartService extends BaseService {


    public Response getCurrentCart(){
        return sendGet(Routes.CART);
    }

    public Response clearEntireCart(){
        return sendDeleteWithAuth(Routes.CART, new AuthService().getLoginToken());
    }

    public Response addToCart(){
        return sendPostWithAuth(Routes.ADD_TO_CART, new RequestPayloads().addToCartPayload(), new AuthService().getLoginToken());
    }
}