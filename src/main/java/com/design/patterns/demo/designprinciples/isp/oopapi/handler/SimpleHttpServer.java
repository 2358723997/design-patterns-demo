package com.design.patterns.demo.designprinciples.isp.oopapi.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Viewer;

/**
 * SimpleHttpServer类
 *
 * @author wangjixue
 * @date 1/1/22 4:43 PM
 */
public class SimpleHttpServer {

    private String host;

    private int port;
    private Map<String, List<Viewer>> viewerMap = new HashMap<>();

    public SimpleHttpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void addViewer(String urlDirectory, Viewer viewer) {
        if (!viewerMap.containsKey(urlDirectory)) {
            viewerMap.put(urlDirectory, new ArrayList<Viewer>());
        }

        viewerMap.get(urlDirectory).add(viewer);

    }

    public void run(){
        // 输出项目的配置信息到一个固定的 HTTP 地址
        // 比如：http://127.0.0.1:2389/config 。
        // 我们只需要在浏览器中输入这个地址，就可以显示出系统的配置信息。
    }
}
