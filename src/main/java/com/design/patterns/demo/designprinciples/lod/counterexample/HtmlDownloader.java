package com.design.patterns.demo.designprinciples.lod.counterexample;

import com.design.patterns.demo.designprinciples.lod.request.HtmlRequest;
import com.design.patterns.demo.designprinciples.lod.domain.Html;

/**
 * HtmlDownloader类
 *
 * @author wangjixue
 * @date 1/3/22 10:47 PM
 */
public class HtmlDownloader {

    private NetworkTransporter transporter;//通过构造函数或IOC注入

    public Html downloadHtml(String url) {

        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        return new Html(rawHtml);
    }

}
