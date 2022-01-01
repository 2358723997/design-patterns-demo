package com.design.patterns.demo.designprinciples.isp.oopapi.configsource;

import lombok.Getter;

/**
 * ConfigSource类
 *
 * @author wangjixue
 * @date 1/1/22 3:57 PM
 */
@Getter
public abstract class ConfigSource {

    public String address;

    private int timeout;

    private int maxTotal;

}
