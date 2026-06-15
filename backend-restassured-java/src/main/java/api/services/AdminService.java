package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.LoginAs;
import io.restassured.response.Response;

import static api.utils.LoginAs.ADMIN;

public class AdminService extends BaseService {
    public Response createCoupon(String couponCode, LoginAs loginAs){
        RequestPayloads requestPayloads = new RequestPayloads();
        Object payload = requestPayloads.createCouponPayload(couponCode);
        return sendPost(Routes.COUPON, payload, null, loginAs);
    }

    public Response getDashboardAnalytics(LoginAs loginAs){
    return sendGet(Routes.DASHBOARD, null, null, loginAs);
    }
    public Response getAllUsers(LoginAs loginAs){
        return sendGet(Routes.ALL_USERS, null, null, loginAs);
    }
}
