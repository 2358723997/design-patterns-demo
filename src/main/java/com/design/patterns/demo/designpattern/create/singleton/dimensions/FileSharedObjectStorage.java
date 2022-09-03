package com.design.patterns.demo.designpattern.create.singleton.dimensions;

/**
 * FileSharedObjectStorage类
 *
 * @author wangjixue
 * @date 2022/9/3 2:53 PM
 */
public class FileSharedObjectStorage implements SharedObjectStorage{
    private String address;

    public FileSharedObjectStorage(String address) {
        this.address = address;
    }
}
