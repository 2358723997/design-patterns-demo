package com.design.patterns.demo.designpattern.action.observer.v1;

import org.apache.logging.log4j.message.Message;

/**
 * Observer类
 *
 * @author wangjixue
 * @date 8/10/22 10:43 PM
 */
public interface Observer {
    public void update(Message message);
}
