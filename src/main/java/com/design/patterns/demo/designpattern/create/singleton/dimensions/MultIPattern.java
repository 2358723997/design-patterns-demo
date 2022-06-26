package com.design.patterns.demo.designpattern.create.singleton.dimensions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * MultIPattern类
 * 多例模式
 *
 * “多例”指的就是，一个类可以创建多个对象，但是个数是有限制的，比如只能创建 3 个对象。
 *
 * @author wangjixue
 * @date 6/26/22 8:59 PM
 */
public class MultIPattern {

    private static final int SERVER_COUNT = 3;

    private static final Map<Long,MultIPattern> CACHE_MAP = new HashMap<>();
    private long serverId;
    private String serverAddress;

    private MultIPattern(long serverId, String serverAddress) {
        this.serverId = serverId;
        this.serverAddress = serverAddress;
    }

    static {
        CACHE_MAP.put(1l,new MultIPattern(1l,"127.0.0.1:8080"));
        CACHE_MAP.put(2l,new MultIPattern(2l,"127.0.0.1:80"));
        CACHE_MAP.put(3l,new MultIPattern(3l,"127.0.0.1:443"));
    }

    public MultIPattern getInstance(Long serverId){
        return CACHE_MAP.get(serverId);
    }

    public MultIPattern getRandomInstance(){
        Random random = new Random();
        int serverId = random.nextInt(SERVER_COUNT) + 1;
        return CACHE_MAP.get(serverId);
    }
}
