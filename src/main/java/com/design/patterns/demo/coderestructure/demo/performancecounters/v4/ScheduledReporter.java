package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.List;
import java.util.Map;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.Aggregator;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.StatViewer;

/**
 * ScheduledReporter类
 *
 * 将 ConsoleReporter 和 EmailReporter 中的相同代码逻辑，提取到父类 ScheduledReporter 中，以解决代码重复问题。
 * @author wangjixue
 * @date 6/15/22 11:19 PM
 */
public abstract class ScheduledReporter {
    private MetricsStorage storage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public ScheduledReporter(MetricsStorage storage, Aggregator aggregator, StatViewer viewer) {
        this.storage = storage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> statMap = aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(statMap,startTimeInMillis,endTimeInMillis);
    }
}
