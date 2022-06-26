package com.design.patterns.demo.designpattern.create.factory.factorymethod;

import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;

/**
 * RuleConfigParserFactory接口
 *
 * 工厂方法模式：
 *
 * 相比于简单工厂模式通过大量if分支来判断创建那种对象。工厂方法模式利用多态特性来实现对象
 *
 * 创建的判断逻辑，具体来说通过实现类实现或者继承接口和抽象类的方法来创建不同的parser对象，
 * 这样当我们新增一种 parser 的时候，只需要新增一个实现了RuleConfigParserFactory接口的Factory 类即可。
 * 所以，工厂方法模式比起简单工厂模式更加符合开闭原则。
 **
 * @author wangjixue
 * @date 6/26/22 11:08 PM
 */
public interface RuleConfigParserFactory {
    RuleConfigParser createParser();
}
