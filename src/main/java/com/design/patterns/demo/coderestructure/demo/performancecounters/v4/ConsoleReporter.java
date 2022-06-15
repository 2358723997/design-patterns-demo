package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RedisMetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.Aggregator;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.ConsoleViewer;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.StatViewer;

/**
 * ConsoleReporter类
 *
 * @author wangjixue
 * @date 6/14/22 11:51 PM
 */
public class ConsoleReporter extends ScheduledReporter{

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executorService;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public ConsoleReporter() {
        this(new RedisMetricsStorage(),new Aggregator(),new ConsoleViewer());
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public ConsoleReporter(MetricsStorage storage, Aggregator aggregator, StatViewer viewer) {
        super(storage, aggregator, viewer);
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMills = durationInSeconds * 1000;
                long endInMills = System.currentTimeMillis();
                long startInMills = System.currentTimeMillis() - durationInMills;

                doStatAndReport(startInMills,endInMills);
            }
        },0L,periodInSeconds, TimeUnit.SECONDS);
    }
}
