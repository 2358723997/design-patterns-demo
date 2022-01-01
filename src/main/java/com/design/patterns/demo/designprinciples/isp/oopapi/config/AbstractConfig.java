package com.design.patterns.demo.designprinciples.isp.oopapi.config;

import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ConfigSource;
import lombok.Getter;

/**
 * AbstractConfig类
 *
 * @author wangjixue
 * @date 1/1/22 4:18 PM
 */
@Getter
public abstract class AbstractConfig {

    /**
     * 配置中心（比如zookeeper）
     */
    protected ConfigSource configSource;

    protected String address;

    protected int timeout;

    protected int maxTotal;

}
