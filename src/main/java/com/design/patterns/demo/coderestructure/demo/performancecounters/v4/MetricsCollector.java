package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.EventListener;
import java.util.concurrent.Executors;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RedisMetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.StringUtils;

/**
 * MetricsCollector类
 *
 * 非功能性需求：
 *
 * 易用性、
 * 性能、
 * 对于性能计数器这个框架来说，一方面，我们希望它是低延迟的，也就是说，
 * 统计代码不影响或很少影响接口本身的响应时间；另一方面，我们希望框架本身对内存的消耗不能太大。
 *
 * 对于性能这一点，落实到具体的代码层面，需要解决两个问题，也是我们之前提到过的，
 * 一个是采集和存储要异步来执行，因为存储基于外部存储（比如 Redis），会比较慢，
 * 异步存储可以降低对接口响应时间的影响。
 *
 * 另一个是当需要聚合统计的数据量比较大的时候，一次性加载太多的数据到内存，有可能会导致内存吃紧，
 * 甚至内存溢出，这样整个系统都会瘫痪掉。
 *
 * 针对第一个问题，我们通过在 MetricsCollector 中引入 Google Guava EventBus 来解决。
 * 实际上，我们可以把 EventBus 看作一个“生产者 - 消费者”模型或者“发布 - 订阅”模型，
 * 采集的数据先放入内存共享队列中，另一个线程读取共享队列中的数据，写入到外部存储（比如 Redis）中。
 *
 * 针对第二个问题，解决的思路比较简单，但代码实现稍微有点复杂。当统计的时间间隔较大的时候，需要统计的
 * 数据量就会比较大。我们可以将其划分为一些小的时间区间（比如 10 分钟作为一个统计单元），针对每个小的
 * 时间区间分别进行统计，然后将统计得到的结果再进行聚合，得到最终整个时间区间的统计结果。不过，这个思路只
 * 适合响应时间的 max、min、avg，及其接口请求 count、tps 的统计，对于响应时间的 percentile 的统计并
 * 不适用。
 *
 * 对于 percentile 的统计要稍微复杂一些，具体的解决思路是这样子的：我们分批从 Redis 中读取数据，然后存储
 * 到文件中，再根据响应时间从小到大利用外部排序算法来进行排序（具体的实现方式可以看一下《数据结构与算法之美》
 * 专栏）。排序完成之后，再从文件中读取第 count*percentile（count 表示总的数据个数，percentile
 * 就是百分比，99 百分位就是 0.99）个数据，就是对应的 percentile 响应时间。
 *
 * @author wangjixue
 * @date 6/8/22 11:09 PM
 */
public class MetricsCollector {
    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;
    //基于接口而非实现编程
    private MetricsStorage storage;

    private EventBus eventBus;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public MetricsCollector() {
        this(new RedisMetricsStorage(), DEFAULT_STORAGE_THREAD_POOL_SIZE);
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public MetricsCollector(MetricsStorage storage, int threadNumToSaveData) {
        this.storage = storage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData));
        eventBus.register(new EventListener());
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        eventBus.post(requestInfo);
    }

    public class EventListener {
        @Subscribe
        public void saveRequestInfo(RequestInfo requestInfo) {
            storage.saveRequestInfo(requestInfo);
        }
    }
}

