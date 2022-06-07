package com.design.patterns.demo.coderestructure.demo.performancecounters.v1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

/**
 * Metrics类
 *
 * 项目背景：
 *
 * 我们希望设计开发一个小的框架，能够获取接口调用的各种统计信息，
 * 比如，响应时间的最大值（max）、最小值（min）、平均值（avg）、
 * 百分位值（percentile）、接口调用次数（count）、频率（tps） 等，
 * 并且支持将统计结果以各种显示格式（比如：JSON 格式、网页格式、自定义显示格式等）
 * 输出到各种终端（Console 命令行、HTTP 网页、Email、日志文件、自定义输出终端等），
 * 以方便查看。
 *
 * 需求分析：
 *
 * 1. 功能性需求分析
 * 1.1接口统计信息：包括接口响应时间的统计信息，以及接口调用次数的统计信息等。
 * 1.2统计信息的类型：max、min、avg、percentile、count、tps 等。
 * 1.3统计信息显示格式：Json、Html、自定义显示格式。
 * 1.4统计信息显示终端：Console、Email、HTTP 网页、日志、自定义显示终端。
 *
 * 2. 隐藏需求
 * 2.1 统计触发方式：包括主动和被动两种。主动表示以一定的频率定时统计数据，并主动推送到显示终端，
 * 比如邮件推送。被动表示用户触发统计，比如用户在网页中选择要统计的时间区间，触发统计，并将结果显示给用户。
 * 2.2 统计时间区间：框架需要支持自定义统计时间区间，比如统计最近 10 分钟的某接口的 tps、访问次数，
 * 或者统计 12 月 11 日 00 点到 12 月 12 日 00 点之间某接口响应时间的最大值、最小值、平均值等。
 * 2.3 统计时间间隔：对于主动触发统计，我们还要支持指定统计时间间隔，也就是多久触发一次统计显示。
 * 比如，每间隔 10s 统计一次接口信息并显示到命令行中，每间隔 24 小时发送一封统计信息邮件。
 *
 * 3. 非功能性需求分析
 * 3.1 易用性
 * 3.2 性能
 * 3.3 扩展性
 * 3.4 容错性
 * 3.5 通用性
 *
 * 最小原型的代码需求：
 *
 * 统计用户注册、登录这两个接口的响应时间的最大值和平均值、接口调用次数，
 * 并且将统计结果以 JSON 的格式输出到命令行中。
 * @author wangjixue
 * @date 6/7/22 10:23 PM
 */
public class Metrics {
    //Map的key是接口名称，value对应接口请求的响应时间或时间戳；
    private Map<String, List<Double>> responseTimes = new HashMap<>();
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    /**
     * 记录接口响应时长
     *
     * @param apiName
     * @param responseTime
     */
    public void recordResponseTime(String apiName, double responseTime){
        responseTimes.putIfAbsent(apiName,new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    /**
     * 记录访问时间
     *
     * @param apiName
     * @param timestamp
     */
    public void recordTimestamp(String apiName, double timestamp){
        timestamps.putIfAbsent(apiName,new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    /**
     * 指定的频率统计数据并输出结果
     */
    public void startRepeatedReport(long period, TimeUnit unit){
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Map<String,Map<String,Double>> stats = new HashMap<>();
                responseTimes.forEach((apiName,apiRespTimes)->{
                    stats.putIfAbsent(apiName,new HashMap<>());
                    stats.get(apiName).put("max",max(apiRespTimes));
                    stats.get(apiName).put("avg",avg(apiRespTimes));
                });

                timestamps.forEach((apiName,apiTimestamps)->{
                    stats.putIfAbsent(apiName,new HashMap<>());
                    stats.get(apiName).put("count",(double)apiTimestamps.size());
                });

                System.err.println(JSON.toJSONString(stats));
            }
        },0,period,unit);
    }

    private double max(List<Double> dataset){
        return dataset.parallelStream().max(Comparator.comparing(Double::doubleValue)).get();
    }

    private double avg(List<Double> dataset){
        return dataset.parallelStream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }
}
