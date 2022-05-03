package com.design.patterns.demo.coderestructure.demo.v2;

/**
 * IdGenerator接口 重构一 提高代码可扩展性
 *
 * @author wangjixue
 * @date 5/3/22 7:14 PM
 */
public interface IdGenerator {
    public String generate() throws IdGenerationFailureException;
}
