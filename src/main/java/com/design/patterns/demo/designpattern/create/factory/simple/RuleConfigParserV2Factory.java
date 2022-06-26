package com.design.patterns.demo.designpattern.create.factory.simple;

import java.util.HashMap;
import java.util.Map;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.PropertiesRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;
import org.apache.commons.lang3.StringUtils;

/**
 * RuleConfigParserFactoryV1类
 *
 * 为了让类的职责更加单一、代码更加清晰，我们还可以进一步将
 * createParser()函数剥离到一个独立的类中，
 * 让这个类只负责对象的创建。而这个类就是我们现在要讲的简单
 * 工厂模式类。
 *
 * 简单工厂第二种实现方法：
 *
 * 简单工厂第一种实现方法每次调用都需要创建一个新的对象，如果
 * parser可以复用的话，为了节约内地和对象创建时间，我们可以先
 * 将parser先创建好，缓存起来。当调用 createParser()函数的
 * 时候，我们从缓存中取出 parser 对象直接使用。
 *
 *
 * 总结一下，尽管简单工厂模式的代码实现中，有多处if分支判断逻辑，
 * 违背开闭原则，但权衡扩展性和可读性，这样的代码实现在大多数情况下
 * （比如，不需要频繁地添加 parser，也没有太多的 parser）
 * 是没有问题的。
 *
 * @author wangjixue
 * @date 6/26/22 10:48 PM
 */
public class RuleConfigParserV2Factory {

    private static final Map<String,RuleConfigParser> CACHE_PARSERS = new HashMap<>();
    static {
        CACHE_PARSERS.put("json",new JsonRuleConfigParser());
        CACHE_PARSERS.put("xml",new XmlRuleConfigParser());
        CACHE_PARSERS.put("yaml",new YamlRuleConfigParser());
        CACHE_PARSERS.put("properties",new PropertiesRuleConfigParser());
    }

    public static RuleConfigParser createParser(String ruleConfigFileExtension) throws InvalidRuleConfigException {
        if(StringUtils.isBlank(ruleConfigFileExtension)){
            throw new InvalidRuleConfigException("Rule config file format is not supporties:" + ruleConfigFileExtension);
        }

        return CACHE_PARSERS.get(ruleConfigFileExtension);
    }
}
