package com.design.patterns.demo.designprinciples.lsp;

import java.io.IOException;

import com.design.patterns.demo.designprinciples.lsp.positiveexample.SecurityTransporter;
import com.design.patterns.demo.designprinciples.lsp.request.Request;
import com.design.patterns.demo.designprinciples.lsp.response.Response;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 1/2/22 4:30 PM
 */
public class Demo {

    public void demoFunction(Transporter transporter) throws IOException {
        Request request = new Request();
        Response response = transporter.sendRequest(request);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        //里式替换原则
        demo.demoFunction(new SecurityTransporter(httpClient,"appId","appToken"));
    }

}
