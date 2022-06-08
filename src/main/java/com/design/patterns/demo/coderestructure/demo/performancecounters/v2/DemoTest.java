package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

/**
 * DemoTest类
 *
 * 将类组装起来并提供执行入口
 * 有两个执行入口：一个是 MetricsCollector 类，
 * 提供了一组 API 来采集原始数据；
 * 另一个是 ConsoleReporter 类和 EmailReporter 类
 * @author wangjixue
 * @date 6/8/22 11:57 PM
 */
public class DemoTest {

    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60,60);
        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.addToAddress("wangjixue@163.com");
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);

        collector.recordRequest(new RequestInfo("register",111,1034));
        collector.recordRequest(new RequestInfo("login",12,102134));
        collector.recordRequest(new RequestInfo("register",109,1934));
        collector.recordRequest(new RequestInfo("register",98,1834));
        collector.recordRequest(new RequestInfo("login",43,134034));

        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
