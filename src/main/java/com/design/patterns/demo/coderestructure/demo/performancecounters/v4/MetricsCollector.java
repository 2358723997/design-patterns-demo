package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RedisMetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * MetricsCollector类
 *
 * 非功能性需求：
 *
 *  易用性
 * @author wangjixue
 * @date 6/8/22 11:09 PM
 */
public class MetricsCollector {
    //基于接口而非实现编程
    private MetricsStorage storage;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public MetricsCollector() {
        this(new RedisMetricsStorage());
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public MetricsCollector(MetricsStorage storage) {
        this.storage = storage;
    }

    public void recordRequest(RequestInfo requestInfo){
        if(requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())){
            return;
        }
        storage.saveRequestInfo(requestInfo);
    }
}
