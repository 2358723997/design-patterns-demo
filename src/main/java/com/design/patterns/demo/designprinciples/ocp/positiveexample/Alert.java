package com.design.patterns.demo.designprinciples.ocp.positiveexample;

import java.util.ArrayList;
import java.util.List;

import com.design.patterns.demo.designprinciples.ocp.positiveexample.handler.AlertHandler;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;

/**
 * Alert类
 * 基于OCP设计原则，首先对代码重构，
 * 第一部分是将 check() 函数的多个入参封装成 ApiStatInfo 类；
 * 第二部分是引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中。
 *
 * 基于重构之后的代码，如果再添加上面讲到的那个新功能，每秒钟接口超时请求个数超过某个最大阈值就告警，
 * 主要的改动有下面四处。
 * 第一处改动是：在 ApiStatInfo 类中添加新的属性 timeoutCount。
 * 第二处改动是：添加新的 TimeoutAlertHander 类。
 * 第三处改动是：在 ApplicationContext 类的 initializeBeans() 方法中，往 alert
 *            对象中注册新的 timeoutAlertHandler。
 * 第四处改动是：在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值。
 *
 * 基于开闭原则的设计优点：
 *    1.代码更加灵活和易扩展。添加新的需求，只需要基于扩展的方式创建新的handler类即可，
 *      不需要改动原来的 check() 函数的逻辑，即--调用这个接口的代码无需修改。
 *    2.只需要为新的handler类添加单元测试，老的单元测试都不会失败，也不用修改。
 *
 * @author wangjixue
 * @date 1/2/22 3:10 PM
 */
public class Alert {
    private List<AlertHandler> handlerList = new ArrayList<AlertHandler>();

    public void addHandler(AlertHandler handler) {
        this.handlerList.add(handler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : handlerList) {
            handler.check(apiStatInfo);
        }
    }
}
