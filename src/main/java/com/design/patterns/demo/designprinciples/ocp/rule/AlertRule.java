package com.design.patterns.demo.designprinciples.ocp.rule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

/**
 * AlertRule类
 *
 * @author wangjixue
 * @date 1/2/22 2:29 PM
 */
@Getter
public class AlertRule {
    private static final Map<String, AlertRule> RULES_CACHE = new ConcurrentHashMap<>();

    private long maxTps = Long.MAX_VALUE;

    private long maxErrorCount = Long.MAX_VALUE;

    private long maxTimeoutTps = Long.MAX_VALUE;


    public AlertRule getMatchedRule(String api) {
        if(!RULES_CACHE.containsKey(api)){
            RULES_CACHE.put(api,new AlertRule());
        }
        return RULES_CACHE.get(api);
    }
}
