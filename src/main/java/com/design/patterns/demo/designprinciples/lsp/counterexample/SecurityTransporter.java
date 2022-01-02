package com.design.patterns.demo.designprinciples.lsp.counterexample;

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
 * 在改造之后的代码中如果传递给 demoFunction()函数的是子类SecurityTransporter对象，
 * 那demoFunction()有可能会有异常抛出。尽管代码中抛出的是运行时异常(Runtime Exception),
 * 我们可以不在代码中显式地捕获处理，但子类替换父类传递进demoFunction函数之后，整个程序的逻辑行为有了改变。
 * 虽然改造之后的代码仍然可以通过Java的多态语法，动态地用子类SecurityTransporter来替换父类Transporter,
 * 也并不会导致程序编译或者运行报错。但是，从设计思路上来讲，SecurityTransporter 的设计是不符合里式替换原则的。
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
        if(StringUtils.isBlank(appId) || StringUtils.isBlank(appToken)){
            throw new NoAuthorizationRuntimeException();
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);

        return super.sendRequest(request);
    }
}
