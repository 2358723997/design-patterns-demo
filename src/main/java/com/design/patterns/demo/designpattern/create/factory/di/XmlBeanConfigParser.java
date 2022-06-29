package com.design.patterns.demo.designpattern.create.factory.di;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XmlBeanConfigParser类
 *
 * 配置文件解析主要包含BeanConfigParser接口和XmlBeanConfigParser实现类，
 * 负责将配置文件解析为BeanDefinition结构，以便BeansFactory根据这个结构来创建对象。
 *
 * @author wangjixue
 * @date 6/28/22 11:35 PM
 */
public class XmlBeanConfigParser implements BeanConfigParser{
    @Override
    public List<MyBeanDefinition> parse(InputStream inputStream) {
        String content = null;
        //TODO 获取数据流中的信息，解析成String字符串
        return parse(content);
    }

    @Override
    public List<MyBeanDefinition> parse(String configContent) {
        List<MyBeanDefinition> beanDefinitions = new ArrayList<>();
        //TODO 根据一定规则，将配置文件中的内容解析为BeanDefinition对象。
        return beanDefinitions;
    }
}
