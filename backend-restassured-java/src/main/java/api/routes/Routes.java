package api.routes;

import api.utils.Config;

public class Routes {
    public static final String LOGIN = "/auth/login";
    public static final String REGISTER = "/auth/register";
    public static final String GET_ME = "/auth/me";
    public static final String REFRESH_TOKEN = "/auth/refresh";

    public static final String FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String VERIFY_EMAIL = "/auth/verify-email/";
    public static final String RESET_PASSWORD = "/auth/reset-password/";

    public static final String UPDATE_PROFILE = "/users/profile";
    public static final String UPLOAD_AVATAR = "/users/avatar";
    public static final String CHANGE_PASSWORD = "/users/change-password";
    public static final String ADDRESS = "/users/addresses";
    public static final String CATEGORIES = "/categories";
    public static final String SINGLE_CATEGORY = CATEGORIES + Config.getCategorySlug();
    public static final String PRODUCT = "/products";
    public static final String SINGLE_PRODUCT = PRODUCT + "/" + Config.getProductSlug();
    public static final String UPDATE_PRODUCT = PRODUCT + "/" + Config.getProductId();
    public static final String DELETE_PRODUCT = PRODUCT + "/" + Config.getIdToDelete();
    public static final String UPLOAD_IMAGE = PRODUCT + "/59b5ea21-15b1-4c26-87f6-46d09c18510f/images";
    public static final String TRENDING = PRODUCT + "/" + "trending";
    public static final String FLESH_SALES = PRODUCT + "/flash-sales";

}

