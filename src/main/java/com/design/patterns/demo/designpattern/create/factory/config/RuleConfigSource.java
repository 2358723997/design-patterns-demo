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
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
      String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
      RuleConfigParser parser = null;
      if("json".equals(ruleConfigFileExtension)){
        parser = new JsonRuleConfigParser();
      }else if("xml".equals(ruleConfigFileExtension)){
          parser = new XmlRuleConfigParser();
      }else if("yaml".equals(ruleConfigFileExtension)){
          parser = new YamlRuleConfigParser();
      }else if("properties".equals(ruleConfigFileExtension)){
          parser = new PropertiesRuleConfigParser();
      }else{
          throw new InvalidRuleConfigException("Rule config file format is not supporties:"+ruleConfigFileExtension);
      }
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
