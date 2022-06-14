package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * ConsoleViewer类
 *
 * @author wangjixue
 * @date 6/14/22 11:40 PM
 */
public class ConsoleViewer implements StatViewer{
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        System.err.println(JSON.toJSONString(requestStats));
    }
}
