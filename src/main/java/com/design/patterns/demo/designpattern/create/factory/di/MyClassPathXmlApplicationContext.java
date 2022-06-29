package com.design.patterns.demo.designpattern.create.factory.di;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * MyClassPathXmlApplicationContext类
 *
 * ClassPathXmlApplicationContext负责组装BeansFactory和BeanConfigParser两个类，
 * 串联执行流程：从classpath中加载 XML 格式的配置文件，通过 BeanConfigParser解析为统一
 * 的BeanDefinition格式，然后，BeansFactory根据BeanDefinition来创建对象。
 *
 * @author wangjixue
 * @date 6/28/22 11:31 PM
 */
@Slf4j
public class MyClassPathXmlApplicationContext implements MyApplicationContext {
    private MyBeansFactory beansFactory;
    private BeanConfigParser parser;

    public MyClassPathXmlApplicationContext(String configLocation) {
        beansFactory = new MyBeansFactory();
        parser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);

    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/" + configLocation);

            if (in == null) {
                throw new RuntimeException("Can not find config file:" + configLocation);
            }
            List<MyBeanDefinition> beanDefinitions = parser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    log.error("Failed to close I/O stream,the reason is",e);
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
