package com.design.patterns.demo.designprinciples.ocp.positiveexample.request;

import lombok.Getter;
import lombok.experimental.Helper;

/**
 * ApiStatInfo类
 *
 * @author wangjixue
 * @date 1/2/22 3:16 PM
 */
@Getter
public class ApiStatInfo {

    private String api;

    private long requestCount;

    private long errorCount;

    private long timeoutCount;

    private long durationOfSeconds;

}
