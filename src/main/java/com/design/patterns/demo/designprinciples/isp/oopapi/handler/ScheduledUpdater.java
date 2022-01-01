package com.design.patterns.demo.designprinciples.isp.oopapi.handler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Updater;

/**
 * ScheduledUpdater类
 *
 * @author wangjixue
 * @date 1/1/22 4:50 PM
 */
public class ScheduledUpdater {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private long initialDelayInSeconds;

    private long periodInSeconds;

    private Updater updater;

    public ScheduledUpdater(Updater updater, long initialDelayInSeconds, long periodInSeconds) {
        this.initialDelayInSeconds = initialDelayInSeconds;

        this.periodInSeconds = periodInSeconds;

        this.updater = updater;
    }

    public void run(){
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updater.update();
            }
        },this.initialDelayInSeconds,this.periodInSeconds, TimeUnit.SECONDS);
    }
}
