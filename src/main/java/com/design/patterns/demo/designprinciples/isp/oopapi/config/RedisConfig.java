package com.design.patterns.demo.designprinciples.isp.oopapi.config;

    import java.util.HashMap;
    import java.util.Map;

    import com.alibaba.fastjson.JSON;
    import com.alibaba.fastjson.TypeReference;
    import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ConfigSource;
    import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Updater;
    import com.design.patterns.demo.designprinciples.isp.oopapi.interfaces.Viewer;

/**
 * RedisConfig类
 *
 * @author wangjixue
 * @date 1/1/22 3:45 PM
 */
public class RedisConfig extends AbstractConfig implements Updater, Viewer {

    public RedisConfig(ConfigSource configSource) {
        super();
        super.configSource = configSource;
    }

    /**
     * 热部署
     *
     * 从configSource加载配置到address/timeout/maxTotal
     */
    @Override
    public void update() {
        super.address = configSource.getAddress();
        super.timeout = configSource.getTimeout();
        super.maxTotal = configSource.getMaxTotal();
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
