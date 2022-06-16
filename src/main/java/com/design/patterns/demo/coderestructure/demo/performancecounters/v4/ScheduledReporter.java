package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.ArrayList;
import java.util.HashMap;
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
 * 将 ConsoleReporter 和 EmailReporter 中的相同代码逻辑，提取到父类 ScheduledReporter 中，
 * 以解决代码重复问题。
 *
 * 性能问题: 当需要聚合统计的数据量比较大的时候，一次性加载太多的数据到内存，有可能会导致内存吃紧，
 * 甚至内存溢出，这样整个系统都会瘫痪掉。
 *
 * 针对该问题，解决的思路比较简单，但代码实现稍微有点复杂。当统计的时间间隔较大的时候，需要统计的
 * 数据量就会比较大。我们可以将其划分为一些小的时间区间（比如 10 分钟作为一个统计单元），针对每个小的
 * 时间区间分别进行统计，然后将统计得到的结果再进行聚合，得到最终整个时间区间的统计结果。不过，这个思路只
 * 适合响应时间的 max、min、avg，及其接口请求 count、tps 的统计，对于响应时间的 percentile 的统计并
 * 不适用。
 *
 * 对于 percentile 的统计要稍微复杂一些，具体的解决思路是这样子的：我们分批从 Redis 中读取数据，然后存储
 * 到文件中，再根据响应时间从小到大利用外部排序算法来进行排序（具体的实现方式可以看一下《数据结构与算法之美》
 * 专栏）。排序完成之后，再从文件中读取第 count*percentile（count 表示总的数据个数，percentile
 * 就是百分比，99 百分位就是 0.99）个数据，就是对应的 percentile 响应时间。
 *
 * @author wangjixue
 * @date 6/15/22 11:19 PM
 */
public abstract class ScheduledReporter {
    private static final long MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000; // 10minutes

    private MetricsStorage storage;
    private Aggregator aggregator;
    private StatViewer viewer;

    public ScheduledReporter(MetricsStorage storage, Aggregator aggregator, StatViewer viewer) {
        this.storage = storage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        Map<String, RequestStat> statMap = doStat(startTimeInMillis, endTimeInMillis);
        viewer.output(statMap, startTimeInMillis, endTimeInMillis);
    }

    private Map<String, RequestStat> doStat(long startTimeInMillis, long endTimeInMillis) {
        Map<String, List<RequestStat>> segmentStats = new HashMap<>();

        long segmentStartTimeInMillis = startTimeInMillis;

        while (segmentStartTimeInMillis < endTimeInMillis) {
            long segmentEndTimeInMillis = segmentStartTimeInMillis + MAX_STAT_DURATION_IN_MILLIS;
            if (segmentEndTimeInMillis > endTimeInMillis) {
                segmentEndTimeInMillis = endTimeInMillis;
            }
            Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(segmentStartTimeInMillis, segmentEndTimeInMillis);
            if (requestInfos == null || requestInfos.isEmpty()) {
                continue;
            }
            long segmentDurationInMillis = segmentEndTimeInMillis - segmentStartTimeInMillis;
            Map<String, RequestStat> segmentStat = aggregator.aggregate(requestInfos, segmentDurationInMillis);

            addStat(segmentStats, segmentStat);

            segmentStartTimeInMillis += MAX_STAT_DURATION_IN_MILLIS;

        }
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, RequestStat> statMap = aggregateStats(segmentStats, durationInMillis);
        return statMap;
    }

    private void addStat(Map<String, List<RequestStat>> segmentStats, Map<String, RequestStat> segmentStat) {
        segmentStat.forEach((apiName, stat) -> {
            List<RequestStat> requestStats = segmentStats.putIfAbsent(apiName, new ArrayList<>());
            requestStats.add(stat);
        });
    }

    private Map<String, RequestStat> aggregateStats(Map<String, List<RequestStat>> segmentStats, long durationInMillis) {
        Map<String, RequestStat> statMap = new HashMap<>();
        segmentStats.forEach((apiName, statList) -> {

            double maxRespTime = Double.MIN_VALUE;
            double minRespTime = Double.MAX_VALUE;
            long count = 0;
            double sumRespTime = 0;
            for (RequestStat stat : statList) {

                if (stat.getMaxResponseTime() > maxRespTime) {
                    maxRespTime = stat.getMaxResponseTime();
                }

                if (stat.getMinResponseTime() < minRespTime) {
                    minRespTime = stat.getMinResponseTime();
                }
                count += stat.getCount();
                sumRespTime += (stat.getAvgResponseTime() * stat.getCount());

            }
            RequestStat stat = new RequestStat();
            stat.setMaxResponseTime(maxRespTime);
            stat.setMinResponseTime(minRespTime);
            stat.setCount(count);
            stat.setAvgResponseTime(sumRespTime / count);
            stat.setTps(count / durationInMillis * 1000);
            //TODO 百分比统计

            statMap.put(apiName, stat);
        });
        return statMap;
    }
}
