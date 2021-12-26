package com.design.patterns.demo.designprinciples.srp.positiveexample;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Serialization类
 *
 * 拆分过度问题：以序列化为例经过拆分之后，Serializer 类和 Deserializer 类的职责更加单一了，
 * 但也随之带来了新的问题。如果我们修改了协议的格式，数据标识从“UEUEUE”改为“DFDFDF”，或者序列
 * 化方式从 JSON 改为了 XML，那 Serializer 类和 Deserializer 类都需要做相应的修改，代码的
 * 内聚性显然没有原来 Serialization 高了。而且，如果我们仅仅对 Serializer 类做了协议修改，而
 * 忘记了修改 Deserializer 类的代码，那就会导致序列化、反序列化不匹配，程序运行出错，也就是说，
 * 拆分之后，代码的可维护性变差了。
 *
 *
 *
 * @author wangjixue
 * @date 12/26/21 3:36 PM
 */
public class Serialization {
    private static final String IDENTIFIER_STRING = "UEUEUE;";

    public String serialze(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder(IDENTIFIER_STRING);
        textBuilder.append(JSON.toJSONString(object));
        return textBuilder.toString();
    }

    public Map<String, String> deserialize(String text){
        if(!text.startsWith(IDENTIFIER_STRING)){
            return Collections.emptyMap();
        }

        text = text.substring(IDENTIFIER_STRING.length());

        return JSON.parseObject(text,new TypeReference<HashMap<String,String>>(){});
    }
}
