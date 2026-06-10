package api.utils;

import api.POJOs.requestPOJO.LoginPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;

public class TokenManager extends BaseService {
    private final String path;

    // Shared globally across all class instances in memory
    private static String cachedToken;
    private static String cachedRefreshToken;

    public TokenManager() {
        this.path = Routes.LOGIN;
    }

    public synchronized String getToken() {
        // Only hit the backend if we don't already have a token saved
        if (cachedToken == null) {
            System.out.println("🔑 [AUTH] Token not found in memory. Authenticating via API...");
            LoginPOJO loginData = RequestPayloads.createLoginBody();
            var response = sendPost(path, loginData);

            cachedToken = response.jsonPath().getString("data.token");
            cachedRefreshToken = response.jsonPath().getString("data.refreshToken");

            if (cachedToken == null) {
                throw new RuntimeException(Config.getRunTimeException());
            }
        }
        return cachedToken;
    }

    public synchronized String getRefreshToken() {
        if (cachedRefreshToken == null) {
            getToken(); // This automatically fetches and populates both tokens
        }
        return cachedRefreshToken;
    }

    // Helper to reset the session if you ever need to test an invalid session explicitly
    public static void clearCache() {
        cachedToken = null;
        cachedRefreshToken = null;
    }
}