package com.design.patterns.demo.designpattern.create.factory.AbstractFactory;

import com.design.patterns.demo.designpattern.create.factory.config.SystemParser;
import com.design.patterns.demo.designpattern.create.factory.config.parser.RuleConfigParser;

/**
 * ConfigParserFactory类
 *
 * 使用场景：
 *
 * 如果类有两种分类方式，比如，我们既可以按照配置文件格式来分类，也可以按照解析的对象
 * （Rule 规则配置还是 System 系统配置）来分类，那就会对应下面这 8 个 parser 类。
 *
 * 针对这种特殊的场景，如果还是继续用工厂方法来实现的话，我们要针对每个 parser 都编
 * 写一个工厂类，也就是要编写 8 个工厂类。如果我们未来还需要增加针对业务配置的解析器
 * （比如 IBizConfigParser），那就要再对应地增加 4 个工厂类。而我们知道，过多的类也
 * 会让系统难维护。这个问题该怎么解决呢？
 *
 * 抽象工厂就是针对这种非常特殊的场景而诞生的。我们可以让一个工厂负责创建多个不同类型的
 * 对象（IRuleConfigParser、ISystemConfigParser 等），而不是只创建一种 parser
 * 对象。这样就可以有效地减少工厂类的个数。
 *
 * @author wangjixue
 * @date 6/26/22 11:42 PM
 */
public interface ConfigParserFactory {

    RuleConfigParser createRuleParser();
    SystemParser createSystemParser();
}
