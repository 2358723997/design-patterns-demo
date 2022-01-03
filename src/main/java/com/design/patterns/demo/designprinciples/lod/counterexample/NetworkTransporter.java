package com.design.patterns.demo.designprinciples.lod.counterexample;

import com.design.patterns.demo.designprinciples.lod.request.HtmlRequest;

/**
 * NetworkTransporter类
 *
 * 需求描述：实现了简化版的搜索引擎爬取网页的功能。代码中包含三个主要的类。
 *
 *  NetworkTransporter 类负责底层网络通信，根据请求获取数据；
 *  HtmlDownloader 类用来通过 URL 获取网页；
 *  Document 表示网页文档，后续的网页内容抽取、分词、索引都是以此为处理对象。
 *
 * @author wangjixue
 * @date 1/3/22 10:47 PM
 */
public class NetworkTransporter {
    public Byte[] send(HtmlRequest htmlRequest){
        return null;
    }
}
