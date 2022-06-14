package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * Aggregator类
 *
 * Aggregator 类负责根据原始数据计算统计数据。
 *
 * 根据原始数据，计算得到统计数据。我们可以将这部分逻辑移动到 Aggregator 类中
 *
 * @author wangjixue
 * @date 6/8/22 11:10 PM
 */
public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> statMap = new HashMap<>();
        requestInfos.forEach((apiName, list) -> {

            RequestStat stat = doAggregate(list, durationInMillis);
            statMap.put(apiName, stat);
        });
        return statMap;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfoList, long durationInMillis) {

        List<Double> respTimes = requestInfoList.parallelStream().map(RequestInfo::getResponseTime).collect(Collectors.toList());

        RequestStat stat = new RequestStat();

        stat.setMaxResponseTime(max(respTimes));
        stat.setMinResponseTime(min(respTimes));
        stat.setAvgResponseTime(avg(respTimes));
        stat.setP999ResponseTime(percentile999(respTimes));
        stat.setP99ResponseTime(percentile99(respTimes));
        stat.setCount(respTimes.size());
        stat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));

        return stat;
    }

    private double max(List<Double> dataset) {
        double maxRespTime = Double.MIN_VALUE;
        for (Double responseTime : dataset) {
            if (maxRespTime < responseTime) {
                maxRespTime = responseTime;
            }
        }
        return maxRespTime;
    }

    private double min(List<Double> dataset) {
        double minRespTime = Double.MIN_VALUE;
        for (Double responseTime : dataset) {
            if (minRespTime > responseTime) {
                minRespTime = responseTime;
            }
        }
        return minRespTime;
    }

    private double avg(List<Double> dataset) {
        double avgRespTime = -1;
        double sumRespTime = 0;
        for (Double responseTime : dataset) {
            sumRespTime += responseTime;
        }
        avgRespTime = sumRespTime / dataset.size();
        return avgRespTime;
    }

    private double tps(int count, double duration) {
        return count / duration;
    }

    private double percentile999(List<Double> dataset) {
        return percentile(dataset, 0.999);
    }

    private double percentile99(List<Double> dataset) {

        return percentile(dataset, 0.99);
    }

    private double percentile(List<Double> dataset, double ratio) {
        Collections.sort(dataset, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {

                double diff = o1 - o2;
                if (diff > 0.0) {
                    return 1;
                } else if (diff < 0.0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int index = (int) (dataset.size() * ratio);
        return dataset.get(index);
    }
}
