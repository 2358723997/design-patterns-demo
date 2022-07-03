package com.design.patterns.demo.designpattern.create.builder;

import org.apache.commons.lang3.StringUtils;

/**
 * ResourcePoolConfig类
 *
 * 需求：请设计出一个资源池配置类 ResourcePoolConfig,该类有多个必填参数（> 4）和非必填参数，且 参数之间存在依赖关系。
 *
 * 具体要求如下：
 *
 * 1、 name是必填的，所以我们把它放到构造函数中，强制创建对象的时候就设置。如果必填的配置项有很多，
 * 把这些必填配置项都放到构造函数中设置，那构造函数就又会出现参数列表很长的问题。如果我们把必填项
 * 也通过set()方法设置，那校验这些必填项是否已经填写的逻辑就无处安放了。
 *
 * 2. 除此之外，假设配置项之间有一定的依赖关系，比如，如果用户设置了maxTotal、maxIdle、minIdle
 * 其中一个，就必须显式地设置另外两个；或者配置项之间有一定的约束条件，比如，maxIdle 和 minIdle
 * 要小于等于 maxTotal。如果我们继续使用现在的设计思路，那这些配置项之间的依赖关系或者约束条件的校
 * 验逻辑就无处安放了。
 *
 * 3. 如果我们希望ResourcePoolConfig类对象是不可变对象，也就是说，对象在创建好之后，就不能再修改
 * 内部的属性值。要实现这个功能，我们就不能在 ResourcePoolConfig 类中暴露set()方法。
 *
 *
 * 为了解决这些问题，建造者模式就派上用场了。
 *
 * 我们可以把校验逻辑放置到Builder类中，先创建建造者，并且通过set()方法设置建造者的变量值，
 * 然后在使用build()方法真正创建对象之前，做集中的校验，校验通过之后才会创建对象。除此之外，
 * 我们把ResourcePoolConfig的构造函数改为private私有权限。这样我们就只能通过建造者来创建
 * ResourcePoolConfig类对象。并且ResourcePoolConfig没有提供任何set()方法，这样我们创
 * 建出来的对象就是不可变对象了。
 *
 * 总结：如果一个类中有很多属性，为了避免构造函数的参数列表过长，影响代码的可读性和易用性，
 * 我们可以通过构造函数配合set()方法来解决。但是，如果存在下面情况中的任意一种，我们就要
 * 考虑使用建造者模式了。
 *
 * 1. 我们把类的必填属性放到构造函数中，强制创建对象的时候就设置。如果必填的属性有很多，把
 * 这些必填属性都放到构造函数中设置，那构造函数就又会出现参数列表很长的问题。如果我们把必填
 * 属性通过set()方法设置，那校验这些必填属性是否已经填写的逻辑就无处安放了。
 *
 * 2. 如果类的属性之间有一定的依赖关系或者约束条件，我们继续使用构造函数配合set()方法的设计
 * 思路，那这些依赖关系或约束条件的校验逻辑就无处安放了。
 *
 * 3. 如果我们希望创建不可变对象，也就是说，对象在创建好之后，就不能再修改内部的属性值，要实
 * 现这个功能，我们就不能在类中暴露set()方法。构造函数配合set()方法来设置属性值的方式就不适用了。
 *
 * 4. 工厂模式是用来创建不同但是相关类型的对象（继承同一父类或者接口的一组子类），由给定的参数来决
 * 定创建哪种类型的对象。
 *
 * 建造者模式是用来创建一种类型的复杂对象，可以通过设置不同的可选参数，“定制化”地创建不同的对象。
 * @author wangjixue
 * @date 7/3/22 4:42 PM
 */
public class ResourcePoolConfig {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    private ResourcePoolConfig(Builder builder) {
        name = builder.name;
        maxTotal = builder.maxTotal;
        maxIdle = builder.maxIdle;
        minIdle = builder.minIdle;
    }

    // 我们将Builder类设计成了ResourcePoolConfig的内部类。
    // 我们也可以将Builder类设计成独立的非内部类ResourcePoolConfigBuilder。
    public static class Builder{
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;


        public ResourcePoolConfig builder(){
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            if(StringUtils.isBlank(name)){
                throw new IllegalArgumentException("name should not be empty.");
            }

            if(maxIdle > maxTotal){
                throw new IllegalArgumentException("maxIdle should not > maxTotal.");
            }
            if(minIdle > maxTotal || minIdle > maxIdle){
                throw new IllegalArgumentException("minIdle should not > (maxTotal or maxIdle).");
            }
            return new ResourcePoolConfig(this);
        }

        public Builder setName(String name){
            if(StringUtils.isBlank(name)){
                throw new IllegalArgumentException("name should not be empty.");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal){
            if(maxTotal <= 0){
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdel(int maxIdel){
            if(maxIdel < 0){
                throw new IllegalArgumentException("maxIdel should be positive.");
            }
            this.maxIdle = maxIdel;
                return this;
        }

        public Builder setMinIdel(int minIdel){
            if(minIdel < 0){
                throw new IllegalArgumentException("maxIdel should be positive.");
            }
            this.minIdle = maxIdle;
            return this;
        }

    }
}
