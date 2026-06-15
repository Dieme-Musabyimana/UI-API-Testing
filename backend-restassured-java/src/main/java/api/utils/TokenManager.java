//package api.utils;
//
//import api.POJOs.requestPOJO.LoginPOJO;
//import api.base.BaseService;
//import api.payloads.RequestPayloads;
//import api.routes.Routes;
//
//public class TokenManager {
//
//    private static String cachedToken;
//    private static String cachedRefreshToken;
//
//    public TokenManager() {
//    }
//
//    public static synchronized String getToken() {
//        if (cachedToken == null) {
//            System.out.println("[AUTH] Token not found in memory. Authenticating via API...");
//            LoginPOJO loginData = RequestPayloads.createLoginBody();
//            var response = new BaseService().sendPost(Routes.LOGIN, loginData, null, false);
//
//            cachedToken = response.jsonPath().getString("data.token");
//            cachedRefreshToken = response.jsonPath().getString("data.refreshToken");
//
//            if (cachedToken == null) {
//                throw new RuntimeException("Failed to retrieve authentication tokens.");
//            }
//        }
//        return cachedToken;
//    }
//
//    public static synchronized String getRefreshToken() {
//        if (cachedRefreshToken == null) {
//            getToken();
//        }
//        return cachedRefreshToken;
//    }
//
//    public static void clearCache() {
//        cachedToken = null;
//        cachedRefreshToken = null;
//    }
//}

package api.utils;

import api.POJOs.requestPOJO.LoginPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static api.utils.LoginAs.ADMIN;

public class TokenManager {

    private static final Map<LoginAs, String> tokenCache = new ConcurrentHashMap<>();
    private static final Map<LoginAs, String> refreshTokenCache = new ConcurrentHashMap<>();

    public TokenManager() {
    }

    // Overloaded for backward compatibility (defaults to ADMIN if no context is passed)
    public static synchronized String getToken() {
        return getToken(ADMIN);
    }

    // Master method to get the token for a specific user role context
    public static synchronized String getToken(LoginAs context) {
        if (!tokenCache.containsKey(context)) {
            System.out.println("[AUTH] Token not found in memory for " + context + ". Authenticating via API...");

            // Fetches the right POJO body using your switch statement inside RequestPayloads
            LoginPOJO loginData = RequestPayloads.getCredentials(context);

            // CRITICAL: We pass the extra null at the end for our dynamic method signature
            var response = new BaseService().sendPost(Routes.LOGIN, loginData, null, null);

            String token = response.jsonPath().getString("data.token");
            String refreshToken = response.jsonPath().getString("data.refreshToken");

            if (token == null) {
                throw new RuntimeException("Failed to retrieve authentication tokens for role: " + context);
            }

            // Save both tokens to their respective maps using the enum as the key
            tokenCache.put(context, token);
            refreshTokenCache.put(context, refreshToken);
        }
        return tokenCache.get(context);
    }

    // Overloaded for backward compatibility
    public static synchronized String getRefreshToken() {
        return getRefreshToken(ADMIN);
    }

    public static synchronized String getRefreshToken(LoginAs context) {
        if (!refreshTokenCache.containsKey(context)) {
            getToken(context);
        }
        return refreshTokenCache.get(context);
    }

    public static void clearCache() {
        tokenCache.clear();
        refreshTokenCache.clear();
    }

    public List<String> getAllTokens(){
        Map<String, Object> body = new RequestPayloads().login2Body();
        Response response = new BaseService().sendPost(Routes.LOGIN, body, null, null);
        String token = response.jsonPath().getString("data.token");
        String refreshToken = response.jsonPath().getString("data.refreshToken");
        List<String> allTokens = new ArrayList<>();
        allTokens.add(token);
        allTokens.add(refreshToken);
        return allTokens;
    }
}