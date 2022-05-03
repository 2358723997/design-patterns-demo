# design-patterns-demo

## 面向对象编程

### 实战--基于充血模型、贫血模型分别实现虚拟钱包功能

### 实战--面向对象分析、设计、实战之鉴权功能

## 设计原则

    我们开始学习一些经典的设计原则，其中包括，SOLID、KISS、YAGNI、DRY、LOD 等。

我们需要理解这些原则的定义，设计初衷，解决的问题以及对于的应用场景，在学习的时候，希望把握住重点，真正做到活学活用。

### SOLID

    SOLID 原则并非单纯的 1 个原则，而是由 5 个设计原则组成的，它们分别是：单一职责原则、开闭原则、里式替换原则、接口隔离原则和依赖反转原则，依次对应 SOLID 中的 S、O、L、I、D 这 5 个英文字母。

#### 单一原则(SRP)

#### 接口隔离原则(ISP)

#### 开闭原则(OCP)

#### 里式替换(LSP)

#### 依赖反转(DIP)

### KISS

### YAGNI

### DRY

### 迪米特法则(LOD)

## 规范与重构

### 重构相关概念

### 单元测试

### 代码可测试性

### 大规模高层次重构 -- 解耦

### 小规模低层次重构 -- 编程规范


### 重构实战
#### 代码code review 的checklist

##### 非功能性

- 目录设置是否合理、模块划分是否清晰、代码结构是否满足“高内聚，低耦合”特性；
- 是否遵循经典设计原则与设计思想，如：SOLID、DRY、KISS、YAGNI和LOD等；
- 设计模式是否应用合理，是否过度设计；
- 代码是否易扩展，是否满足开闭原则；
- 代码是否可复用，是否可以复用已有代码或类库，是否存在重复造轮子；
- 代码是否易测试，UT对正常和异常边界情况是否覆盖全面；
- 代码是否可读，是否满足编码规范，如：命名是否准确达意、注释是否恰当、代码风格是否一致、编程是否存在多层嵌套，复杂逻辑或多个入参情况是否进行拆分或封装等。

##### 功能性

- 代码设计是否符合功能预期；
- 逻辑是否正确，异常边界情况是否处置合理；
- 日志打印是否得当，是否方便排查问题？
- 接口是否易用，是否支持CUD场景事务和幂等操作；
- 并发场景下代码是否线程安全，是否存在共享变量；
- 性能是否有优化空间，如：SQL、算法是否可以继续优化；
- 是否存在安全漏洞，输入、输出校验是否全面合理；
