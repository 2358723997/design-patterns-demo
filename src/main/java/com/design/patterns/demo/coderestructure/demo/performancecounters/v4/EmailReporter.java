package com.design.patterns.demo.coderestructure.demo.performancecounters.v4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RedisMetricsStorage;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.Aggregator;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.EmailViewer;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v3.StatViewer;

/**
 * EmailReporter类
 *
 * @author wangjixue
 * @date 6/14/22 11:58 PM
 */
public class EmailReporter extends ScheduledReporter{
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        this(new RedisMetricsStorage(),new Aggregator(),new EmailViewer(emailToAddresses));
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public EmailReporter(MetricsStorage storage, Aggregator aggregator, StatViewer viewer) {
        super(storage, aggregator, viewer);
    }

    public void startDailyReport() {
        Date fristTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMills = DAY_HOURS_IN_SECONDS * 1000;
                long endInMills = System.currentTimeMillis();
                long startInMills = System.currentTimeMillis() - durationInMills;

                doStatAndReport(startInMills,endInMills);
            }
        },fristTime,DAY_HOURS_IN_SECONDS * 1000);
    }

    /**
     * 获取下一天0点时间
     *  将获取时间逻辑抽离成函数，提供代码可读性和可测试性 ，通过依赖注入时间解决方法强依赖当前的系统时间的问题。
     * @param date
     * @return
     */
    protected Date trimTimeFieldsToZeroOfNextDay(Date date){
        Calendar calendar = Calendar.getInstance();// 这里可以获取当前时间
        calendar.setTime(date);// 重新设置时间
        calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.HOUR_OF_DAY,0);
        calendar.add(Calendar.MINUTE,0);
        calendar.add(Calendar.SECOND,0);
        calendar.add(Calendar.MILLISECOND,0);
        Date fristTime = calendar.getTime();
        return fristTime;
    }
}
