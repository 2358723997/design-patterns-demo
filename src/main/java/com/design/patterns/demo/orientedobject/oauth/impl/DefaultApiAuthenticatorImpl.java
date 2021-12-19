package com.design.patterns.demo.orientedobject.oauth.impl;

import com.design.patterns.demo.orientedobject.oauth.ApiAuthenticator;
import com.design.patterns.demo.orientedobject.oauth.domain.AuthToken;
import com.design.patterns.demo.orientedobject.oauth.manager.CredentialStorage;
import com.design.patterns.demo.orientedobject.oauth.request.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DefaultApiAuthenticatorImpl类
 *
 * @author wangjixue
 * @date 12/19/21 2:53 PM
 */
public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {

    @Autowired
    private CredentialStorage credentialStorage;


    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        Long timestamp = apiRequest.getTimestamp();
        String originalUrl = apiRequest.getOriginalUrl();

        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()){
            throw new RuntimeException("Token is expired.");
        }

        String password = credentialStorage.getPasswordByAppId(appId);

        AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
