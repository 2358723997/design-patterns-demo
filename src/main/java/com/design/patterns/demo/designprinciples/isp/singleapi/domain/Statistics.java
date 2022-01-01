package com.design.patterns.demo.designprinciples.isp.singleapi.domain;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * Statistics类
 *
 * 接口设计分析：count() 函数的功能不够单一，包含很多不同的统计功能，比如，求最大值、最小值、平均值等
 * 场景一：如果在项目中，对每个统计需求，Statistics 定义的那几个统计信息都有涉及，那 count()
 * 函数的设计就是合理的。
 *
 * 场景二：如果每个统计需求只涉及 Statistics 罗列的统计信息中一部分，比如，有的只需要用到 max、
 * min、average 这三类统计信息，在这个应用场景下，count() 函数的设计就有点不合理了，这种场景下
 * 需要将其拆分成粒度更细的多个统计函数。
 *
 * 总结：ISP提供了一种判断接口是否职责单一的标准：通过调用者如何使用接口来间接地判定。如果调用者只
 * 使用部分接口或接口的部分功能，那接口的设计就不够职责单一。
 *
 * @author wangjixue
 * @date 12/26/21 4:43 PM
 */
@Getter
public class Statistics {

    private Long max;
    private Long min;
    private Long average;
    private Long sum;
    private Long percentile99;
    private Long percentile999;

    /**
     * 场景一下合理
     */
    public Statistics count(Collection<Long> dataSet) {
        Statistics statistics = new Statistics();
        //求最大值
        statistics.setMax(2L);
        // 最小值
        statistics.setMin(0L);
        // 平均值
        statistics.setAverage(1L);
        return statistics;
    }

    /**
     * 场景二下合理
     *
     * @param dataSet
     * @return
     */
    public Long max(Collection<Long> dataSet) {
        return 2L;
    }

    public Long min(Collection<Long> dataSet) {
        return 0L;
    }

    public Long average(Collection<Long> dataSet) {
        return 1L;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public void setAverage(Long average) {
        this.average = average;
    }
}
