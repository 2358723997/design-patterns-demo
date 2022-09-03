package com.design.patterns.demo.designpattern.action.observer.eventbus;


import io.reactivex.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MySubscribe接口
 *
 * @author wangjixue
 * @date 2022/9/3 11:35 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Beta
public @interface MySubscribe {
}
