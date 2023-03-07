package org.geekbang.projects.cs.security.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JWTTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> systemInfo = new HashMap<>();
        systemInfo.put("system", "Customer System");

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(systemInfo);
        return accessToken;
    }
}
