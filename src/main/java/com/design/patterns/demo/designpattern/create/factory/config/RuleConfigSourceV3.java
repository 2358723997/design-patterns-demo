package com.design.patterns.demo.designpattern.create.factory.config;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.PropertiesRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.JsonRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.PropertiesRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.RuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.XmlRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.factorymethod.YamlRuleConfigParserFactory;
import com.design.patterns.demo.designpattern.create.factory.simple.RuleConfigParserV1Factory;

/**
 * RuleConfigSource类
 *
 * 缺点：从代码实现来看，工厂类对象的创建逻辑又耦合进了load()函数中，
 * 跟我们最初的代码版本非常相似，引入工厂方法非但没有解决问题，反倒让设计
 * 变得更加复杂了。
 *
 *
 * @author wangjixue
 * @date 6/26/22 10:26 PM
 */
public class RuleConfigSourceV3 {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        // 方法工厂第一种调用方式
        RuleConfigParserFactory factory = null;
        if("json".equals(ruleConfigFileExtension)){
            factory = new JsonRuleConfigParserFactory();
        }else if("xml".equals(ruleConfigFileExtension)){
            factory = new XmlRuleConfigParserFactory();
        }else if("yaml".equals(ruleConfigFileExtension)){
            factory = new YamlRuleConfigParserFactory();
        }else if("properties".equals(ruleConfigFileExtension)){
            factory = new PropertiesRuleConfigParserFactory();
        }else{
            throw new InvalidRuleConfigException("Rule config file format is not supporties:"+ruleConfigFileExtension);
        }
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
