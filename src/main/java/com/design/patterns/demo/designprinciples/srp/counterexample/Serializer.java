package com.design.patterns.demo.designprinciples.srp.counterexample;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Serializer类
 *
 * @author wangjixue
 * @date 12/26/21 3:53 PM
 */
public class Serializer {

    private static final String IDENTIFIER_STRING = "UEUEUE;";

    public String serialze(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder(IDENTIFIER_STRING);
        textBuilder.append(JSON.toJSONString(object));
        return textBuilder.toString();
    }

}
