package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * ConsoleReporter类
 *
 * @author wangjixue
 * @date 6/14/22 11:51 PM
 */
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executorService;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMills = durationInSeconds * 1000;
                long endInMills = System.currentTimeMillis();
                long startInMills = System.currentTimeMillis() - durationInMills;

                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startInMills, endInMills);
                Map<String, RequestStat> statMap = aggregator.aggregate(requestInfos, durationInMills);
                viewer.output(statMap,startInMills,endInMills);
            }
        },0L,periodInSeconds, TimeUnit.SECONDS);
    }
}
