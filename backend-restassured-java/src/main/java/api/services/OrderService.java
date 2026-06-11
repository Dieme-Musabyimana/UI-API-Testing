package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.Map;

import static api.utils.TokenManager.getToken;

public class OrderService extends BaseService {


    public Response placeOder(){
        RequestPayloads requestPayloads = new RequestPayloads();
        Object requestBody = requestPayloads.orderPayload();
        return sendPostWithAuth(Routes.ORDERS, requestBody);
    }

    public Response getUserOder(String status,  int page){
        return sendGetWithAuth(Routes.ORDERS, Map.of("status", status, "page", page));
    }

    public Response getSingleOrder(String id){
        String path = Routes.ORDERS + "/" + id;
        return sendGetWithAuth(path, null);
    }

    public Response cancelOrder(String id){
        return sendPatchWithAuth(Routes.CANCEL_ORDER, null, Map.of("id", id));
    }
}
