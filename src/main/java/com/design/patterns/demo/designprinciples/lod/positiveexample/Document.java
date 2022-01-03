package com.design.patterns.demo.designprinciples.lod.positiveexample;

import com.design.patterns.demo.designprinciples.lod.domain.Html;

/**
 * Document类
 *
 * @author wangjixue
 * @date 1/3/22 10:47 PM
 */
public class Document {
    private Html html;
    private String url;

    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }

}
