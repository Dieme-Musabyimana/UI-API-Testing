package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.TokenManager;
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
        return sendPatchWithAuth(Routes.CANCEL_ORDER, null, java.util.Map.of("id", id), null);
    }

    public Response getAllOrders(){
        return sendGetWithAuth(Routes.ALL_ORDES, null);
    }
    private Map<String, Object> updateOderPayload;
    public Response updateOderStatus(Map<String, Object> payload){
        Map<String, Object> param = java.util.Map.of("id", "824ba5ed-f8e5-4b08-ae51-a8dbe459c932");
        return sendPatchWithAuth(Routes.UPDATE_ORDER_STATUS, payload, param,    null);
    }

}
