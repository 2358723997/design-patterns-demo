package com.design.patterns.demo.coderestructure.demo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * IdGenerator类
 *
 * @author wangjixue
 * @date 5/3/22 11:52 AM
 */
@Slf4j
public class IdGenerator {
    /**
     * 获取请求ID
     *
     * @return
     */
    public static String generate(){
        String id = "";
        try{
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if(tokens.length > 0){
                hostName = tokens[tokens.length-1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while(count < 8){
                int randomAscii = random.nextInt(122);

                if(randomAscii >= 48 && randomAscii <= 57){
                    randomChars[count] = (char) (('0')+(randomAscii -48));
                    count++;
                }else if(randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char) (('A') + (randomAscii - 65));
                    count++;
                }else if(randomAscii >= 97 && randomAscii <= 122){
                    randomChars[count] = (char) (('a')+(randomAscii -97));
                    count++;
                }
            }
            id = String.format("%s-%d-%s",hostName,System.currentTimeMillis(),new String(randomChars));
        }catch (UnknownHostException e){
            log.warn("Failed to get the host name.",e);
        }
        return id;
    }
}
