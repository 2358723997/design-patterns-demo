package com.design.patterns.demo.orientedobject.oauth;

import com.design.patterns.demo.orientedobject.oauth.request.ApiRequest;

/**
 * ApiAuthenticator接口
 *
 * @author wangjixue
 * @date 12/19/21 2:49 PM
 */
public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}
