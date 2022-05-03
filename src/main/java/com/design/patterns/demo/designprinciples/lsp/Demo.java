package com.design.patterns.demo.designprinciples.lsp;

import java.io.IOException;

import com.design.patterns.demo.designprinciples.lsp.exceptions.NoAuthorizationRuntimeException;
import com.design.patterns.demo.designprinciples.lsp.positiveexample.SecurityTransporter;
import com.design.patterns.demo.designprinciples.lsp.request.Request;
import com.design.patterns.demo.designprinciples.lsp.response.Response;
import org.apache.http.client.HttpClient;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 1/2/22 4:30 PM
 */
public class Demo {

    public void demoFunction(Transporter transporter) throws IOException, NoAuthorizationRuntimeException {
        Request request = new Request();
        Response response = transporter.sendRequest(request);
    }

    public static void main(String[] args) throws IOException, NoAuthorizationRuntimeException {
        Demo demo = new Demo();
        //里式替换原则
        HttpClient httpClient = null;
        demo.demoFunction(new SecurityTransporter(httpClient,"appId","appToken"));
    }

}
