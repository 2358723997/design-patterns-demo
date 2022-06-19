package com.design.patterns.demo.designpattern.create.singleton.v2;

import java.io.File;
import java.io.FileWriter;

/**
 * Logger类
 * 自定义实现了一个往文件中打印日志的 Logger 类
 *
 * 单例模式 -- 饿汉模式
 * @author wangjixue
 * @date 6/19/22 10:42 PM
 */
public class Logger {
    private FileWriter writer;

    private static final Logger instance = new Logger();

    private Logger() {
        try {
            File file = new File("/tmp/" + System.currentTimeMillis() + ".log");
            // true: 追加写入
            writer = new FileWriter(file, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(){
        return instance;
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
