package com.design.patterns.demo.designpattern.create.factory.di;

import java.io.InputStream;
import java.util.List;

/**
 * XmlBeanConfigParser类
 *
 * @author wangjixue
 * @date 6/28/22 11:35 PM
 */
public class XmlBeanConfigParser implements BeanConfigParser{
    @Override
    public List<MyBeanDefinition> parse(InputStream inputStream) {
        return null;
    }

    @Override
    public List<MyBeanDefinition> parse(String configContent) {
        return null;
    }
}
