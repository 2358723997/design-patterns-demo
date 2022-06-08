package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import lombok.Data;

/**
 * RequestInfo类
 *
 * @author wangjixue
 * @date 6/8/22 11:09 PM
 */
@Data
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;

    public RequestInfo(String apiName, double responseTime, long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
