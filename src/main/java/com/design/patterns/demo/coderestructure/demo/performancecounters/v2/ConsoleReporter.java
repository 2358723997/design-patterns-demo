package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import io.reactivex.internal.schedulers.ExecutorScheduler;

/**
 * ConsoleReporter类
 *
 * ConsoleReporter 类、EmailReporter 类分别负责以一定频率统计并发送统计数据到命令行和邮件。
 * 至于 ConsoleReporter 和 EmailReporter 是否可以抽象出可复用的抽象类，或者抽象出一个公共的接口，
 * 我们暂时还不能确定。
 *
 * 我们把统计显示所要完成的功能逻辑细分一下的话，主要包含下面 4 点：
 *
 * 1.根据给定的时间区间，从数据库中拉取数据；
 * 2.根据原始数据，计算得到统计数据；
 * 3.将统计数据显示到终端（命令行或邮件）；
 * 4.定时触发以上 3 个过程的执行。
 *
 * @author wangjixue
 * @date 6/8/22 11:11 PM
 */
public class ConsoleReporter {

    private MetricsStorage storage;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage storage) {
        this.storage = storage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(startTimeInMillis, endTimeInMillis);

                Map stats = new HashMap<>();
                requestInfos.forEach((apiName,requestInfoList)->{
                    // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                    RequestStat stat = Aggregator.aggregate(requestInfoList, durationInMillis);
                    stats.put(apiName,stat);
                });
                // 第3个代码逻辑：将统计数据显示到终端（命令行）；
                System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
                System.err.println(JSON.toJSONString(stats));
            }
        },0,periodInSeconds, TimeUnit.SECONDS);
    }

}
