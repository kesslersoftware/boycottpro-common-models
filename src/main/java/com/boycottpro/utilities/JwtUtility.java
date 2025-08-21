package com.boycottpro.utilities;// package: com.boycottpro.common.auth
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;     // REST v1
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;          // HTTP v2
import java.util.Map;

public final class JwtUtility {
    private JwtUtility() {}

    // REST API (v1): claims at requestContext.authorizer.claims.sub
    public static String getSubFromRestEvent(APIGatewayProxyRequestEvent event) {
        if (event == null || event.getRequestContext() == null) return null;
        Map<String, Object> authz = event.getRequestContext().getAuthorizer();
        if (authz == null) return null;
        @SuppressWarnings("unchecked")
        Map<String, String> claims = (Map<String, String>) authz.get("claims");
        return claims != null ? claims.get("sub") : null;
    }

    // HTTP API (v2): claims at requestContext.authorizer.jwt.claims.sub
    public static String getSubFromHttpEvent(APIGatewayV2HTTPEvent event) {
        if (event == null || event.getRequestContext() == null || event.getRequestContext().getAuthorizer() == null)
            return null;
        var jwt = event.getRequestContext().getAuthorizer().getJwt();
        if (jwt == null || jwt.getClaims() == null) return null;
        Object sub = jwt.getClaims().get("sub");
        return sub != null ? sub.toString() : null;
    }
}
