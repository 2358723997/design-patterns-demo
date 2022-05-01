package com.design.patterns.demo.coderestructure.programmingspecification.art;

import java.util.Date;

/**
 * ExplainingVariable类
 *
 * @author wangjixue
 * @date 5/1/22 2:48 PM
 */
public class ExplainingVariable {

    //案例一：常量取代魔法数字
    //重构前代码
    public double CalculateCircularArea(double radius){
        return (3.1415) * radius * radius;
    }

    //重构后代码

    private static final double PI = 3.1415;
    public double CalculateCircularArea_new(double radius){
        return PI * radius *radius;
    }

    //案例二：解释性变量来解释复杂表达式。
    private static final Date SUMMER_START = null;
    private static final Date SUMMER_END = null;
    public boolean validateDate(Date date){
        if(date.after(SUMMER_START) && date.before(SUMMER_END)){
            return true;
        }
        return false;
    }

    //重构后代码
    public boolean validateDate_new(Date date){
        // 引入解释性变量
        if(isSummer(date)){
            return true;
        }
        return false;
    }

    private boolean isSummer(Date date) {
        return date.after(SUMMER_START) && date.before(SUMMER_END);
    }

}
