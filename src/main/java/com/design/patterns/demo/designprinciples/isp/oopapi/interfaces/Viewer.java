package com.design.patterns.demo.designprinciples.isp.oopapi.interfaces;

import java.util.Map;

/**
 * Viewer监控接口
 *
 * @author wangjixue
 * @date 1/1/22 4:06 PM
 */
public interface Viewer {
    /**
     * 监控-输出文本信息
     *
     * @return
     */
    String outputInPlainText();

    /**
     * 监控-输出监控项
     *
     * @return
     */
    Map<String,String> output();
}
