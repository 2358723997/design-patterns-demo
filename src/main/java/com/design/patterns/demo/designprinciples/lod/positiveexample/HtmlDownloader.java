package com.design.patterns.demo.designprinciples.lod.positiveexample;

import com.design.patterns.demo.designprinciples.lod.domain.Html;
import com.design.patterns.demo.designprinciples.lod.request.HtmlRequest;

/**
 * HtmlDownloader类
 *
 * @author wangjixue
 * @date 1/3/22 10:47 PM
 */
public class HtmlDownloader {

    private NetworkTransporter transporter;//通过构造函数或IOC注入

    public Html downloadHtml(String url) {
        HtmlRequest htmlRequest = new HtmlRequest(url);
        Byte[] rawHtml = transporter.send(htmlRequest.getAddress(),htmlRequest.getContent().getBytes());
        return new Html(rawHtml);
    }
}
