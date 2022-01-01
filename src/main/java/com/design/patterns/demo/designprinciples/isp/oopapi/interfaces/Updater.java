package com.design.patterns.demo.designprinciples.isp.oopapi.interfaces;

/**
 * Updater接口
 *
 * 功能：热更新
 *
 * @author wangjixue
 * @date 1/1/22 4:00 PM
 */
public interface Updater {
    /**
     * 热部署
     *
     * 从configSource加载配置到address/timeout/maxTotal
     */
    void update();
}
