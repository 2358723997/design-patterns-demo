package com.design.patterns.demo.designpattern.create.factory.config.parser;

import com.design.patterns.demo.designpattern.create.factory.config.RuleConfig;

/**
 * RuleConfigParser接口
 *
 * @author wangjixue
 * @date 6/26/22 10:34 PM
 */
public interface RuleConfigParser {
    /**
     * 将配置文件解析为规则配置
     *
     * @param configText
     * @return
     */
    RuleConfig parse(String configText);
}
