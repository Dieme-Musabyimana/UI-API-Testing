package api.routes;

import api.payloads.RequestPayloads;
import api.services.CartService;
import api.services.ProductService;
import api.utils.Config;

public class Routes {
    public static final String LOGIN = "/auth/login";
    public static final String REGISTER = "/auth/register";
    public static final String GET_ME = "/auth/me";
    public static final String REFRESH_TOKEN = "/auth/refresh";

    public static final String FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String VERIFY_EMAIL = "/auth/verify-email/{token}";
    public static final String RESET_PASSWORD = "/auth/reset-password/{token}";

    public static final String UPDATE_PROFILE = "/users/profile";
    public static final String UPLOAD_AVATAR = "/users/avatar";
    public static final String CHANGE_PASSWORD = "/users/change-password";
    public static final String ADDRESS = "/users/addresses";
    public static final String CATEGORIES = "/categories";
    public static final String SINGLE_CATEGORY = "/categories/{slug}";
    public static final String PRODUCT = "/products";
    public static final String SINGLE_PRODUCT = "/products/{slug}";
    public static final String UPDATE_PRODUCT = PRODUCT + "/" + Config.getProductId();
    public static final String DELETE_PRODUCT = PRODUCT + "/";
    public static final String UPLOAD_IMAGE = PRODUCT + "/" + Config.getProductId() + "/images";
    public static final String TRENDING = PRODUCT + "/" + "trending";
    public static final String FLESH_SALES = PRODUCT + "/flash-sales";
    public static final String RELATED_PRODUCT = UPDATE_PRODUCT + "/related";
    public static final String CART = "/cart";
    public static final String ADD_TO_CART = CART + "/items";
    public static final String UPDATE_QUANTITY = "/cart/items/{itemId}";
    public static final String COUPON = "/admin/coupons";
    public static final String APPLY_COUPON = "/cart/coupon";
    public static final String ORDERS = "/orders";
    public static final String SINGLE_ODER = "/orders/{id}";
    public static final String CANCEL_ORDER = "/orders/{id}/cancel";
    public static final String ALL_ORDES = "/orders/admin/all";
    public static final String UPDATE_ORDER_STATUS = "/orders/admin/{id}/status";
    public static final String ORDER_RETURN = "/orders/{id}/return";
    public static final String PAYMENT_PROOF = "/orders/{id}/payment-proof";
    public static final String SAVE_FOR_LATER =     "/cart/items/{itemId}/save-for-later";
    public static final String DASHBOARD = "/admin/dashboard";
    public static final String ALL_USERS = "/admin/users";

}

