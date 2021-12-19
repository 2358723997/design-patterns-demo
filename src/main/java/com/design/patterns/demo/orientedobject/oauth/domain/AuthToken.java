package com.design.patterns.demo.orientedobject.oauth.domain;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import lombok.Getter;

/**
 * AuthToken类
 *
 * @author wangjixue
 * @date 12/19/21 3:37 PM
 */
@Getter
public class AuthToken {

    private static final long DEFAULT_TIMEOUT = 60 * 1000l;

    private String token;

    private Long timestamp;

    private boolean isExpired = true;

    public AuthToken(String token, Long timestamp) {

        this.token = token;
        this.timestamp = timestamp;
        if(System.currentTimeMillis() - timestamp < DEFAULT_TIMEOUT){
            this.isExpired = false;
        }
    }

    public static AuthToken generate(String originalUrl, String appId, String password, Long timestamp) {
        String token = null;
        Base64.Encoder encoder = Base64.getUrlEncoder();
        try {
            byte[] bytes = String.join("&", originalUrl, appId, password, String.valueOf(timestamp)).getBytes("UTF-8");
            token = new String(encoder.encode(bytes),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new AuthToken(token,timestamp);
    }

    public boolean match(AuthToken clientAuthToken) {

        return token.equals(clientAuthToken);
    }
}
