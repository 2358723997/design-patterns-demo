package com.design.patterns.demo.orientedobject.oauth.request;

import lombok.Getter;

/**
 * ApiRequest类
 *
 * @author wangjixue
 * @date 12/19/21 2:51 PM
 */
@Getter
public class ApiRequest {

    private String appId;

    private String token;

    private String originalUrl;

    private Long timestamp = System.currentTimeMillis();

    public ApiRequest(String appId, String token, Long timestamp,String originalUrl) {
        this.appId = appId;
        this.token = token;
        this.timestamp = timestamp;
        this.originalUrl = originalUrl;
    }

    public static ApiRequest buildFromUrl(String url) {
        String[] split = url.split("&");
        return new ApiRequest(split[0],split[1],Long.valueOf(split[2]),url);
    }
}
