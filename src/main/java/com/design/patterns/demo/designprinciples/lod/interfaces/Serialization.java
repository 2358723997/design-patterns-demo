package com.design.patterns.demo.designprinciples.lod.interfaces;

/**
 * Serialization类
 *
 * @author wangjixue
 * @date 1/3/22 11:18 PM
 */
public class Serialization implements Serializable,Deserializable {
    @Override
    public Object deserialize(String text) {
        return null;
    }

    @Override
    public String serialize(Object object) {
        return null;
    }
}
