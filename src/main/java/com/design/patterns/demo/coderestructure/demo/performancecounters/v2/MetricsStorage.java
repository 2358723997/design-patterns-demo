package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import java.util.List;
import java.util.Map;

/**
 * MetricsStorage接口
 *
 * MetricsStorage 接口负责原始数据存储，
 * RedisMetricsStorage 类实现 MetricsStorage 接口。
 * 这样做是为了今后灵活地扩展新的存储方法，比如用 HBase 来存储。
 * @author wangjixue
 * @date 6/8/22 11:09 PM
 */
public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimstamp, long endTimestamp);

    Map<String,List<RequestInfo>> getRequestInfos(long startTimstamp, long endTimestamp);
}
