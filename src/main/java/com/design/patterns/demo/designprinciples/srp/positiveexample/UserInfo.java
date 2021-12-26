package com.design.patterns.demo.designprinciples.srp.positiveexample;

import lombok.Getter;
import lombok.Setter;

/**
 * UserInfo类
 *
 * 该类是否满足单一职责？
 *
 * 分析问题要结合实际的应用场景：如果在这个社交产品中，用户的地址信息跟其他信息一样，只是单纯地用来展示，那 UserInfo 现在的设计就是合理的。
 * 但是，如果这个社交产品发展得比较好，之后又在产品中添加了电商的模块，用户的地址信息还会用在电商物流中，那我们最好将地址信息从 UserInfo 中拆分出来，独立成用户物流信息（或者叫地址信息、收货信息等）。
 *
 * @author wangjixue
 * @date 12/26/21 3:30 PM
 */
@Getter
@Setter
public class UserInfo {

    private long userId;
    private String username;
    private String email;
    private String telephone;
    private long createTime;
    private long lastLoginTime;
    private String avatarUrl;
    private String provinceOfAddress; // 省
    private String cityOfAddress; // 市
    private String regionOfAddress; // 区
    private String detailedAddress; // 详细地址

}
