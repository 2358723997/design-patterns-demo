package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import lombok.Data;

/**
 * RequestStat类
 *
 * @author wangjixue
 * @date 6/8/22 11:10 PM
 */
@Data
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
