package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.Map;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * StatViewer接口
 *
 * 将统计数据显示到终端。我们将这部分逻辑剥离出来，
 * 设计成两个类：ConsoleViewer 类和 EmailViewer 类，分别负责将统计结果显示到命令行和邮件中。
 * @author wangjixue
 * @date 6/14/22 11:39 PM
 */
public interface StatViewer {

    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
