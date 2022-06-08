package com.design.patterns.demo.coderestructure.demo.performancecounters.v2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * EmailReporter类
 *
 * @author wangjixue
 * @date 6/8/22 11:11 PM
 */
public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage storage;
    private EmailSender sender;
    private List toAddresses = new ArrayList<>();

    public EmailReporter(MetricsStorage storage) {
        this(storage,new EmailSender());
    }

    public EmailReporter(MetricsStorage storage, EmailSender sender) {
        this.storage = storage;
        this.sender = sender;
    }

    public void addToAddress(String toAddress){
        toAddresses.add(toAddress);
    }

    public void startDailyReport(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.HOUR_OF_DAY,0);
        calendar.add(Calendar.MINUTE,0);
        calendar.add(Calendar.SECOND,0);
        calendar.add(Calendar.MILLISECOND,0);
        Date firstTime = calendar.getTime();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos = storage.getRequestInfos(startTimeInMillis, endTimeInMillis);

                Map stats = new HashMap<>();
                requestInfos.forEach((apiName,requestInfoList)->{
                    // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                    RequestStat stat = Aggregator.aggregate(requestInfoList, durationInMillis);
                    stats.put(apiName,stat);
                });
                // TODO 第3个代码逻辑：将统计数据格式化为html格式，并且发送邮件；

            }
        },firstTime,DAY_HOURS_IN_SECONDS * 1000);
    }
}
