package com.design.patterns.demo.designprinciples.lsp.request;

import lombok.Getter;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Request类
 *
 * @author wangjixue
 * @date 1/2/22 4:20 PM
 */
@Getter
public class Request {

    private HttpUriRequest httpRequest;

    public Request() {
    }

    public Request(HttpUriRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    //TODO 未实现
    public void addPayload(String key, String value) {
    }
}
