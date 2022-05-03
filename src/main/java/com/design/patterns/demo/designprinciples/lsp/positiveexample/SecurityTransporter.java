package com.design.patterns.demo.designprinciples.lsp.positiveexample;

import java.io.IOException;

import com.design.patterns.demo.designprinciples.lsp.Transporter;
import com.design.patterns.demo.designprinciples.lsp.exceptions.NoAuthorizationRuntimeException;
import com.design.patterns.demo.designprinciples.lsp.request.Request;
import com.design.patterns.demo.designprinciples.lsp.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;

/**
 * SecurityTransporter类
 *
 * @author wangjixue
 * @date 1/2/22 4:24 PM
 */
public class SecurityTransporter extends Transporter {
    private String appId;

    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public Response sendRequest(Request request) throws IOException, NoAuthorizationRuntimeException {
        if(StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)){
            request.addPayload("app-id", appId);
            request.addPayload("app-token", appToken);
        }
        return super.sendRequest(request);
    }
}
