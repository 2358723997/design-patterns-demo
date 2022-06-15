package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.ArrayList;
import java.util.List;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;

/**
 * PerfCounterTest类
 *
 * 通过设置默认值，提高了代码的易用性。
 *
 * @author wangjixue
 * @date 6/15/22 12:05 AM
 */
public class PerfCounterTest {
    public static void main(String[] args) {

        // 定时触发统计并将结果显示到终端
        ConsoleReporter consoleReporter = new ConsoleReporter();
        consoleReporter.startRepeatedReport(60,60);

        // 定时触发统计并将结果输出到邮件
        List<String> emailToAddresses = new ArrayList<>();
        emailToAddresses.add("wangjixue@163.com");
        EmailReporter emailReporter = new EmailReporter(emailToAddresses);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector();

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
