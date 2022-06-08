package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import java.util.List;
import java.util.Map;

/**
 * RedisMetricsStorage类
 *
 * @author wangjixue
 * @date 6/8/22 11:10 PM
 */
public class RedisMetricsStorage implements MetricsStorage{
    //TODO 添加属性,实现方法

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimstamp, long endTimestamp) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimstamp, long endTimestamp) {
        return null;
    }
}
