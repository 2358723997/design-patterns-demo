package com.design.patterns.demo.designprinciples.isp.oopapi.config;

import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ConfigSource;
import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Updater;

/**
 * KafkaConfig类
 *
 * @author wangjixue
 * @date 1/1/22 3:58 PM
 */
public class KafkaConfig extends AbstractConfig implements Updater {

    public KafkaConfig(ConfigSource configSource) {
        super();
        super.configSource = configSource;
    }

    /**
     * 热部署
     *
     * 从configSource加载配置到address/timeout/maxTotal
     */
    @Override
    public void update() {
        super.address = configSource.getAddress();
        super.timeout = configSource.getTimeout();
        super.maxTotal = configSource.getMaxTotal();
    }
}
