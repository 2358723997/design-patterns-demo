package com.design.patterns.demo.designprinciples.ocp.positiveexample;

import com.design.patterns.demo.designprinciples.ocp.positiveexample.context.ApplicationContext;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 1/2/22 3:44 PM
 */
public class Demo {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
