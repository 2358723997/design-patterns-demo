package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import org.apache.commons.lang3.StringUtils;

/**
 * MetricsCollector类
 *
 * MetricsCollector 类负责提供 API，来采集接口请求的原始数据。
 * 我们可以为 MetricsCollector 抽象出一个接口，但这并不是必须的，
 * 因为暂时我们只能想到一个 MetricsCollector 的实现方式。
 * @author wangjixue
 * @date 6/8/22 11:09 PM
 */
public class MetricsCollector {
    //基于接口而非实现编程
    private MetricsStorage storage;


    //依赖注入
    public MetricsCollector(MetricsStorage storage) {
        this.storage = storage;
    }

    //用一个函数替换最小原型的两个函数
    public void recordRequest(RequestInfo requestInfo){
        if(requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())){
            return;
        }
        storage.saveRequestInfo(requestInfo);
    }
}
