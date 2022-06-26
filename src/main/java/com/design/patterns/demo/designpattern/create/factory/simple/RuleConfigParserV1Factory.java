package com.design.patterns.demo.designpattern.create.factory.simple;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.PropertiesRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;

/**
 * RuleConfigParserFactoryV1类
 *
 * 为了让类的职责更加单一、代码更加清晰，我们还可以进一步将
 * createParser()函数剥离到一个独立的类中，
 * 让这个类只负责对象的创建。而这个类就是我们现在要讲的简单
 * 工厂模式类。
 *
 *
 * @author wangjixue
 * @date 6/26/22 10:48 PM
 */
public class RuleConfigParserV1Factory {

    public static RuleConfigParser createParser(String ruleConfigFileExtension) throws InvalidRuleConfigException {
        RuleConfigParser parser = null;
        if ("json".equals(ruleConfigFileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equals(ruleConfigFileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equals(ruleConfigFileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equals(ruleConfigFileExtension)) {
            parser = new PropertiesRuleConfigParser();
        } else {
            throw new InvalidRuleConfigException("Rule config file format is not supporties:" + ruleConfigFileExtension);
        }
        return parser;
    }
}
