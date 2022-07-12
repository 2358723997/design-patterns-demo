package com.design.patterns.demo.designpattern.structure.bridge.newcase;

/**
 * Notification类
 * Notification 类的代码实现有一个最明显的问题，那就是有很多if-else分支逻辑。
 * 实际上，如果每个分支中的代码都不复杂，后期也没有无限膨胀的可能（增加更多if-else分支判断），
 * 那这样的设计问题并不大，没必要非得一定要摒弃if-else 分支逻辑。
 *
 * 不过，Notification的代码显然不符合这个条件。因为每个if-else分支中的代码逻辑都比较复杂，
 * 发送通知的所有逻辑都扎堆在Notification类中。我们知道，类的代码越多，就越难读懂，越难修改，
 * 维护的成本也就越高。很多设计模式都是试图将庞大的类拆分成更细小的类，然后再通过某种更合理的结
 * 构组装在一起。
 *
 * 针对Notification的代码，我们将不同渠道的发送逻辑剥离出来，形成独立的消息发送类（MsgSender
 * 相关类）。其中，Notification类相当于抽象，MsgSender类相当于实现，两者可以独立开发，通过组合
 * 关系（也就是桥梁）任意组合在一起。所谓任意组合的意思就是，不同紧急程度的消息和发送渠道之间的对应关系，
 * 不是在代码中固定写死的，我们可以动态地去指定（比如，通过读取配置来获取对应关系）。
 *
 * @author wangjixue
 * @date 7/13/22 12:00 AM
 */
public abstract class Notification {

    protected MsgSender sender;

    public Notification(MsgSender sender) {
        this.sender = sender;
    }

    public abstract void notifity(String message);
}
