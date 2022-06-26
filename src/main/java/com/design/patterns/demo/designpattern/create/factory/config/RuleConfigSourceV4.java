package com.design.patterns.demo.designpattern.create.factory.config;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.JsonRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.PropertiesRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.RuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.RuleConfigParserFactoryMap;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.XmlRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.YamlRuleConfigParserFactory;

/**
 * RuleConfigSource类
 *
 * 使用工厂的工厂来解决RuleConfigSourceV3的问题
 *
 * 那什么时候该用工厂方法模式，而非简单工厂模式呢？
 *
 * 我们前面提到，之所以将某个代码块剥离出来，独立为函数或者类，
 * 原因是这个代码块的逻辑过于复杂，剥离之后能让代码更加清晰，更加可读、可维护。
 * 但是，如果代码块本身并不复杂，就几行代码而已，我们完全没必要将它拆分成单独的函数或者类。
 *
 * 基于这个设计思想，当对象的创建逻辑比较复杂，不只是简单的new一下就可以，而是要组合其他类
 * 对象，做各种初始化操作的时候，我们推荐使用工厂方法模式，将复杂的创建逻辑拆分到多个工厂类
 * 中，让每个工厂类都不至于过于复杂。而使用简单工厂模式，将所有的创建逻辑都放到一个工厂类中，
 * 会导致这个工厂类变得很复杂。
 *
 * 除此之外，在某些场景下，如果对象不可复用，那工厂类每次都要返回不同的对象。如果我们使用简单
 * 工厂模式来实现，就只能选择第一种包含if分支逻辑的实现方式。如果我们还想避免烦人的 if-else
 * 分支逻辑，这个时候，我们就推荐使用工厂方法模式。
 *
 *
 * @author wangjixue
 * @date 6/26/22 10:26 PM
 */
public class RuleConfigSourceV4 {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        // 方法工厂第二种调用方式
        RuleConfigParserFactory factory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        RuleConfigParser parser = factory.createParser();

        String configText = "";
        RuleConfig  ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    private String getFileExtension(String file){
        // 解析文件获取文件拓展名,rule.json 返回json.
        return "json";
    }
}
