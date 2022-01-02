package com.design.patterns.demo.designprinciples.lsp;

import java.io.IOException;

import com.design.patterns.demo.designprinciples.lsp.exceptions.NoAuthorizationRuntimeException;
import com.design.patterns.demo.designprinciples.lsp.request.Request;
import com.design.patterns.demo.designprinciples.lsp.response.Response;
import org.apache.http.client.HttpClient;

/**
 * 需求描述：父类 Transporter 使用 org.apache.http库中的HttpClient类来传输网络数据。
 * 子类 SecurityTransporter 继承父类Transporter，增加了额外的功能，支持传输appId和appToken安全认证信息
 *
 * @author wangjixue
 * @date 1/2/22 4:15 PM
 */
public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient clihttpClientent) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) throws IOException, NoAuthorizationRuntimeException {
        //use httpClient to send request
        return new Response(httpClient.execute(request.getHttpRequest()));
    }
}
