package com.design.patterns.demo.coderestructure.demo.v2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于生成随机 ID 的 Id 生成器。//重构四：添加注释
 *
 *
 * 此类生成的 ID 不是绝对唯一的，
 * 但重复的可能性非常低。
 */
@Slf4j
public class RandomIdGenerator implements LogTraceIdGenerator{//重构一 提高代码可扩展性



    /**
     * 生成随机 ID。只有在极端情况下，才能生成重复ID。
     *
     * @return随机 ID
     */
    @Override
    public String generate() throws IdGenerationFailureException {//重构一
        String substrOfHostName = null;
        try {
            substrOfHostName = getLastfieldOfHostName();
        } catch (UnknownHostException e) { //重构二：封装新异常继续抛出
           throw new IdGenerationFailureException("host name is empty.");
        }
        //if(substrOfHostName == null || substrOfHostName.isEmpty()){//重构二：异常处理
        //    throw new IdGenerationFailureException("host name is empty.");
        //}
        long currentTimeMillis = System.currentTimeMillis();

        String randomString = generateRandomAlphameric(8);//重构二 提供代码可测试性
        String id = String.format("%s-%d-%s", substrOfHostName, currentTimeMillis, randomString);
        return id;
    }

    /**
     * 获取本地主机名和
     * 提取由分隔符 '.' 拆分的名称字符串的最后一个字段。
     *
     * @return 主机名的最后一个字段。如果未获取主机名，则返回 null。
     */
    private String getLastfieldOfHostName() throws UnknownHostException {// 重构一
        String substrOfHostName = null;
        //try {
        String hostName = InetAddress.getLocalHost().getHostName();

        //重构二 NULL判断
        if (hostName == null || hostName.isEmpty()) {
            throw new UnknownHostException();
        }
        //重构二 逻辑拆分
        substrOfHostName = getLastSubstrSplittedByDot(hostName);
        //String[] tokens = hostName.split("\\.");
        //substrOfHostName = tokens[tokens.length -1];

        //}catch (Exception e){////重构二：异常处理，直接抛出
        //    log.warn("Failed to get the host name.",e);
        //}
        return substrOfHostName;

    }

    /**
     * 获取 {@hostName} 的最后一个字段，该字段由 delemiter '.' 拆分。
     *
     * @param hostName 主机名不应为空
     * @return {@hostName} 的最后一个字段。如果 {@hostName} 为空字符串，则返回空字符串。
     */
    @VisibleForTesting //重构二
    protected String getLastSubstrSplittedByDot(String hostName) {//重构二 提供代码可测试性
        //重构二：NULL 判断，如果传入NULL 则抛运行异常
        if(hostName == null || hostName.isEmpty()){
            throw new IllegalArgumentException("host name is empty");
        }
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length -1];
        return substrOfHostName;
    }

    /**
     * 生成随机字符串，其中
     * 仅包含数字、大写字母和小写字母。
     *
     * @param length 长度应不小于0
     * @return 随机字符串 如果 {@length} 为 0 则返回空字符串
     *
     */
    @VisibleForTesting //重构二
    protected String generateRandomAlphameric(int length) {//重构一 复杂逻辑拆分 //重构二 为了提高测试性，将private 改为 protected
        if(length < 0){ //重构二 边界处理
            throw new IllegalArgumentException("parameter length is illegal.");
        }

        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length){
            int maxAscii = 'z';
            int radomAscii = random.nextInt(maxAscii);
            //重构一 提高代码可读性
            boolean isDigit = radomAscii >= '0' && radomAscii < '9';
            boolean isLowercase = radomAscii >= 'a' && radomAscii < 'z';
            boolean isUppercase = radomAscii >= 'A' && radomAscii < 'Z';
            if(isDigit || isLowercase || isUppercase){
                randomChars[count] = (char) radomAscii;
                count++;
            }
        }
        return new String(randomChars);
    }
}
