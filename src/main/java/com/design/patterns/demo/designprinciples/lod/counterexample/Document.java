package com.design.patterns.demo.designprinciples.lod.counterexample;

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

    public Document(String url) {
        this.url = url;
        HtmlDownloader downloader = new HtmlDownloader();
        this.html = downloader.downloadHtml(url);
    }
}
