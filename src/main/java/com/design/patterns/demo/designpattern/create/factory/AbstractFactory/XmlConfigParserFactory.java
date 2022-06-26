package com.design.patterns.demo.designpattern.create.factory.AbstractFactory;

import com.design.patterns.demo.designpattern.create.factory.config.JsonSystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.SystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.XmlSystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;

/**
 * JsonConfigParserFactory类
 *
 * @author wangjixue
 * @date 6/26/22 11:45 PM
 */
public class XmlConfigParserFactory implements ConfigParserFactory{
    @Override
    public RuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public SystemParser createSystemParser() {
        return new XmlSystemParser();
    }
}
