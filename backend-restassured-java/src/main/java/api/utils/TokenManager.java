package api.utils;

import api.POJOs.requestPOJO.LoginPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;

public class TokenManager {

    private static String cachedToken;
    private static String cachedRefreshToken;

    public TokenManager() {
    }

    public static synchronized String getToken() {
        if (cachedToken == null) {
            System.out.println("🔑 [AUTH] Token not found in memory. Authenticating via API...");
            LoginPOJO loginData = RequestPayloads.createLoginBody();

            // Explicitly pass 'false' to the single sendPost method to bypass auth check
            var response = new BaseService().sendPost(Routes.LOGIN, loginData, false);

            cachedToken = response.jsonPath().getString("data.token");
            cachedRefreshToken = response.jsonPath().getString("data.refreshToken");

            if (cachedToken == null) {
                throw new RuntimeException("Failed to retrieve authentication tokens.");
            }
        }
        return cachedToken;
    }

    public static synchronized String getRefreshToken() {
        if (cachedRefreshToken == null) {
            getToken();
        }
        return cachedRefreshToken;
    }

    public static void clearCache() {
        cachedToken = null;
        cachedRefreshToken = null;
    }
}