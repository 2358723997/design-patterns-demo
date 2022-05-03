package com.design.patterns.demo.coderestructure.programmingspecification.art;

import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

/**
 * ArtDemo类
 *
 * @author wangjixue
 * @date 4/20/22 10:49 PM
 */
public class ArtDemo {
    // 1. 把代码分割成更小的单元块 也可理解为 将复杂的逻辑提炼拆分成函数和类
    public void invest(long userId, long financialProductId){

        //判断当前时间是否为本月最后一天
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.set(Calendar.DATE,(instance.get(Calendar.DATE)+1));
        if(instance.get(Calendar.DAY_OF_MONTH) == 1){
            return;
        }
    }

    //优化后代码
    //public void invest(long userId, long financialProductId){
    //
    //    //判断当前时间是否为本月最后一天
    //    if(isLastDayOfMonth(new Date())){
    //        return;
    //    }
    //}

    private boolean isLastDayOfMonth(Date date){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.DATE,(instance.get(Calendar.DATE)+1));
        return instance.get(Calendar.DAY_OF_MONTH) == 1;
    }
}
