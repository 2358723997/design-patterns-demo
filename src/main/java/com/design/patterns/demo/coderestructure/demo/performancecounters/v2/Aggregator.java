package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Aggregator类
 *
 * Aggregator 类负责根据原始数据计算统计数据。
 *
 * @author wangjixue
 * @date 6/8/22 11:10 PM
 */
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfoList, long durationInMillis){
        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MIN_VALUE;
        double avgRespTime = -1;
        double p999RespTime = -1;
        double p99RespTime = -1;
        double sumRespTime = -1;
        long count =0;

        for (RequestInfo info : requestInfoList) {
            ++count;
            double responseTime = info.getResponseTime();

            if(maxRespTime < responseTime){
                maxRespTime = responseTime;
            }

            if(minRespTime > responseTime){
                minRespTime = responseTime;
            }

            sumRespTime += responseTime;

        }

        if(count != 0){
            avgRespTime = sumRespTime/count;
        }
        long tps = (long)(count/durationInMillis * 1000);

        Collections.sort(requestInfoList, new Comparator<RequestInfo>() {
            @Override
            public int compare(RequestInfo o1, RequestInfo o2) {

                double diff = o1.getResponseTime() - o2.getResponseTime();
                if(diff > 0.0){
                    return 1;
                }else if(diff < 0.0){
                    return -1;
                }else {
                    return 0;
                }
            }
        });

       int idx999  = (int)(count * 0.999);
       int idx99  = (int)(count * 0.99);

       if(count != 0){
           p999RespTime = requestInfoList.get(idx999).getResponseTime();
           p99RespTime = requestInfoList.get(idx99).getResponseTime();
       }

       RequestStat stat = new RequestStat();

        stat.setMaxResponseTime(maxRespTime);
        stat.setMinResponseTime(minRespTime);
        stat.setAvgResponseTime(avgRespTime);
        stat.setP999ResponseTime(p999RespTime);
        stat.setP99ResponseTime(p99RespTime);
        stat.setCount(count);
        stat.setTps(tps);

       return stat;
    }
}
