package com.design.patterns.demo.orientedobject.oauth.manager;

/**
 * CredentialStorage类
 *
 * @author wangjixue
 * @date 12/19/21 3:00 PM
 */
public interface CredentialStorage {
    /**
     * 根据AppID获取密码
     *
     * todo 数据可以存储在本地磁盘，本地内存、内存型数据库、关系型数据库,所以此处有接口
     *
     * @param appId
     * @return
     */
    String getPasswordByAppId(String appId);
}
