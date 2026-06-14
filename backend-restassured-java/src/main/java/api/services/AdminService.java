package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

public class AdminService extends BaseService {
    public Response createCoupon(){
        RequestPayloads requestPayloads = new RequestPayloads();
        Object payload = requestPayloads.createCouponPayload();
        return sendPost(Routes.COUPON, payload, null,true);
    }
}
