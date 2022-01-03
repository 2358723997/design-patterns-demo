package com.design.patterns.demo.designprinciples.lod.request;

import lombok.Getter;

/**
 * HtmlRequest类
 *
 * @author wangjixue
 * @date 1/3/22 10:53 PM
 */
@Getter
public class HtmlRequest {
    private String url;
    private String address;
    private String content;

    public HtmlRequest(String url) {
        this.url = url;
    }

}
