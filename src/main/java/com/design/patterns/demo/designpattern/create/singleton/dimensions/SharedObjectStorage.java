package com.design.patterns.demo.designpattern.create.singleton.dimensions;

/**
 * SharedObjectStorage接口
 *
 * @author wangjixue
 * @date 2022/9/3 2:51 PM
 */
public interface SharedObjectStorage {

    ClusterSingleton load(Class<ClusterSingleton> clusterSingletonClass);
}
