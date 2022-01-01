package com.design.patterns.demo.designprinciples.isp.oopapi.config;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ConfigSource;
import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Viewer;

/**
 * MysqlConfig类
 *
 * @author wangjixue
 * @date 1/1/22 3:58 PM
 */
public class MysqlConfig extends AbstractConfig implements Viewer {

    public MysqlConfig(ConfigSource configSource) {
        super();
        super.configSource = configSource;
    }

    /**
     * 监控-输出文本信息
     *
     * @return
     */
    @Override
    public String outputInPlainText() {
        return JSON.toJSONString(this);
    }

    /**
     * 监控-输出监控项
     *
     * @return
     */
    @Override
    public Map<String, String> output() {
        return JSON.parseObject(this.outputInPlainText(),
            new TypeReference<HashMap<String, String>>(){});
    }
}
