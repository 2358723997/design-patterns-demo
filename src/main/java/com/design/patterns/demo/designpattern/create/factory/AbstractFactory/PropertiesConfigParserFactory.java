package com.design.patterns.demo.designpattern.create.factory.AbstractFactory;

import com.design.patterns.demo.designpattern.create.factory.config.PropertiesSystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.SystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.YamlSystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.PropertiesRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;

/**
 * JsonConfigParserFactory类
 *
 * @author wangjixue
 * @date 6/26/22 11:45 PM
 */
public class PropertiesConfigParserFactory implements ConfigParserFactory{
    @Override
    public RuleConfigParser createRuleParser() {
        return new PropertiesRuleConfigParser();
    }

    @Override
    public SystemParser createSystemParser() {
        return new PropertiesSystemParser();
    }
}
