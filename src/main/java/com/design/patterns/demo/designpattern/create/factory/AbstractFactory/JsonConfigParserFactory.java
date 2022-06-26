package com.design.patterns.demo.designpattern.create.factory.AbstractFactory;

import com.design.patterns.demo.designpattern.create.factory.config.JsonSystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.SystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;

/**
 * JsonConfigParserFactory类
 *
 * @author wangjixue
 * @date 6/26/22 11:45 PM
 */
public class JsonConfigParserFactory implements ConfigParserFactory{
    @Override
    public RuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public SystemParser createSystemParser() {
        return new JsonSystemParser();
    }
}
