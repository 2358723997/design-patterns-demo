package com.design.patterns.demo.designprinciples.lod.positiveexample.factory;

import com.design.patterns.demo.designprinciples.lod.domain.Html;
import com.design.patterns.demo.designprinciples.lod.positiveexample.Document;
import com.design.patterns.demo.designprinciples.lod.positiveexample.HtmlDownloader;

/**
 * DocumentFactory类
 *
 * 通过一个工厂方法来创建Document
 *
 * @author wangjixue
 * @date 1/3/22 11:10 PM
 */
public class DocumentFactory {
    private HtmlDownloader downloader;

    public DocumentFactory(HtmlDownloader downloader) {
        this.downloader = downloader;
    }

    public Document createDocument(String url){
        Html html = downloader.downloadHtml(url);
        return new Document(url,html);
    }
}
