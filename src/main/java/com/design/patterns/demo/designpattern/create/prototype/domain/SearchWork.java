package com.design.patterns.demo.designpattern.create.prototype.domain;

import lombok.Data;

/**
 * SearchWork类
 *
 * @author wangjixue
 * @date 7/4/22 11:27 PM
 */
@Data
public class SearchWork {
    private long lastUpdateTime;
    private String keyWord;
    private Integer count;

    public SearchWork() {
    }

    public SearchWork(long lastUpdateTime, String keyWord, Integer count) {
        this.lastUpdateTime = lastUpdateTime;
        this.keyWord = keyWord;
        this.count = count;
    }
}
