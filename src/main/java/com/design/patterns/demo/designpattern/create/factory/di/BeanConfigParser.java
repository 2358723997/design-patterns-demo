package com.design.patterns.demo.designpattern.create.factory.di;

import java.io.InputStream;
import java.util.List;

/**
 * BeanConfigParser接口
 *
 * 配置文件解析
 *
 * @author wangjixue
 * @date 6/28/22 11:33 PM
 */
public interface BeanConfigParser {

    List<MyBeanDefinition> parse(InputStream inputStream);

    List<MyBeanDefinition> parse(String configContent);
}
