package com.design.patterns.demo.designpattern.create.singleton.v1;

import java.io.File;
import java.io.FileWriter;

/**
 * Logger类
 * 自定义实现了一个往文件中打印日志的 Logger 类
 *
 * 存在问题：所有日志写入一个文件中，在 UserController 和 OrderController 中，
 * 我们分别创建两个 Logger 对象。在 Web 容器的 Servlet 多线程环境下，如果两个 Servlet
 * 线程同时分别执行 login() 和 create() 两个函数，并且同时写日志到 log.txt 文件中，那
 * 就有可能存在日志信息互相覆盖的情况。
 *
 * 如何解决？
 * 1. 加锁 （类锁、分布式锁：实现复杂）
 * 2.并发队列（多个线程写入到队列中BlockingQueue，一个线程从队列读取数据写入日志中。）
 * 3. 单例模式
 * @author wangjixue
 * @date 6/19/22 10:42 PM
 */
public class Logger {
    private FileWriter writer;

    public Logger() {
        try {
            File file = new File("/tmp/" + System.currentTimeMillis() + ".log");
            // true: 追加写入
            writer = new FileWriter(file, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        //优化方式一：加类锁
        synchronized (Logger.class){
            try {
                writer.write(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
