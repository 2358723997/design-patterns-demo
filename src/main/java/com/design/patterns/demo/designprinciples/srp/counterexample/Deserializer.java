package com.design.patterns.demo.designprinciples.srp.counterexample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Deserializer类
 *
 * @author wangjixue
 * @date 12/26/21 3:54 PM
 */
public class Deserializer {
    private static final String IDENTIFIER_STRING = "UEUEUE;";


    public Map<String, String> deserialize(String text){
        if(!text.startsWith(IDENTIFIER_STRING)){
            return Collections.emptyMap();
        }

        text = text.substring(IDENTIFIER_STRING.length());

        return JSON.parseObject(text,new TypeReference<HashMap<String,String>>(){});
    }
}
