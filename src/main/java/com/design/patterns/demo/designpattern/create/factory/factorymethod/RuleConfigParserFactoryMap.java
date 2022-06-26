package com.design.patterns.demo.designpattern.create.factory.factorymethod;

import java.util.HashMap;
import java.util.Map;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import org.apache.commons.lang3.StringUtils;

/**
 * RuleConfigParserFactoryMap类
 *
 * 如何解决RuleConfigSourceV3 中存在的问题？
 *
 * 我们可以为工厂类再创建一个简单工厂RuleConfigParserFactoryMap，
 * 也就是工厂的工厂，用来创建和管理工厂类对象
 *
 * 解析：因为工厂类只包含方法，不包含成员变量，完全可以复用，
 *      不需要每次都创建新的工厂类对象，所以，简单工厂模式的第二种
 *      实现思路更加合适。
 *
 * @author wangjixue
 * @date 6/26/22 11:28 PM
 */
public class RuleConfigParserFactoryMap {

    private static final Map<String, RuleConfigParserFactory> CACHE_FACTORY_MAP = new HashMap<>();

    static {
        CACHE_FACTORY_MAP.put("json",new JsonRuleConfigParserFactory());
        CACHE_FACTORY_MAP.put("xml",new XmlRuleConfigParserFactory());
        CACHE_FACTORY_MAP.put("yaml",new YamlRuleConfigParserFactory());
        CACHE_FACTORY_MAP.put("properties",new PropertiesRuleConfigParserFactory());
    }

    public static RuleConfigParserFactory getParserFactory(String type) throws InvalidRuleConfigException {
        if(StringUtils.isBlank(type)){
            throw new InvalidRuleConfigException("Rule config file format is not supporties:"+type);
        }
       return CACHE_FACTORY_MAP.get(type);
    }
}
