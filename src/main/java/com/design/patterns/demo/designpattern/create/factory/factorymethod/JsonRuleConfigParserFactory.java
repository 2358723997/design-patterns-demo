package com.design.patterns.demo.designpattern.create.factory.factorymethod;

import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;

/**
 * JsonRuleConfigParserFactory类
 *
 * @author wangjixue
 * @date 6/26/22 11:11 PM
 */
public class JsonRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public RuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
