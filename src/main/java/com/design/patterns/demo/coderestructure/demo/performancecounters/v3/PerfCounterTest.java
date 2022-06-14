package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsCollector;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RedisMetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;

/**
 * PerfCounterTest类
 *
 * @author wangjixue
 * @date 6/15/22 12:05 AM
 */
public class PerfCounterTest {
    public static void main(String[] args) {

        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        StatViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage,aggregator,consoleViewer);
        consoleReporter.startRepeatedReport(60,60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangjixue@163.com");
        EmailReporter emailReporter = new EmailReporter(storage,aggregator,emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
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
