package com.design.patterns.demo.designpattern.create.factory.config;

import com.design.patterns.demo.designpattern.create.factory.config.exception.InvalidRuleConfigException;
import com.design.patterns.demo.designpattern.create.factory.config.parser.JsonRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.PropertiesRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.XmlRuleConfigParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.YamlRuleConfigParser;

/**
 * RuleConfigSource类
 *
 * 需求：根据配置文件的后缀（json、xml、yaml、properties），
 * 选择不同的解析器（JsonRuleConfigParser、XmlRuleConfigParser等），
 * 将存储在文件中的配置解析成内存对象 RuleConfig
 *
 * @author wangjixue
 * @date 6/26/22 10:26 PM
 */
public class RuleConfigSourceV1 {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        RuleConfigParser parser = createParser(ruleConfigFileExtension);
        String configText = "";
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    /**
     * 优化点：为了让代码逻辑更加清晰，可读性更好，
     *
     * 我们要善于将功能独立的代码块封装成函数。按照这个设计思路，
     * 我们可以将代码中涉及 parser 创建的部分逻辑剥离出来，
     * 抽象成createParser()函数。
     *
     * @param ruleConfigFileExtension
     * @return
     * @throws InvalidRuleConfigException
     */
    private RuleConfigParser createParser(String ruleConfigFileExtension) throws InvalidRuleConfigException {
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
