package com.design.patterns.demo.designpattern.create.factory.di;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.design.patterns.demo.designpattern.create.factory.di.exception.BeanCreationFailureException;
import javafx.beans.value.ObservableBooleanValue;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * MyBeansFactory类
 *
 * DI容器：
 *
 * 一个简单的 DI 容器的核心功能一般有三个：配置解析、对象创建和对象生命周期管理。
 *
 * 配置解析：
 *
 * 我们将需要由 DI 容器来创建的类对象和创建类对象的必要信息
 * （使用哪个构造函数以及对应的构造函数参数都是什么等等），放到配置文件中。容器读取
 * 配置文件，根据配置文件提供的信息来创建对象。
 *
 * 对象创建：
 *
 * 在 DI 容器中，我们只需要将所有类对象的创建都放到一个工厂类中完成就可以了，比如 BeansFactory。
 * 我们会讲“反射”这种机制，它能在程序运行的过程中，动态地加载类、创建对象，不需要事先在代码中写死要
 * 创建哪些对象。所以，不管是创建一个对象还是十个对象，BeansFactory 工厂类代码都是一样的。
 *
 * 对象生命周期管理：
 *
 * 简单工厂模式有两种实现方式，一种是每次都返回新创建的对象，另一种是每次都返回同一个事先创建好的对象，
 * 也就是所谓的单例对象。在 Spring 框架中，我们可以通过配置 scope 属性，来区分这两种不同类型的对象。
 * scope=prototype 表示返回新创建的对象，scope=singleton 表示返回单例对象。除此之外，我们还可以
 * 配置对象是否支持懒加载。如果 lazy-init=true，对象在真正被使用到的时候（比如：BeansFactory.getBean(“userService”)）
 * 才被被创建；如果 lazy-init=false，对象在应用启动的时候就事先创建好。不仅如此，我们还可以配置对象的 init-method
 * 和 destroy-method 方法，比如 init-method=loadProperties()，destroy-method=updateConfigFile()。
 *
 * DI 容器在创建好对象之后，会主动调用 init-method 属性指定的方法来初始化对象。在对象被最终销毁之前，
 * DI 容器会主动调用 destroy-method 属性指定的方法来做一些清理工作，比如释放数据库连接池、关闭文件。
 *
 *
 * 如何实现一个简单DI？
 *
 * 核心逻辑包括这样两个部分：配置文件解析、根据配置文件通过“反射”语法来创建对象。
 *
 * @author wangjixue
 * @date 6/28/22 11:23 PM
 */
//核心工厂类设计
public class MyBeansFactory {

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, MyBeanDefinition> beanDefinations = new ConcurrentHashMap<>();


    public void addBeanDefinitions(List<MyBeanDefinition> beanDefinitionList){
        for (MyBeanDefinition beanDefinition : beanDefinitionList) {
            this.beanDefinations.putIfAbsent(beanDefinition.getId(),beanDefinition);
        }

        for (MyBeanDefinition beanDefinition : beanDefinitionList) {
            if(beanDefinition.isLazyInit() == false && beanDefinition.isSingleton()){
                createBean(beanDefinition);
            }
        }

    }

    public Object getBean(String beanId) {
        MyBeanDefinition beanDefinition = beanDefinations.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("Bean is not defined:" + beanId);
        }
        return createBean(beanDefinition);
    }

    private Object createBean(MyBeanDefinition beanDefinition) {

        if(beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())){
            return singletonObjects.get(beanDefinition.getId());
        }

        Object bean = null;

        try{
            Class<?> beanClass = Class.forName(beanDefinition.getClassName());
           List<MyBeanDefinition.ConstructorArg> args =  beanDefinition.getConstructorArgs();
            if(args.isEmpty()){
                bean = beanClass.newInstance();
            }else {
                Class[] argClasses = new Class[args.size()];
                Object[] argObjects = new Object[args.size()];

                for (int i = 0; i < args.size(); i++) {
                    MyBeanDefinition.ConstructorArg arg = args.get(i);
                    if(!arg.getIsRef()){
                      argClasses[i] = arg.getType();
                      argObjects[i] = arg.getArg();
                    }else{
                        MyBeanDefinition refBeandefinition = beanDefinations.get(arg.getArg());
                        if(refBeandefinition == null){
                            throw new NoSuchBeanDefinitionException("Bean is not defined:" + arg.getArg());
                        }
                        argClasses[i] = Class.forName(refBeandefinition.getClassName());
                        argObjects[i] = createBean(refBeandefinition);
                    }
                }

                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        }catch (Exception e){
            throw new BeanCreationFailureException("",e);
        }

        if(bean != null && beanDefinition.isSingleton()){
            singletonObjects.putIfAbsent(beanDefinition.getId(),bean);
            return singletonObjects.get(beanDefinition.getId());
        }

        return bean;
    }

}
