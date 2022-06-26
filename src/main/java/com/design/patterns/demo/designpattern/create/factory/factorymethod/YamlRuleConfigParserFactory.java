package com.design.patterns.demo.designpattern.create.factory.factorymethod;

import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;

/**
 * JsonRuleConfigParserFactory类
 *
 * @author wangjixue
 * @date 6/26/22 11:11 PM
 */
public class YamlRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public RuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
