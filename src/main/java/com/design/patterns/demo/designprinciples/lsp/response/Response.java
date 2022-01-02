package com.design.patterns.demo.designprinciples.lsp.response;

import org.apache.http.HttpResponse;

/**
 * Response类
 *
 * @author wangjixue
 * @date 1/2/22 4:20 PM
 */
public class Response {
    private HttpResponse httpResponse;


    public Response(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
