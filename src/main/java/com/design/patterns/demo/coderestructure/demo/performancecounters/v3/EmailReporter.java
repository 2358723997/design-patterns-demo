package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * EmailReporter类
 *
 * @author wangjixue
 * @date 6/14/22 11:58 PM
 */
public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.HOUR_OF_DAY,0);
        calendar.add(Calendar.MINUTE,0);
        calendar.add(Calendar.SECOND,0);
        calendar.add(Calendar.MILLISECOND,0);
        Date fristTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMills = DAY_HOURS_IN_SECONDS * 1000;
                long endInMills = System.currentTimeMillis();
                long startInMills = System.currentTimeMillis() - durationInMills;

                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startInMills, endInMills);
                Map<String, RequestStat> statMap = aggregator.aggregate(requestInfos, durationInMills);
                viewer.output(statMap,startInMills,endInMills);
            }
        },fristTime,DAY_HOURS_IN_SECONDS * 1000);
    }
}
